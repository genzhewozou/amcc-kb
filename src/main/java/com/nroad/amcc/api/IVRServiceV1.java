package com.nroad.amcc.api;

import com.nroad.amcc.PlatformError;
import com.nroad.amcc.PlatformException;
import com.nroad.amcc.kb.*;
import com.nroad.amcc.support.jpa.HistoryDataJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IVRServiceV1 {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private HistoryDataJpaRepository historyDataJpaRepository;

    @Autowired
    AreaAdmitNumberRepository areaAdmitNumberRepository;

    @Value("${amcc.crm.service}")
    private String crmUrl;

    @Autowired
    private RestTemplate restTemplate;


    /**
     *  获取最佳的3个专业
     * @param provinceName
     * @param score
     * @param classCategory
     * @param mobilePhone
     * @param tenantId
     * @return
     */
    public String accessBestProfession(String provinceName, int score, String classCategory, String mobilePhone, String tenantId) {

        String province=provinceName+"%";

        List<String> accessPrCodes = historyDataJpaRepository.findAllProfessionByScore((score + 10),
                tenantId, province, classCategory);
        if(accessPrCodes.isEmpty()){
            throw PlatformException.of(PlatformError.NOT_MATCH_PROFESSION);
        }
        List<AreaAdmitNumber> areaAdmitNumbers = areaAdmitNumberRepository.findAllByName(province, accessPrCodes, tenantId);  //查询出省top3
        return areaAdmitNumbers.stream().map(it->it.getPrTitle()).collect(Collectors.joining(","));

    }

    /**
     * 是否有匹配专业
     * @param provinceName
     * @param score
     * @param classCategory
     * @param mobilePhone
     * @param tenantId
     * @return
     */
    public boolean matchProfession(String provinceName, int score, String classCategory, String mobilePhone, String tenantId){
        String province=provinceName+"%";
        List<String> accessPrCodes = historyDataJpaRepository.findAllProfessionByScore((score + 10), tenantId, province, classCategory);
        if(accessPrCodes.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

}