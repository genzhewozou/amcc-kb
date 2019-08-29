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

import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

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
                                             String mobilePhone, String tenantId) throws Exception {
        UserPortrait userPortrait = new UserPortrait();

        userPortrait.setViewBestProfessions(accessBestProfession(provinceName, prCodes, score, classCategory, tenantId));
        userPortrait.setViewLastYearScores(accessLastYearScore(classCategory, tenantId));
        userPortrait.setViewGraduateAreas(BestProfessionArea(provinceName, prCodes, score, classCategory, tenantId));
        userPortrait.setRank(accessRank(provinceName, score, classCategory, tenantId));
        userPortrait.setAlumni(accessAlumni(mobilePhone, tenantId));
        userPortrait.setCandidateInformation(accessCandidateInformation(mobilePhone, tenantId));
        return userPortrait;
    }

    /*
    查询去年本校招生的各个科类所有分数对应人数
     */
    public List<ViewLastYearScore> accessLastYearScore(String classCategory, String tenantId) {
        List<Integer> scoreList = lastYearScoreRepository.getAllScore(tenantId);
        List<ViewLastYearScore> viewLastYearScores = new ArrayList<>();
        if (scoreList != null && scoreList.size() != 0) {
            scoreList.forEach(score ->
            {
                ViewLastYearScore viewLastYearScore = new ViewLastYearScore();
                viewLastYearScore.setScore(score);
                viewLastYearScore.setNumber(lastYearScoreRepository.getCountScoreByClassCategory(classCategory, score,
                        tenantId));
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
            , int score, String classCategory, String tenantId) throws Exception {

        List<String> accessPrCodes = historyDataJpaRepository.findAllProfessionByScore((score + 10),
                tenantId, provinceName, classCategory);
        List<AreaAdmitNumber> areaAdmitNumbers = new ArrayList<>();

        List<String> prCodes = new ArrayList<>();

        if (accessPrCodes == null || accessPrCodes.size() == 0) {  //如果没有满足考生分数的专业
            prCodes = null;
        } else {
            areaAdmitNumbers = areaAdmitNumberRepository.findAllByName(provinceName, accessPrCodes,
                    tenantId);  //查询出省top3

            for (int i = 0; i < areaAdmitNumbers.size(); i++) {  //在history-data表中有的专业，在area-admit-number中也一定有
                String temp = new String();
                temp = areaAdmitNumbers.get(i).getPrCode();
                prCodes.add(temp);
            }
        }

        if (null != studentsPrCodes && studentsPrCodes.size() != 0) {  //如果考生选择了专业

            List<String> prCodesTemps = sortStudentsPrCodes(studentsPrCodes, provinceName, tenantId);  //排序
            if (prCodesTemps.size() != 0 && prCodesTemps != null) {
                if (prCodes != null) {
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
            }
            return accessEmploymentArea(prCodesTemps, score, classCategory, provinceName, tenantId);
//            }
        }
        return accessEmploymentArea(prCodes, score, classCategory, provinceName, tenantId);
    }

    /*
  组装Top1的毕业生去向
   */
    private List<ViewGraduateArea> BestProfessionArea(String provinceName, List<String> studentsPrCodes, int score,
                                                      String classCategory, String tenantId) throws Exception {

        List<String> accessPrCodes = historyDataJpaRepository.findAllProfessionByScore((score + 10),
                tenantId, provinceName, classCategory);
        List<AreaAdmitNumber> areaAdmitNumbers = new ArrayList<>();

        List<String> prCodes = new ArrayList<>();

        if (accessPrCodes == null || accessPrCodes.size() == 0) {  //如果没有满足考生分数的专业
            prCodes = null;
        } else {
            areaAdmitNumbers = areaAdmitNumberRepository.findAllByName(provinceName, accessPrCodes,
                    tenantId);  //查询出省top3
            for (int i = 0; i < areaAdmitNumbers.size(); i++) {  //在history-data表中有的专业，在area-admit-number中也一定有
                String temp = new String();
                temp = areaAdmitNumbers.get(i).getPrCode();
                prCodes.add(temp);
            }
        }

        if (null != studentsPrCodes && studentsPrCodes.size() != 0) {  //如果考生选择了专业

            List<String> prCodesTemps = sortStudentsPrCodes(studentsPrCodes, provinceName, tenantId);  //排序

            if (prCodesTemps.size() != 0 && prCodesTemps != null) {
                if (prCodes != null) {
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
                return accessGraduate(prCodesTemps, tenantId);
            }
        }
        return accessGraduate(prCodes, tenantId);
    }

    /*
    组装各个专业热门就业地区top1,录取批次对应人数,男女分布情况,毕业薪资超越情况
     */
    private List<ViewAreaTop3Profession> accessEmploymentArea(List<String> prCodes, int score, String classCategory
            , String provinceName, String tenantId) {
        if (null == prCodes) {
            return null;
        }
        List<ProfessionDetails> professionDetailsList = new ArrayList<>();
        int totalProfession = professionDetailsRepository.getAllPrCode(tenantId);//获取本校所有专业数目

        for (int i = 0; i < prCodes.size(); i++) {

            //组装各个专业热门就业地区top1
            ProfessionDetails professionDetails = new ProfessionDetails();
            professionDetails = professionDetailsRepository.findByPrCode(prCodes.get(i), tenantId);
            List<ContractedArea> contractedAreas = contractedAreaRepository.findAllByProfession(professionDetails.getId());
            if (contractedAreas == null || contractedAreas.size() == 0) {
                professionDetails.setEmploymentArea(null);
            } else {
                contractedAreas.sort((x, y) -> Double.compare(x.getProportion(), y.getProportion()));
                Collections.reverse(contractedAreas);
                ContractedArea contractedArea = contractedAreas.get(0);
                professionDetails.setEmploymentArea(contractedArea.getName());
                professionDetails.setEmploymentAreaProportion(contractedArea.getProportion());
            }

            //组装此专业在此地区的招生人数
            professionDetails.setAreaAdmitNumber(areaAdmitNumberRepository.findNumberByPrCode(prCodes.get(i), tenantId));

            //组装录取批次map
            String prCode = new String();
            prCode = prCodes.get(i);
            Map<String, Integer> admissionMap = new HashMap<>();
            List<String> admissions = new ArrayList<>();
            admissions = historyDataJpaRepository.findAllBatch(prCodes.get(i), provinceName,
                    classCategory, tenantId);
            for (int j = 0; j < admissions.size(); j++) {
                int panNumber = historyDataJpaRepository.findNUmber(prCode, provinceName,
                        classCategory, tenantId, admissions.get(j));
                admissionMap.put(admissions.get(j), panNumber);
            }
            professionDetails.setAdmissionMap(admissionMap);

            //组装男女比例
            String boyProportion = professionDetails.getBoyProportion();
            String girlProportion = professionDetails.getGirlProportion();
            professionDetails.setGirlProportion(girlProportion);
            professionDetails.setBoyProportion(boyProportion);

            //组装毕业薪资超越情况
            double salary = professionDetails.getAverageSalary();

            log.info(totalProfession + "");
            int surpassingProfessionNumber = professionDetailsRepository.getSurpassingProfessionNumber(salary,
                    tenantId);
            log.info(surpassingProfessionNumber + "");
            if (totalProfession == 0 || surpassingProfessionNumber == 0) {
                professionDetails.setSurpassingProfessionNumber(0.00);
            } else {
                BigDecimal b = new BigDecimal((double) surpassingProfessionNumber / totalProfession);
                professionDetails.setSurpassingProfessionNumber(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }

            professionDetailsList.add(professionDetails);
        }

        List<ViewAreaTop3Profession> viewAreaTop3Professions = accessViewAreaTop3Profession(professionDetailsList);
        return viewAreaTop3Professions;
    }

    /*
    排序studentsPrCodes
     */
    private List<String> sortStudentsPrCodes(List<String> studentsPrCodes, String areaName, String tenantId) throws Exception {

        List<AreaAdmitNumber> areaAdmitNumberStudents = areaAdmitNumberRepository.findAllByName(areaName, studentsPrCodes,
                tenantId);

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
        for (int m = 0; m < prCodes.size(); m++) {
            log.info("eeff");
            log.info(prCodes.get(m) + "ccded");
        }
        return prCodes;
    }

    /*
    组装专业top1的毕业生去向
     */
    public List<ViewGraduateArea> accessGraduate(List<String> prCodes, String tenantId) {
        List<String> allAreas = graduateStudentRepository.getAllArea();
        if (allAreas == null || allAreas.size() == 0) {
            return null;
        }
        if (prCodes == null || prCodes.size() == 0) {
            return null;
        }
        String prCode = prCodes.get(0);
        int totalNumber = graduateStudentRepository.getAllCount(prCode, tenantId);

        List<ViewGraduateArea> viewGraduateAreas = new ArrayList<>();

        allAreas.forEach(area -> {
            ViewGraduateArea viewGraduateArea = new ViewGraduateArea();
            viewGraduateArea.setArea(area);
            int areaNumber = graduateStudentRepository.getAllAreaCount(prCode, area, tenantId);
            if (areaNumber == 0) {
                viewGraduateArea.setProportion(0);
                viewGraduateAreas.add(viewGraduateArea);
            } else {
                BigDecimal b = new BigDecimal((double) areaNumber / totalNumber);
                viewGraduateArea.setProportion(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
        });
        return viewGraduateAreas;
    }

    /*
    组装考生在本校相同科类中的位次
     */
    private double accessRank(String area, int score, String classCategory, String tenantId) {
        int countTotal = lastYearScoreRepository.getCountNumber(classCategory, tenantId, area);
        int surpassingNumber = lastYearScoreRepository.getsurpassingNumber(classCategory, tenantId,
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
    public int accessAlumni(String mobilePhone, String tenantId) {
        String url = MessageFormat.format(crmUrl + "/api/v1/cust/getSchoolByMobilePhone?mobilePhone={0}&tenantId={1}",
                mobilePhone, tenantId);
        log.info("getFreeSeatUrl:{}", url);
        String school = restTemplate.getForObject(url, String.class);
        log.info(school);
        if (school == null) {
            return 0;
        }
        int number = graduateStudentRepository.getAlumni(school, tenantId);
        return number;
    }

    /*
    组装考生基本信息
     */
    public CandidateInformation accessCandidateInformation(String mobilePhone, String tenantId) {
        String url = MessageFormat.format(crmUrl + "/api/v1/cust/getInformationByMobilePhone?mobilePhone={0}&tenantId={1}",
                mobilePhone, tenantId);
        log.info("getFreeSeatUrl:{}", url);
        CandidateInformation candidateInformation = restTemplate.getForObject(url, CandidateInformation.class);
        return candidateInformation;
    }

    /*
   组装考生姓名
    */
    public String accessCandidateName(String mobilePhone) {
        String url = MessageFormat.format(crmUrl + "/api/v1/cust/getInformationByMobilePhone?mobilePhone={0}&tenantId={1}",
                mobilePhone, AuthenticationUtil.getTenantId());
        CandidateInformation candidateInformation = restTemplate.getForObject(url, CandidateInformation.class);
        String name = candidateInformation.getName();
        return name;
    }

    /*
    组装视图返回
     */
    private List<ViewAreaTop3Profession> accessViewAreaTop3Profession
    (List<ProfessionDetails> professionDetailsList) {
        List<ViewAreaTop3Profession> viewAreaTop3Professions = new ArrayList<>();

        for (int i = 0; i < professionDetailsList.size(); i++) {
            ProfessionDetails professionDetails = professionDetailsList.get(i);
            ViewAreaTop3Profession viewAreaTop3Profession = new ViewAreaTop3Profession();
            viewAreaTop3Profession.setEmploymentRate(professionDetails.getEmploymentRate());
            viewAreaTop3Profession.setEmploymentSalary(professionDetails.getAverageSalary());
            viewAreaTop3Profession.setPrCode(professionDetails.getPrCode());
            viewAreaTop3Profession.setPrTitle(professionDetails.getPrTitle());
            viewAreaTop3Profession.setEmploymentArea(professionDetails.getEmploymentArea());
            viewAreaTop3Profession.setAdmissionMap(professionDetails.getAdmissionMap());
            viewAreaTop3Profession.setBoyProportion(professionDetails.getBoyProportion());
            viewAreaTop3Profession.setGirlProportion(professionDetails.getGirlProportion());
            viewAreaTop3Profession.setSurpassingProfessionNumber(professionDetails.getSurpassingProfessionNumber());
            viewAreaTop3Profession.setEmploymentAreaProportion(professionDetails.getEmploymentAreaProportion());
            viewAreaTop3Profession.setAreaAdmitNumber(professionDetails.getAreaAdmitNumber());
            viewAreaTop3Professions.add(viewAreaTop3Profession);
        }
        return viewAreaTop3Professions;
    }

}

