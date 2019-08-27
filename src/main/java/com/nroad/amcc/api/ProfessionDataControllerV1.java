package com.nroad.amcc.api;

import com.aliyuncs.exceptions.ClientException;
import com.nroad.amcc.PlatformError;
import com.nroad.amcc.PlatformException;
import com.nroad.amcc.Sms.SmsUtil;
import com.nroad.amcc.kb.*;
import com.nroad.amcc.support.View.ViewCommonQuestion;
import com.nroad.amcc.support.View.ViewLastYearScore;
import com.nroad.amcc.support.View.ViewProfessionDetails;
import com.nroad.amcc.support.configuration.AuthenticationUtil;
import com.nroad.amcc.support.utils.AESUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import sun.misc.BASE64Encoder;

import java.net.URLDecoder;
import java.util.*;

@RestController
@RequestMapping("/api/v1/professionData")
public class ProfessionDataControllerV1 {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ProfessionDataServiceV1 professionDataServiceV1;

    private CommonQuestionRepository commonQuestionRepository;

    private AdmissionPolicyRepository admissionPolicyRepository;

    @Autowired
    public ProfessionDataControllerV1(ProfessionDataServiceV1 professionDataServiceV1,
                                      CommonQuestionRepository commonQuestionRepository,
                                      AdmissionPolicyRepository admissionPolicyRepository) {
        Assert.notNull(professionDataServiceV1, "HistoryDataServiceV1 can not be null");
        Assert.notNull(commonQuestionRepository, "commonQuestionRepository can not be null");
        Assert.notNull(admissionPolicyRepository, "admissionPolicyRepository can not be null");
        this.professionDataServiceV1 = professionDataServiceV1;
        this.commonQuestionRepository = commonQuestionRepository;
        this.admissionPolicyRepository = admissionPolicyRepository;
    }

    @PostMapping("/upload/historyData")
    @ApiOperation(value = "上传往三年专业分数", notes = "")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public void uploadHistoryData(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (file.isEmpty() || StringUtils.isEmpty(filename) || !filename.endsWith(".xlsx")) {
            throw PlatformException.of(PlatformError.KB_UPLOAD_FILE_NOT_BLANK);
        }
        professionDataServiceV1.uploadHistoryData(file);
    }

    @PostMapping("/upload/professionDetails")
    @ApiOperation(value = "上传专业详情", notes = "")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public void uploadProfessionData(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (file.isEmpty() || StringUtils.isEmpty(filename) || !filename.endsWith(".xlsx")) {
            throw PlatformException.of(PlatformError.KB_UPLOAD_FILE_NOT_BLANK);
        }
        professionDataServiceV1.uploadProfessionData(file);
    }

    @PostMapping("/upload/commonQuestion")
    @ApiOperation(value = "上传常见问题", notes = "")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public void uploadCommonQuestion(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (file.isEmpty() || StringUtils.isEmpty(filename) || !filename.endsWith(".xlsx")) {
            throw PlatformException.of(PlatformError.KB_UPLOAD_FILE_NOT_BLANK);
        }
        professionDataServiceV1.uploadCommonQuestion(file);
    }

    @PostMapping("/upload/professionArea")
    @ApiOperation(value = "上传专业详情的毕业生去向Top3", notes = "")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public void uploadProfessionArea(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (file.isEmpty() || StringUtils.isEmpty(filename) || !filename.endsWith(".xlsx")) {
            throw PlatformException.of(PlatformError.KB_UPLOAD_FILE_NOT_BLANK);
        }
        professionDataServiceV1.uploadProfessionArea(file);
    }

    @GetMapping(value = "/query")
    @ApiOperation(value = "所有专业名字")
    public List<String> getAllProfession() {
        return professionDataServiceV1.findAllProfession(AuthenticationUtil.getTenantId());
    }

    @GetMapping(value = "/queryProfession")
    @ApiOperation(value = "查询专业列表", notes = "根据area、classCategory、prTitle、prCode")
    public Page<HistoryData> findProfession(@RequestParam(value = "area", required = false) String area,
                                            @RequestParam(value = "classCategory", required = false) String classCategory,
                                            @RequestParam(value = "profession", required = false) String profession,
                                            @RequestParam(value = "studentScore", required = false) Integer studentScore,
                                            @RequestParam("page") int page,
                                            @RequestParam("size") int size) {
        String prTitle = null;
        String prCode = null;

        if (StringUtils.isNotEmpty(profession)) {
            Boolean isPrCode = false;
            for (int i = 0; i < profession.length(); i++) {
                if (Character.isDigit(profession.charAt(i))) {  //如果是数字
                    isPrCode = true;
                    break;
                } else {
                    isPrCode = false;
                }
            }
            if (isPrCode) {
                prCode = profession;
            } else {
                prTitle = profession;
            }
        }

        return professionDataServiceV1.findProfession(new PageRequest(page, size), area, classCategory, prTitle, prCode,
                studentScore, AuthenticationUtil.getTenantId());
    }

    @GetMapping(value = "/query/professionDetails")
    @ApiOperation(value = "查询专业详情", notes = "根据prCode")
    public ViewProfessionDetails getProfessionDetails(@RequestParam("prCode") String prCode) {
        return professionDataServiceV1.findProfessionDetails(prCode);
    }

    @GetMapping(value = "/generate/userPortrait")
    @ApiOperation(value = "生成用户画像", notes = "根据用户所在区域名字，分数以及意向专业")
    public UserPortrait generateUserPortrait(@RequestParam(value = "provinceName") String provinceName,
                                             @RequestParam(value = "score") int score,
                                             @RequestParam(value = "classCategory") String classCategory,
                                             @RequestParam(value = "mobilePhone") String mobilePhone,
                                             @RequestParam(value = "prCodes", required = false) List<String> prCodes) throws Exception {

        return professionDataServiceV1.generateUserPortrait(provinceName, score, classCategory, prCodes, mobilePhone);
    }

