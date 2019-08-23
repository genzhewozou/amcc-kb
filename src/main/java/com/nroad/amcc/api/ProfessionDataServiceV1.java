package com.nroad.amcc.api;

import com.nroad.amcc.PlatformError;
import com.nroad.amcc.PlatformException;
import com.nroad.amcc.kb.*;
import com.nroad.amcc.support.View.*;
import com.nroad.amcc.support.configuration.AuthenticationUtil;
import com.nroad.amcc.support.jpa.HistoryDataJpaRepository;
import com.nroad.amcc.support.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.*;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


@Service
public class ProfessionDataServiceV1 {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private HistoryDataJpaRepository historyDataJpaRepository;

    @Autowired
    private ProfessionDetailsRepository professionDetailsRepository;

    @Autowired
    private ContractedAreaRepository contractedAreaRepository;

    @Autowired
    AreaTop3ProfessionRepository areaTop3ProfessionRepository;

    @Autowired
    StudentsScoreRepository studentsScoreRepository;

    @Autowired
    AreaAdmitNumberRepository areaAdmitNumberRepository;

    @Autowired
    LastYearScoreRepository lastYearScoreRepository;

    @Autowired
    GraduateStudentRepository graduateStudentRepository;

    @Autowired
    CommonQuestionRepository commonQuestionRepository;

    @Autowired
    AdmissionPolicyRepository admissionPolicyRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${amcc.crm.service}")
    private String crmUrl;


    @Autowired
    @Qualifier("classCategoryList")
    private List<String> classCategoryList;

