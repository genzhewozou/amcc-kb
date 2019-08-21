package com.nroad.amcc.api;

import com.aliyuncs.exceptions.ClientException;
import com.nroad.amcc.PlatformError;
import com.nroad.amcc.PlatformException;
import com.nroad.amcc.Sms.SmsUtil;
import com.nroad.amcc.kb.HistoryData;
import com.nroad.amcc.kb.UserPortrait;
import com.nroad.amcc.support.View.ViewProfessionDetails;
import com.nroad.amcc.support.configuration.AuthenticationUtil;
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

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/professionData")
public class ProfessionDataControllerV1 {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ProfessionDataServiceV1 professionDataServiceV1;

    @Autowired
    public ProfessionDataControllerV1(ProfessionDataServiceV1 professionDataServiceV1) {
        Assert.notNull(professionDataServiceV1, "HistoryDataServiceV1 can not be null");
        this.professionDataServiceV1 = professionDataServiceV1;
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
                                             @RequestParam(value = "prCodes", required = false) List<String> prCodes) throws Exception {

        return professionDataServiceV1.generateUserPortrait(provinceName, score, classCategory, prCodes);
    }

    @PostMapping(value = "/send/Sms")
    public void senSms(@RequestParam(value = "mobile", required = false) String mobile) {
        SmsUtil smsUtil = new SmsUtil();
        smsUtil.sendSms(mobile, "54651");
    }

}
