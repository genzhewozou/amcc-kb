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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    AreaRepository areaRepository;

    @Autowired
    StudentsScoreRepository studentsScoreRepository;

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
                professionDetails.setAverageSalary(list.get(i + count + 14));
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
                contractedArea.setProportion(list.get(i + count + 2));
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
                predicates.add(criteriaBuilder.equal(root.get("prTitle").as(String.class), prTitle));
            }
            if (prCode != null) {
                predicates.add(criteriaBuilder.equal(root.get("prCode").as(String.class), prCode));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

//        List<ViewHistoryData> viewHistoryDataList = new ArrayList<>();
//        viewHistoryDataList = transformHistoryDataToView(historyDataList, viewHistoryDataList);
//        Page<ViewHistoryData> viewHistoryDataPage = new PageImpl<ViewHistoryData>(viewHistoryDataList, pageable, (long) (pageable.getOffset() + viewHistoryDataList.size()));
        return historyDataList;
    }

    public ViewProfessionDetails findProfessionDetails(String prCode) {
        ProfessionDetails professionDetails = professionDetailsRepository.findByPrCode(prCode, AuthenticationUtil.getTenantId());
        ViewProfessionDetails viewProfessionDetails = new ViewProfessionDetails();

        List<ContractedArea> contractedAreas = contractedAreaRepository.findAllByProfession(professionDetails.getId());
        List<ViewContractedArea> viewContractedAreas = new ArrayList<>();
        for (int i = 0; i < contractedAreas.size(); i++) {
            ViewContractedArea viewContractedArea = new ViewContractedArea();
            viewContractedArea.setName(contractedAreas.get(i).getName());
            viewContractedArea.setProportion(contractedAreas.get(i).getProportion());
            viewContractedAreas.add(viewContractedArea);
        }
        viewContractedAreas.sort((x, y) -> Float.compare(Float.parseFloat(x.getProportion()), Float.parseFloat(y.getProportion())));
        Collections.reverse(viewContractedAreas);

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


    public Page<ViewHistoryData> professionalRecommend(Pageable pageable, String score) {
        List<HistoryData> historyDataList = historyDataJpaRepository.professionRecommend(score);
        List<ViewHistoryData> viewHistoryDataList = new ArrayList<>();
        viewHistoryDataList = transformHistoryDataToView(historyDataList, viewHistoryDataList);
        Page<ViewHistoryData> viewHistoryDataPage = new PageImpl<ViewHistoryData>(viewHistoryDataList, pageable, (long) (pageable.getOffset() + viewHistoryDataList.size()));
        return viewHistoryDataPage;
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


    public UserPortrait generateUserPortrait(String areaName, int score, String classCategory, List<String> prCodes) {
        UserPortrait userPortrait = new UserPortrait();
        userPortrait.setViewAreaTop3Professions(accessAreaTop3Profession(areaName, score, classCategory));
        userPortrait.setStudentsCheckProfessions(accessStudentsProfession(prCodes, score, classCategory));
        return userPortrait;
    }

    private List<ViewAreaTop3Profession> accessAreaTop3Profession(String areaName, int score, String classCategory) {  //组装区域Top3
        Area area = areaRepository.findByName(areaName);
        List<AreaTop3Profession> areaTop3ProfessionsTemp = area.getAreaTop3Professions();
        List<AreaTop3Profession> areaTop3Professions = new ArrayList<>();
        for (int i = 0; i < areaTop3ProfessionsTemp.size(); i++) {
            if (areaTop3ProfessionsTemp.get(i).getProfessionRank() != 0) {
                areaTop3Professions.add(areaTop3ProfessionsTemp.get(i));
            }
        }

        areaTop3Professions = accessScoreRank(areaTop3Professions, score, classCategory);
        List<ViewAreaTop3Profession> viewAreaTop3Professions = accessViewAreaTop3Profession(areaTop3Professions);
        viewAreaTop3Professions.sort((x, y) -> Double.compare(x.getCompeteIndex(), y.getCompeteIndex()));
        Collections.reverse(viewAreaTop3Professions);
        return viewAreaTop3Professions;
    }

    private List<ViewAreaTop3Profession> accessStudentsProfession(List<String> prCodes, int score, String classCategory) {  //组装考生自选专业
        List<AreaTop3Profession> areaTop3Professions = new ArrayList<>();

        for (int i = 0; i < prCodes.size(); i++) {
            AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
            areaTop3Profession = areaTop3ProfessionRepository.findByPrCodeAndRank(prCodes.get(i), 0);
            areaTop3Professions.add(areaTop3Profession);
        }
        areaTop3Professions = accessScoreRank(areaTop3Professions, score, classCategory);
        List<ViewAreaTop3Profession> viewAreaTop3Professions = accessViewAreaTop3Profession(areaTop3Professions);
        viewAreaTop3Professions.sort((x, y) -> Double.compare(x.getCompeteIndex(), y.getCompeteIndex()));
        Collections.reverse(viewAreaTop3Professions);
        return viewAreaTop3Professions;
    }

    private List<AreaTop3Profession> accessScoreRank(List<AreaTop3Profession> areaTop3Professions, int score, String classCategory) {

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

    private List<ViewAreaTop3Profession> accessViewAreaTop3Profession(List<AreaTop3Profession> areaTop3Professions) {
        List<ViewAreaTop3Profession> viewAreaTop3Professions = new ArrayList<>();

        for (int i = 0; i < areaTop3Professions.size(); i++) {
            AreaTop3Profession areaTop3Profession = areaTop3Professions.get(i);
            ViewAreaTop3Profession viewAreaTop3Profession = new ViewAreaTop3Profession();
            viewAreaTop3Profession.setCompeteIndex(areaTop3Profession.getScoreRank());
            viewAreaTop3Profession.setEmploymentRate(areaTop3Profession.getEmploymentRate());
            viewAreaTop3Profession.setEmploymentSalary(areaTop3Profession.getEmploymentSalary());
            viewAreaTop3Profession.setPrCode(areaTop3Profession.getPrCode());
            viewAreaTop3Profession.setPrTitle(areaTop3Profession.getPrTitle());
            viewAreaTop3Professions.add(viewAreaTop3Profession);
        }
        return viewAreaTop3Professions;
    }

}