    public Object uploadHistoryData(MultipartFile file) {

        List<String> list;

        try {
            list = new ExcelUtil().readProfession(file.getInputStream());
            HistoryData historyData = new HistoryData();
            int count = 0;
            for (int i = 0; i < (list.size() / 10); i++) {
                historyData.setId(UUID.randomUUID().toString());
                historyData.setArea(list.get(i + count));
                historyData.setClassCategory(list.get(i + count + 1));
                historyData.setPrTitle(list.get(i + count + 2));
                historyData.setPrCode(list.get(i + count + 3));
                historyData.setMinScoreBefore3(list.get(i + count + 4));
                historyData.setMinScoreBefore2(list.get(i + count + 5));
                historyData.setMinScoreBefore1(list.get(i + count + 6));
                historyData.setForecastScore(Integer.parseInt(list.get(i + count + 7)));
                historyData.setAdmissionBatch(list.get(i + count + 8));
                historyData.setPlanNumber(list.get(i + count + 9));
                historyData.setTenantId(AuthenticationUtil.getTenantId());
                count += 9;
                historyDataJpaRepository.save(historyData);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            throw PlatformException.of(PlatformError.KB_UNKNOW_ERROR, e1.getMessage());
        }
        return null;
    }

    public Object uploadProfessionData(MultipartFile file) {

        List<String> list;

        try {
            list = new ExcelUtil().readProfessionDetails(file.getInputStream());
            ProfessionDetails professionDetails = new ProfessionDetails();
            int count = 0;
            for (int i = 0; i < (list.size() / 15); i++) {
                if (list.get(i + count + 7).length() > 500 || list.get(i + count + 8).length() > 500
                        || list.get(i + count + 9).length() > 500 || list.get(i + count + 12).length() > 500
                        || list.get(i + count + 13).length() > 500) {
                    throw PlatformException.of(PlatformError.KB_OVER_ERROR);
                }
                professionDetails.setId(UUID.randomUUID().toString());
                professionDetails.setPrTitle(list.get(i + count));
                professionDetails.setPrCode(list.get(i + count + 1));
                professionDetails.setStudyYears(list.get(i + count + 2));
                professionDetails.setGrantDegree(list.get(i + count + 3));
                professionDetails.setClassCategory(list.get(i + count + 4));
                professionDetails.setProfessionCategory(list.get(i + count + 5));
                professionDetails.setProfessionDirection(list.get(i + count + 6));
                professionDetails.setProfessionCourses(list.get(i + count + 7));
                professionDetails.setCareerDirection(list.get(i + count + 8));
                professionDetails.setTeacherPower(list.get(i + count + 9));
                professionDetails.setBoyProportion(list.get(i + count + 10));
                professionDetails.setGirlProportion(list.get(i + count + 11));
                professionDetails.setTestableCertificate(list.get(i + count + 12));
                professionDetails.setCooperativeInstitution(list.get(i + count + 13));
                professionDetails.setAverageSalary(Double.parseDouble(list.get(i + count + 14)));
                professionDetails.setTenantId(AuthenticationUtil.getTenantId());
                count += 14;
                professionDetailsRepository.save(professionDetails);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            throw PlatformException.of(PlatformError.KB_UNKNOW_ERROR, e1.getMessage());
        }
        return null;
    }

    public Object uploadProfessionArea(MultipartFile file) {
        List<String> list;

        try {
            list = new ExcelUtil().readProfessionDetailsArea(file.getInputStream());
            ContractedArea contractedArea = new ContractedArea();
            int count = 0;
            for (int i = 0; i < (list.size() / 3); i++) {
                ProfessionDetails professionDetails = professionDetailsRepository.findByPrCode(list.get(i + count),
                        AuthenticationUtil.getTenantId());
                contractedArea.setId(UUID.randomUUID().toString());
                contractedArea.setProfessionDetails(professionDetails);
                contractedArea.setName(list.get(i + count + 1));
                contractedArea.setProportion(Double.parseDouble(list.get(i + count + 2)));
                count += 2;
                contractedAreaRepository.save(contractedArea);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            throw PlatformException.of(PlatformError.KB_UNKNOW_ERROR, e1.getMessage());
        }
        return null;
    }

    public Object uploadCommonQuestion(MultipartFile file) {

        List<String> list;

        try {
            list = new ExcelUtil().readCommonQuestion(file.getInputStream());
            CommonQuestion commonQuestion = new CommonQuestion();
            int count = 0;
            for (int i = 0; i < (list.size() / 3); i++) {
                commonQuestion.setId(UUID.randomUUID().toString());
                commonQuestion.setQuestion(list.get(i + count));
                commonQuestion.setAnswer(list.get(i + count + 1));
                commonQuestion.setTenantId(list.get(i + count + 2));
                count += 2;
                commonQuestionRepository.save(commonQuestion);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            throw PlatformException.of(PlatformError.KB_UNKNOW_ERROR, e1.getMessage());
        }
        return null;
    }

    public Object uploadAdmissionPolicy(MultipartFile file) {

        List<String> list;

        try {
            list = new ExcelUtil().readAdmissionPolicy(file.getInputStream());
            AdmissionPolicy admissionPolicy = new AdmissionPolicy();
            int count = 0;
            for (int i = 0; i < (list.size() / 4); i++) {
                admissionPolicy.setId(UUID.randomUUID().toString());
                admissionPolicy.setArea(list.get(i + count));
                admissionPolicy.setDate(list.get(i + count + 1));
                admissionPolicy.setTitle(list.get(i + count + 2));
                admissionPolicy.setUrl(list.get(i + count + 3));
                count += 3;
                admissionPolicyRepository.save(admissionPolicy);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            throw PlatformException.of(PlatformError.KB_UNKNOW_ERROR, e1.getMessage());
        }
        return null;
    }

    public Page<HistoryData> findProfession(Pageable pageable, String area, String classCategory, String prTitle, String prCode,
                                            Integer studentScore, String tenantId) {
        Page<HistoryData> historyDataList = historyDataJpaRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("tenantId").as(String.class), tenantId));
            if (StringUtils.isNotEmpty(area) && area != null) {
                predicates.add(criteriaBuilder.equal(root.get("area").as(String.class), area));
            }
            if (studentScore != null && studentScore != 0) {
                predicates.add(criteriaBuilder.le(root.<Integer>get("forecastScore"), studentScore));
            }
            if (classCategory != null && StringUtils.isNotEmpty(classCategory)) {
                predicates.add(criteriaBuilder.equal(root.get("classCategory").as(String.class), classCategory));
            }
            if (prTitle != null) {
                predicates.add(criteriaBuilder.like(root.get("prTitle").as(String.class), "%" + prTitle + "%"));
            }
            if (prCode != null) {
                predicates.add(criteriaBuilder.like(root.get("prCode").as(String.class), "%" + prCode + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return historyDataList;
    }

    public ViewProfessionDetails findProfessionDetails(String prCode) {
        ProfessionDetails professionDetails = professionDetailsRepository.findByPrCode(prCode, AuthenticationUtil.getTenantId());
        ViewProfessionDetails viewProfessionDetails = new ViewProfessionDetails();

        List<ContractedArea> contractedAreas = contractedAreaRepository.findAllByProfession(professionDetails.getId());
        List<ViewContractedArea> viewContractedAreas = new ArrayList<>();
        if (contractedAreas != null) {
            for (int i = 0; i < contractedAreas.size(); i++) {
                ViewContractedArea viewContractedArea = new ViewContractedArea();
                viewContractedArea.setName(contractedAreas.get(i).getName());
                viewContractedArea.setProportion(contractedAreas.get(i).getProportion());
                viewContractedAreas.add(viewContractedArea);
            }
        }

        viewProfessionDetails.setId(professionDetails.getId());
        viewProfessionDetails.setPrTitle(professionDetails.getPrTitle());
        viewProfessionDetails.setPrCode(professionDetails.getPrCode());
        viewProfessionDetails.setBoyProportion(professionDetails.getBoyProportion());
        viewProfessionDetails.setGirlProportion(professionDetails.getGirlProportion());
        viewProfessionDetails.setCareerDirection(professionDetails.getCareerDirection());
        viewProfessionDetails.setGrantDegree(professionDetails.getGrantDegree());
        viewProfessionDetails.setProfessionCategory(professionDetails.getProfessionCategory());
        viewProfessionDetails.setProfessionCourses(professionDetails.getProfessionCourses());
        viewProfessionDetails.setStudyYears(professionDetails.getStudyYears());
        viewProfessionDetails.setAverageSalary(professionDetails.getAverageSalary());
        viewProfessionDetails.setClassCategory(professionDetails.getClassCategory());
        viewProfessionDetails.setContractedAreas(viewContractedAreas);
        viewProfessionDetails.setTeacherPower(professionDetails.getTeacherPower());
        viewProfessionDetails.setCooperativeInstitution(professionDetails.getCooperativeInstitution());
        viewProfessionDetails.setProfessionDirection(professionDetails.getProfessionDirection());
        viewProfessionDetails.setTestableCertificate(professionDetails.getTestableCertificate());
        return viewProfessionDetails;
    }

    public List<String> findAllProfession(String tenantId) {
        return historyDataJpaRepository.findAllProfession(tenantId);
    }

    public UserPortrait generateUserPortrait(String provinceName, int score, String classCategory, List<String> prCodes,
                                             String mobilePhone) throws Exception {
        UserPortrait userPortrait = new UserPortrait();

        userPortrait.setViewBestProfessions(accessBestProfession(provinceName, prCodes, score, classCategory));
        userPortrait.setViewLastYearScores(accessLastYearScore(provinceName, classCategory));
        userPortrait.setViewGraduateAreas(BestProfessionArea(provinceName, prCodes, score, classCategory));
        userPortrait.setRank(accessRank(provinceName, score, classCategory));
        userPortrait.setAlumni(accessAlumni(mobilePhone));
        userPortrait.setCandidateInformation(accessCandidateInformation(mobilePhone));
        return userPortrait;
    }

    /*
    查询去年本校招生的各个科类所有分数对应人数
     */
    public List<ViewLastYearScore> accessLastYearScore(String area, String classCategory) {
        List<Integer> scoreList = lastYearScoreRepository.getAllScore(AuthenticationUtil.getTenantId(), area);
        List<ViewLastYearScore> viewLastYearScores = new ArrayList<>();
        if (scoreList != null && scoreList.size() != 0) {
            scoreList.forEach(score ->
            {
                ViewLastYearScore viewLastYearScore = new ViewLastYearScore();
                viewLastYearScore.setScore(score);
                viewLastYearScore.setNumber(lastYearScoreRepository.getCountScoreByClassCategory(classCategory, score,
                        AuthenticationUtil.getTenantId(), area));
                viewLastYearScores.add(viewLastYearScore);
            });
            return viewLastYearScores;
        }
        return null;
    }

    /*
   组装Top3专业
    */
    private List<ViewAreaTop3Profession> accessBestProfession(String provinceName, List<String> studentsPrCodes
            , int score, String classCategory) throws Exception {

        List<String> accessPrCodes = historyDataJpaRepository.findAllProfessionByScore((score + 10),
                AuthenticationUtil.getTenantId(), provinceName, classCategory);
        List<AreaAdmitNumber> areaAdmitNumbers = new ArrayList<>();

        if (accessPrCodes == null || accessPrCodes.size() == 0) {  //如果没有满足考生分数的专业
            return null;
        } else {
            areaAdmitNumbers = areaAdmitNumberRepository.findAllByName(provinceName, accessPrCodes,
                    AuthenticationUtil.getTenantId());  //查询出省top3
        }

        List<String> prCodes = new ArrayList<>();

        for (int i = 0; i < areaAdmitNumbers.size(); i++) {  //在history-data表中有的专业，在area-admit-number中也一定有
            String temp = new String();
            temp = areaAdmitNumbers.get(i).getPrCode();
            prCodes.add(temp);
        }
        log.info("sss");
        if (null != studentsPrCodes && studentsPrCodes.size() != 0) {  //如果考生选择了专业

            List<String> accessLists = available(studentsPrCodes, score, provinceName, classCategory);

            if (accessLists.size() == 0) {

                return accessEmploymentArea(prCodes, score, classCategory, provinceName);
            } else {
                List<String> prCodesTemps = sortStudentsPrCodes(accessLists, provinceName);  //排序

                if (prCodesTemps.size() != 0 && prCodesTemps != null) {

                    if (prCodesTemps.size() != 3) {
                        for (int i = 0; i < prCodes.size(); i++) {
                            if (prCodesTemps.contains(prCodes.get(i))) {
                                continue;
                            } else
                                prCodesTemps.add(prCodes.get(i));
                            if (prCodesTemps.size() == 3) {
                                break;
                            }
                        }
                    }
                }
                for (int k = 0; k < prCodesTemps.size(); k++) {
                    log.info(prCodesTemps.get(k) + "sssm");
                }
                return accessEmploymentArea(prCodesTemps, score, classCategory, provinceName);
            }
        }
        return accessEmploymentArea(prCodes, score, classCategory, provinceName);
    }

    /*
  组装Top1的毕业生去向
   */
    private List<ViewGraduateArea> BestProfessionArea(String provinceName, List<String> studentsPrCodes, int score, String classCategory) throws Exception {

        List<String> accessPrCodes = historyDataJpaRepository.findAllProfessionByScore((score + 10),
                AuthenticationUtil.getTenantId(), provinceName, classCategory);
        List<AreaAdmitNumber> areaAdmitNumbers = new ArrayList<>();

        if (accessPrCodes == null || accessPrCodes.size() == 0) {  //如果没有满足考生分数的专业
            return null;
        } else {
            areaAdmitNumbers = areaAdmitNumberRepository.findAllByName(provinceName, accessPrCodes,
                    AuthenticationUtil.getTenantId());  //查询出省top3
        }

        List<String> prCodes = new ArrayList<>();

        for (int i = 0; i < areaAdmitNumbers.size(); i++) {  //在history-data表中有的专业，在area-admit-number中也一定有
            String temp = new String();
            temp = areaAdmitNumbers.get(i).getPrCode();
            prCodes.add(temp);
        }

        if (null != studentsPrCodes && studentsPrCodes.size() != 0) {  //如果考生选择了专业

            List<String> accessLists = available(studentsPrCodes, score, provinceName, classCategory);

            if (accessLists.size() == 0) {
                return accessGraduate(prCodes);
            }


            List<String> prCodesTemps = sortStudentsPrCodes(accessLists, provinceName);  //排序

            if (prCodesTemps.size() != 0 && prCodesTemps != null) {
                if (prCodesTemps.size() != 3) {
                    for (int i = 0; i < prCodes.size(); i++) {
                        if (prCodesTemps.contains(prCodes.get(i))) {
                            continue;
                        } else
                            prCodesTemps.add(prCodes.get(i));
                        if (prCodesTemps.size() == 3) {
                            break;
                        }
                    }
                }
                for (int k = 0; k < prCodesTemps.size(); k++) {
                    log.info(prCodesTemps.get(k) + "sss");
                }
                return accessGraduate(prCodesTemps);
            }
        }
        return accessGraduate(prCodes);
    }

    /*
    组装各个专业热门就业地区top1,录取批次对应人数,男女分布情况,毕业薪资超越情况
     */
    private List<ViewAreaTop3Profession> accessEmploymentArea(List<String> prCodes, int score, String classCategory, String provinceName) {
        List<AreaTop3Profession> areaTop3Professions = new ArrayList<>();
        int totalProfession = professionDetailsRepository.getAllPrCode(AuthenticationUtil.getTenantId());//获取本校所有专业数目

        for (int i = 0; i < prCodes.size(); i++) {
            AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
            areaTop3Profession = areaTop3ProfessionRepository.findByPrCode(prCodes.get(i), AuthenticationUtil.getTenantId());
            log.info(areaTop3Profession.getPrCode() + "ok");

            //组装各个专业热门就业地区top1
            if (null != areaTop3Profession) {
                ProfessionDetails professionDetails = new ProfessionDetails();
                professionDetails = professionDetailsRepository.findByPrCode(prCodes.get(i), AuthenticationUtil.getTenantId());
                List<ContractedArea> contractedAreas = contractedAreaRepository.findAllByProfession(professionDetails.getId());
                if (contractedAreas == null || contractedAreas.size() == 0) {
                    areaTop3Profession.setEmploymentArea(null);
                } else {
                    contractedAreas.sort((x, y) -> Double.compare(x.getProportion(), y.getProportion()));
                    Collections.reverse(contractedAreas);
                    ContractedArea contractedArea = contractedAreas.get(0);
                    areaTop3Profession.setEmploymentArea(contractedArea.getName());
                    areaTop3Profession.setEmploymentAreaProportion(contractedArea.getProportion());
                }

                //组装录取批次map
                String prCode = new String();
                prCode = prCodes.get(i);
                Map<String, Integer> admissionMap = new HashMap<>();
                List<String> admissions = new ArrayList<>();
                admissions = historyDataJpaRepository.findAllBatch(prCodes.get(i), provinceName,
                        classCategory, AuthenticationUtil.getTenantId());
                for (int j = 0; j < admissions.size(); j++) {
                    int panNumber = historyDataJpaRepository.findNUmber(prCode, provinceName,
                            classCategory, AuthenticationUtil.getTenantId(), admissions.get(j));
                    admissionMap.put(admissions.get(j), panNumber);
                }
                areaTop3Profession.setAdmissionMap(admissionMap);

                //组装男女比例
                String boyProportion = professionDetails.getBoyProportion();
                String girlProportion = professionDetails.getGirlProportion();
                areaTop3Profession.setGirlProportion(girlProportion);
                areaTop3Profession.setBoyProportion(boyProportion);

                //组装毕业薪资超越情况
                double salary = professionDetails.getAverageSalary();

                log.info(totalProfession + "");
                int surpassingProfessionNumber = professionDetailsRepository.getSurpassingProfessionNumber(salary,
                        AuthenticationUtil.getTenantId());
                log.info(surpassingProfessionNumber + "");
                if (totalProfession == 0 || surpassingProfessionNumber == 0) {
                    areaTop3Profession.setSurpassingProfessionNumber(0.00);
                } else {
                    BigDecimal b = new BigDecimal((double) surpassingProfessionNumber / totalProfession);
                    areaTop3Profession.setSurpassingProfessionNumber(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }

                areaTop3Professions.add(areaTop3Profession);
            }

        }
//        areaTop3Professions = accessScoreRank(areaTop3Professions, score, classCategory);
        List<ViewAreaTop3Profession> viewAreaTop3Professions = accessViewAreaTop3Profession(areaTop3Professions);
//        viewAreaTop3Professions.sort((x, y) -> Double.compare(x.getCompeteIndex(), y.getCompeteIndex()));
//        Collections.reverse(viewAreaTop3Professions);
        return viewAreaTop3Professions;
    }

    /*
    排序studentsPrCodes
     */
    private List<String> sortStudentsPrCodes(List<String> studentsPrCodes, String areaName) throws Exception {

        List<AreaAdmitNumber> areaAdmitNumberStudents = areaAdmitNumberRepository.findAllByName(areaName, studentsPrCodes,
                AuthenticationUtil.getTenantId());

        List<String> prCodes = new ArrayList<>();
        if (areaAdmitNumberStudents == null || areaAdmitNumberStudents.size() == 0) {
            throw new Exception("NON Data");
        } else {
            for (int i = 0; i < areaAdmitNumberStudents.size(); i++) {
                String temp = new String();
                temp = areaAdmitNumberStudents.get(i).getPrCode();
                log.info(temp + "xxx");
                prCodes.add(temp);
            }
        }
        for (int j = 0; j < studentsPrCodes.size(); j++) {  //如果这个专业课可以上，但是在去年这个地区没有招生，今年招生了，那么需要加上
            if (prCodes.contains(studentsPrCodes.get(j))) {
                continue;
            } else
                prCodes.add(studentsPrCodes.get(j));
        }
        return prCodes;
    }

    /*
    判断考生自选专业是否可上
     */
    public List<String> available(List<String> studentsPrCodes, int score, String provinceName, String classCategory) {
        List<String> accessLists = new ArrayList<>();
        for (int m = 0; m < studentsPrCodes.size(); m++) {  //判断考生自选的是否可以上

            List<Integer> accessList = historyDataJpaRepository.findScore(studentsPrCodes.get(m), provinceName
                    , AuthenticationUtil.getTenantId(), classCategory);
            Collections.sort(accessList);

            if (accessList.size() == 2) {
                if ((score + 10) >= accessList.get(1)) {
                    accessLists.add(studentsPrCodes.get(m));  //此处无法区分本科还是专科
                    continue;
                } else if ((score + 10) >= accessList.get(0)) {
                    accessLists.add(studentsPrCodes.get(m));
                    continue;
                }
            } else if ((score + 10) >= accessList.get(0)) {
                accessLists.add(studentsPrCodes.get(m));
                continue;
            }
        }
        for (int i = 0; i < accessLists.size(); i++) {
            log.info(accessLists.get(i) + "");
        }
        return accessLists;
    }

    /*
    组装专业top1的毕业生去向
     */
    public List<ViewGraduateArea> accessGraduate(List<String> prCodes) {
        List<String> allAreas = graduateStudentRepository.getAllArea();
        if (allAreas.size() == 0 || allAreas == null) {
            return null;
        }
        if (prCodes.size() == 0 || prCodes == null) {
            return null;
        }
        String prCode = prCodes.get(0);
        List<ViewGraduateArea> viewGraduateAreas = new ArrayList<>();

        allAreas.forEach(area -> {
            ViewGraduateArea viewGraduateArea = new ViewGraduateArea();
            viewGraduateArea.setArea(area);
            viewGraduateArea.setNumber(graduateStudentRepository.getAllAreaCount(prCode, area, AuthenticationUtil.getTenantId()));
            viewGraduateAreas.add(viewGraduateArea);
        });
        return viewGraduateAreas;
    }

    /*
    组装考生在本校相同科类中的位次
     */
    private double accessRank(String area, int score, String classCategory) {
        int countTotal = lastYearScoreRepository.getCountNumber(classCategory, AuthenticationUtil.getTenantId(), area);
        int surpassingNumber = lastYearScoreRepository.getsurpassingNumber(classCategory, AuthenticationUtil.getTenantId(),
                area, score);
        if (surpassingNumber == 0 || countTotal == 0) {
            return 0.00;
        } else {
            BigDecimal b = new BigDecimal((double) surpassingNumber / countTotal);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    /*
    组装历届校友
     */
    public int accessAlumni(String mobilePhone) {
        String url = MessageFormat.format(crmUrl + "/api/v1/cust/getSchoolByMobilePhone?mobilePhone={0}&tenantId={1}",
                mobilePhone, AuthenticationUtil.getTenantId());
        log.info("getFreeSeatUrl:{}", url);
        String school = restTemplate.getForObject(url, String.class);
        log.info(school);
        int number = graduateStudentRepository.getAlumni(school, AuthenticationUtil.getTenantId());
        return number;
    }

    /*
    组装考生基本信息
     */
    public CandidateInformation accessCandidateInformation(String mobilePhone) {
        String url = MessageFormat.format(crmUrl + "/api/v1/cust/getInformationByMobilePhone?mobilePhone={0}&tenantId={1}",
                mobilePhone, AuthenticationUtil.getTenantId());
        log.info("getFreeSeatUrl:{}", url);
        CandidateInformation candidateInformation = restTemplate.getForObject(url, CandidateInformation.class);
        return candidateInformation;
    }

    /*
    组装竞争力
     */
    private List<AreaTop3Profession> accessScoreRank(List<AreaTop3Profession> areaTop3Professions,
                                                     int score, String classCategory) {
        if (null != areaTop3Professions) {
            for (int i = 0; i < areaTop3Professions.size(); i++) {
                AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
                areaTop3Profession = areaTop3Professions.get(i);
                int number = studentsScoreRepository.getTotalNumber(classCategory, areaTop3Profession.getPrCode(),
                        AuthenticationUtil.getTenantId());  //获取本专业所有录取人数
                int surpassingNumber = studentsScoreRepository.getSurpassingNumber(classCategory, areaTop3Profession.getPrCode(),
                        AuthenticationUtil.getTenantId(), score);  //获取考生超过的人数
                if (surpassingNumber == 0 || number == 0) {
                    areaTop3Profession.setScoreRank(0.00);
                } else {
                    BigDecimal b = new BigDecimal((double) surpassingNumber / number);
                    areaTop3Profession.setScoreRank(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
                areaTop3Professions.set(i, areaTop3Profession);
            }
            return areaTop3Professions;
        }
        return null;
    }

    /*
    组装视图返回
     */
    private List<ViewAreaTop3Profession> accessViewAreaTop3Profession
    (List<AreaTop3Profession> areaTop3Professions) {
        List<ViewAreaTop3Profession> viewAreaTop3Professions = new ArrayList<>();

        for (int i = 0; i < areaTop3Professions.size(); i++) {
            AreaTop3Profession areaTop3Profession = areaTop3Professions.get(i);
            ViewAreaTop3Profession viewAreaTop3Profession = new ViewAreaTop3Profession();
//            viewAreaTop3Profession.setCompeteIndex(areaTop3Profession.getScoreRank());
            viewAreaTop3Profession.setEmploymentRate(areaTop3Profession.getEmploymentRate());
            viewAreaTop3Profession.setEmploymentSalary(areaTop3Profession.getEmploymentSalary());
            viewAreaTop3Profession.setPrCode(areaTop3Profession.getPrCode());
            viewAreaTop3Profession.setPrTitle(areaTop3Profession.getPrTitle());
            viewAreaTop3Profession.setEmploymentArea(areaTop3Profession.getEmploymentArea());
            viewAreaTop3Profession.setAdmissionMap(areaTop3Profession.getAdmissionMap());
            viewAreaTop3Profession.setBoyProportion(areaTop3Profession.getBoyProportion());
            viewAreaTop3Profession.setGirlProportion(areaTop3Profession.getGirlProportion());
            viewAreaTop3Profession.setSurpassingProfessionNumber(areaTop3Profession.getSurpassingProfessionNumber());
            viewAreaTop3Profession.setEmploymentAreaProportion(areaTop3Profession.getEmploymentAreaProportion());
            viewAreaTop3Professions.add(viewAreaTop3Profession);
        }
        return viewAreaTop3Professions;
    }


    private static final String defaultCharset = "UTF-8";
    private static final String KEY_AES = "AES";

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key  加密密码
     * @return
     */
    public static String encrypt(String data, String key) {
        return doAES(data, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key  解密密钥
     * @return
     */
    public static String decrypt(String data, String key) {
        return doAES(data, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 加解密
     *
     * @param data     待处理数据
     * @param password 密钥
     * @param mode     加解密mode
     * @return
     */
    private static String doAES(String data, String password, int mode) {
        try {
            if (StringUtils.isBlank(data) || StringUtils.isBlank(password)) {
                return null;
            }
            //判断是加密还是解密
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            //true 加密内容 false 解密内容
            if (encrypt) {
                content = data.getBytes(defaultCharset);
            } else {
                content = parseHexStr2Byte(data);
            }
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_AES);
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            kgen.init(128, new SecureRandom(password.getBytes()));
            //3.产生原始对称密钥
            SecretKey secretKey = kgen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, KEY_AES);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance(KEY_AES);// 创建密码器
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(mode, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                //将二进制转换成16进制
                return parseByte2HexStr(result);
            } else {
                return new String(result, defaultCharset);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}

