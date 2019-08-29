package com.nroad.amcc.api;

import com.nroad.amcc.PlatformError;
import com.nroad.amcc.PlatformException;
import com.nroad.amcc.kb.PasswordMapping;
import com.nroad.amcc.kb.PasswordMappingRepository;
import com.nroad.amcc.support.utils.AESUtils;
import com.nroad.amcc.support.utils.PasswordUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/IVR")
public class IVRControllerV1 {


    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private IVRServiceV1 ivrServiceV1;

    @Autowired
    private PasswordMappingRepository passwordMappingRepository;

    @PostMapping(value = "/professionTop3")
    @ApiOperation(value = "返回3个推荐专业", notes = "根据用户所在区域名字，分数以及意向专业")
    public String generateUserPortrait(@RequestParam(value = "provinceName") String provinceName,
                                       @RequestParam(value = "score") int score,
                                       @RequestParam(value = "classCategory") String classCategory,
                                       @RequestParam(value = "mobilePhone") String mobilePhone,
                                       @RequestParam(value = "tenantId") String tenantId) {
        log.info("provinceName={},score={},classCategory={},mobilePhone={},tenantId={}", provinceName, score, classCategory, mobilePhone, tenantId);

        return ivrServiceV1.accessBestProfession(provinceName, score, classCategory, mobilePhone, tenantId);
    }

    @PostMapping(value = "/generateKey")
    @ApiOperation(value = "生成加密串", notes = "根据用户所在区域名字，分数以及意向专业")
    public String encryption(@RequestParam(value = "provinceName") String provinceName,
                             @RequestParam(value = "score") int score,
                             @RequestParam(value = "classCategory") String classCategory,
                             @RequestParam(value = "mobilePhone") String mobilePhone,
                             @RequestParam(value = "tenantId") String tenantId) {
        log.info("provinceName={},score={},classCategory={},mobilePhone={},tenantId={}", provinceName, score, classCategory, mobilePhone, tenantId);

        if (!ivrServiceV1.matchProfession(provinceName, score, classCategory, mobilePhone, tenantId)) {
            throw PlatformException.of(PlatformError.NOT_MATCH_PROFESSION);
        }

        String password = "sde@5f98H*^hsff%dfs$r344&df8543*er";

        String toBeEncrypted = new String(mobilePhone + "," + provinceName + "," +
                classCategory + "," + score + "," + tenantId);

        log.info(toBeEncrypted + "");
        String encryptionString = AESUtils.encrypt(toBeEncrypted, password);  //加密后的串

        String eightPassword = new PasswordUtil().generateEightPassword();

        PasswordMapping passwordMapping = new PasswordMapping();
        passwordMapping.setId(UUID.randomUUID().toString());
        passwordMapping.setEncryptedString(encryptionString);
        passwordMapping.setMapString(eightPassword);
        passwordMappingRepository.saveAndFlush(passwordMapping);

        log.info(encryptionString + "");

        return eightPassword;
    }
}
