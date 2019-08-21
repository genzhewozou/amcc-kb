package com.nroad.amcc.api;

import com.nroad.amcc.PlatformError;
import com.nroad.amcc.PlatformException;
import com.nroad.amcc.kb.*;
import com.nroad.amcc.support.View.ViewAreaTop3Profession;
import com.nroad.amcc.support.View.ViewContractedArea;
import com.nroad.amcc.support.View.ViewHistoryData;
import com.nroad.amcc.support.View.ViewProfessionDetails;
import com.nroad.amcc.support.configuration.AuthenticationUtil;
import com.nroad.amcc.support.jpa.HistoryDataJpaRepository;
import com.nroad.amcc.support.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import javax.swing.text.View;
import java.io.IOException;
import java.math.BigDecimal;
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

    public Page<HistoryData> findProfession(Pageable pageable, String area, String classCategory, String prTitle, String prCode,
                                            Integer studentScore, String tenantId) {
        Page<HistoryData> historyDataList = historyDataJpaRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("tenantId").as(String.class), tenantId));
            if (area != null && StringUtils.isNotEmpty(area)) {
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

    private List<ViewHistoryData> transformHistoryDataToView(List<HistoryData> historyDataList, List<ViewHistoryData> viewHistoryDataList) {
        for (int i = 0; i < historyDataList.size(); i++) {
            HistoryData historyData = historyDataList.get(i);

            ViewHistoryData viewHistoryData = new ViewHistoryData();

            viewHistoryData.setId(historyData.getId());
            viewHistoryData.setArea(historyData.getArea());
            viewHistoryData.setClassCategory(historyData.getClassCategory());
            viewHistoryData.setPrTitle(historyData.getPrTitle());
            viewHistoryData.setPrCode(historyData.getPrCode());
            viewHistoryData.setMinScoreBefore3(historyData.getMinScoreBefore3());
            viewHistoryData.setMinScoreBefore2(historyData.getMinScoreBefore2());
            viewHistoryData.setMinScoreBefore1(historyData.getMinScoreBefore1());
            viewHistoryData.setForecastScore(historyData.getForecastScore());
            viewHistoryData.setAdmissionBatch(historyData.getAdmissionBatch());
            viewHistoryData.setPlanNumber(historyData.getPlanNumber());
            viewHistoryDataList.add(viewHistoryData);
        }
        return viewHistoryDataList;
    }

    public List<String> findAllProfession(String tenantId) {
        return historyDataJpaRepository.findAllProfession(tenantId);
    }

    public UserPortrait generateUserPortrait(String provinceName, int score, String classCategory, List<String> prCodes) throws Exception {
        UserPortrait userPortrait = new UserPortrait();

        userPortrait.setViewBestProfessions(accessBestProfession(provinceName, prCodes, score, classCategory));
        userPortrait.setLastYearScoreMap(accessLastYearScore(provinceName));
        userPortrait.setAreaMap(BestProfessionArea(provinceName, prCodes, score, classCategory));
        userPortrait.setRank(accessRank(provinceName, score, classCategory));
        return userPortrait;
    }

    /*
    查询去年本校招生的各个科类所有分数对应人数
     */
    public Map<Integer, Integer> accessLastYearScore(String area) {

        List<Integer> scoreList = lastYearScoreRepository.getAllScore(AuthenticationUtil.getTenantId(), area);
        if (scoreList != null && scoreList.size() != 0) {
            Map<Integer, Integer> lastYearScoreMap = new HashMap<>();
            classCategoryList.forEach(classCategory -> {
                scoreList.forEach(score ->
                {
                    lastYearScoreMap.put(score, lastYearScoreRepository.getCountScoreByClassCategory(classCategory, score,
                            AuthenticationUtil.getTenantId(), area));
                });
            });
            return lastYearScoreMap;
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
        if (null != studentsPrCodes && studentsPrCodes.size() != 0) {  //如果考生选择了专业

            List<String> accessLists = available(studentsPrCodes, score, provinceName, classCategory);

            if (accessLists.size() == 0 || accessLists == null) {
                return accessEmploymentArea(prCodes, score, classCategory, provinceName);
            } else {
                List<String> prCodesTemps = sortStudentsPrCodes(accessLists, provinceName);  //排序
                if (prCodesTemps.size() != 0 && prCodesTemps != null) {
                    if (prCodesTemps.size() != 3) {
                        for (int i = 0; i < prCodes.size(); i++) {
                            if (prCodesTemps.contains(prCodes.get(i))) {
                                continue;
                            } else {
                                prCodesTemps.add(prCodes.get(i));
                            }
                            if (prCodesTemps.size() == 3) {
                                break;
                            }
                        }
                    }
                    return accessEmploymentArea(prCodesTemps, score, classCategory, provinceName);
                }
            }
        }
        return accessEmploymentArea(prCodes, score, classCategory, provinceName);
    }

    /*
  组装Top1的毕业生去向
   */
    private Map<String, Integer> BestProfessionArea(String provinceName, List<String> studentsPrCodes, int score, String classCategory) throws Exception {

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
            areaTop3Profession = areaTop3ProfessionRepository.findByPrCode(prCodes.get(i));

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
//            return null;
        } else {
            for (int i = 0; i < areaAdmitNumberStudents.size(); i++) {
                String temp = new String();
                temp = areaAdmitNumberStudents.get(i).getPrCode();
                prCodes.add(temp);
            }
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
        return accessLists;
    }

    /*
    组装专业top1的毕业生去向
     */
    public Map<String, Integer> accessGraduate(List<String> prCodes) {
        List<String> allAreas = graduateStudentRepository.getAllArea();
        if (allAreas.size() == 0 || allAreas == null) {
            return null;
        }
        if (prCodes.size() == 0 || prCodes == null) {
            return null;
        }
        String prCode = prCodes.get(0);
        Map<String, Integer> areaMap = new HashMap<>();
        allAreas.forEach(area -> {
            areaMap.put(area, graduateStudentRepository.getAllAreaCount(prCode, area, AuthenticationUtil.getTenantId()));
        });
        return areaMap;
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
            viewAreaTop3Professions.add(viewAreaTop3Profession);
        }
        return viewAreaTop3Professions;
    }

}

