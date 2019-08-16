package com.nroad.amcc.api;

import com.nroad.amcc.PlatformError;
import com.nroad.amcc.PlatformException;
import com.nroad.amcc.kb.*;
import com.nroad.amcc.support.View.ViewContractedArea;
import com.nroad.amcc.support.View.ViewHistoryData;
import com.nroad.amcc.support.View.ViewProfessionDetails;
import com.nroad.amcc.support.configuration.AuthenticationUtil;
import com.nroad.amcc.support.jpa.HistoryDataJpaRepository;
import com.nroad.amcc.support.utils.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.io.IOException;
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
                historyData.setForecastScore(list.get(i + count + 7));
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

//    public Page<ViewHistoryData> findProfession(Pageable pageable, String area, String classCategory, String prTitle, String prCode,
//                                                String tenantId) {
//        List<HistoryData> historyDataList = historyDataJpaRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            predicates.add(criteriaBuilder.equal(root.get("tenantId").as(String.class), tenantId));
//            if (area != null) {
//                predicates.add(criteriaBuilder.equal(root.get("area").as(String.class), area));
//            }
//            if (classCategory != null) {
//                predicates.add(criteriaBuilder.equal(root.get("classCategory").as(String.class), classCategory));
//            }
//            if (prTitle != null) {
//                predicates.add(criteriaBuilder.equal(root.get("prTitle").as(String.class), prTitle));
//            }
//            if (prCode != null) {
//                predicates.add(criteriaBuilder.equal(root.get("prCode").as(String.class), prCode));
//            }
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        });
//
//        List<ViewHistoryData> viewHistoryDataList = new ArrayList<>();
//
//        viewHistoryDataList = transformHistoryDataToView(historyDataList, viewHistoryDataList);
//        Page<ViewHistoryData> viewHistoryDataPage = new PageImpl<ViewHistoryData>(viewHistoryDataList, pageable, (long) (pageable.getOffset() + viewHistoryDataList.size()));
//        return viewHistoryDataPage;
//    }

    public Page<HistoryData> findProfession(Pageable pageable, String area, String classCategory, String prTitle, String prCode,
                                                String tenantId) {
        Page<HistoryData> historyDataList = historyDataJpaRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("tenantId").as(String.class), tenantId));
            if (area != null) {
                predicates.add(criteriaBuilder.equal(root.get("area").as(String.class), area));
            }
            if (classCategory != null) {
                predicates.add(criteriaBuilder.equal(root.get("classCategory").as(String.class), classCategory));
            }
            if (prTitle != null) {
                predicates.add(criteriaBuilder.equal(root.get("prTitle").as(String.class), prTitle));
            }
            if (prCode != null) {
                predicates.add(criteriaBuilder.equal(root.get("prCode").as(String.class), prCode));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        },pageable);

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
}