    @PostMapping(value = "/generateKey")
    @ApiOperation(value = "生成加密串", notes = "根据用户所在区域名字，分数以及意向专业")
    public void encryption(@RequestParam(value = "provinceName") String provinceName,
                           @RequestParam(value = "score") int score,
                           @RequestParam(value = "classCategory") String classCategory,
                           @RequestParam(value = "mobilePhone") String mobilePhone,
                           @RequestParam(value = "prCodes", required = false) List<String> prCodes) throws Exception {
        int length = prCodes.size();
        String prCode = "";

        String name = professionDataServiceV1.accessCandidateName(mobilePhone);

        SmsUtil smsUtil = new SmsUtil();
        String password = "sde@5f98H*^hsff%dfs$r344&df8543*er";

        if (length == 0) {
            String toBeEncrypted = new String(mobilePhone + "," + provinceName + "," +
                    classCategory + "," + score);

            log.info(toBeEncrypted + "");
            String encryptionString = AESUtils.encrypt(toBeEncrypted, password);  //加密后的串
            log.info(encryptionString + "");
            smsUtil.sendSms(name, mobilePhone, encryptionString);
        }
        if (length != 0 && length == 1) {
            prCode = prCodes.get(0);
            String toBeEncrypted = new String(mobilePhone + "," + provinceName + "," +
                    classCategory + "," + score + "," + prCode);
            log.info(toBeEncrypted + "");
            String encryptionString = AESUtils.encrypt(toBeEncrypted, password);  //加密后的串
            log.info(encryptionString + "");
            smsUtil.sendSms(name, mobilePhone, encryptionString);
        }
        if (length != 0 && length > 1) {
            prCode = prCodes.get(0) + ",";
            for (int i = 1; i < prCodes.size(); i++) {
                prCode = prCode + prCodes.get(i) + ",";
            }
            prCode = prCode.substring(0, prCode.length() - 1);

            String toBeEncrypted = new String(mobilePhone + "," + provinceName + "," +
                    classCategory + "," + score + "," + prCode);
            log.info(toBeEncrypted + "");
            String encryptionString = AESUtils.encrypt(toBeEncrypted, password);  //加密后的串
            log.info(encryptionString + "");
            smsUtil.sendSms(name, mobilePhone, encryptionString);
        }

    }

    @GetMapping(value = "/accessByKey")
    @ApiOperation(value = "解密加密串并访问")
    public UserPortrait decrypt(@RequestParam(value = "encryptionString") String encryptionString) throws Exception {
        String password = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        String list = AESUtils.decrypt(encryptionString, password);
        String[] totalList = list.split(",");
        String mobilePhone = totalList[0];
        String provinceName = totalList[1];
        String classCategory = totalList[2];
        int score = Integer.parseInt(totalList[3]);
        int length = totalList.length;
        log.info(length + "");
        List<String> prCodes = new ArrayList<>();
        if (length > 4) {
            for (int i = 0; i < length - 4; i++) {
                prCodes.add(totalList[4 + i]);
            }
        }
        return professionDataServiceV1.generateUserPortrait(provinceName, score, classCategory, prCodes, mobilePhone);
    }

    @GetMapping(value = "/query/answer")
    @ApiOperation(value = "模糊查询常见问题")
    public List<ViewCommonQuestion> findAnswerByKeyWord(@RequestParam(value = "keyWord", required = false) String keyWord) {
        List<ViewCommonQuestion> viewCommonQuestions = new ArrayList<>();

        if (keyWord != null) {
            List<String> questionList = commonQuestionRepository.findByKeyWord(keyWord);
            for (int i = 0; i < questionList.size(); i++) {
                ViewCommonQuestion viewCommonQuestion = new ViewCommonQuestion();
                String answer = commonQuestionRepository.findAnswerByQuestion(questionList.get(i));
                viewCommonQuestion.setQuestion(questionList.get(i));
                viewCommonQuestion.setAnswer(answer);
                viewCommonQuestions.add(viewCommonQuestion);
            }
            return viewCommonQuestions;
        } else {
            List<CommonQuestion> questionList = commonQuestionRepository.findAll();
            for (int i = 0; i < questionList.size(); i++) {
                ViewCommonQuestion viewCommonQuestion = new ViewCommonQuestion();
                CommonQuestion commonQuestion = questionList.get(i);
                viewCommonQuestion.setQuestion(commonQuestion.getQuestion());
                viewCommonQuestion.setAnswer(commonQuestion.getAnswer());
                viewCommonQuestions.add(viewCommonQuestion);
            }
            return viewCommonQuestions;
        }
    }

    @GetMapping(value = "/query/policy")
    @ApiOperation(value = "模糊查询招生政策")
    public List<AdmissionPolicy> findPolicyByKeyWord(@RequestParam(value = "keyWord", required = false) String keyWord,
                                                     @RequestParam(value = "area") String area) {
        List<AdmissionPolicy> admissionPolicies = new ArrayList<>();

        if (keyWord != null) {
            List<String> titleList = admissionPolicyRepository.findByKeyWord(keyWord, area);
            for (int i = 0; i < titleList.size(); i++) {
                AdmissionPolicy admissionPolicy = admissionPolicyRepository.findUrlByTitle(titleList.get(i));
                admissionPolicies.add(admissionPolicy);
            }
            return admissionPolicies;
        }
        return admissionPolicies;
    }

}
