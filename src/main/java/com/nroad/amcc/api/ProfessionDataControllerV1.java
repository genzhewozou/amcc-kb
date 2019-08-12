package com.nroad.amcc.api;

import com.nroad.amcc.PlatformError;
import com.nroad.amcc.PlatformException;
import com.nroad.amcc.kb.ViewHistoryData;
import com.nroad.amcc.kb.ViewProfessionDetails;
import com.nroad.amcc.support.configuration.AuthenticationUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping(value = "/queryProfession")
    @ApiOperation(value = "查询专业列表", notes = "根据area、classCategory、prTitle、prCode")
    public List<ViewHistoryData> findProfession(@RequestParam(value = "area", required = false) String area,
                                                @RequestParam(value = "classCategory", required = false) String classCategory,
                                                @RequestParam(value = "profession", required = false) String profession) {
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

        return professionDataServiceV1.findProfession(area, classCategory, prTitle, prCode, AuthenticationUtil.getTenantId());
    }

    @GetMapping(value = "/query/professionDetails")
    @ApiOperation(value = "查询专业详情", notes = "根据prCode")
    public ViewProfessionDetails getProfessionDetails(@RequestParam("prCode") String prCode) {
        return professionDataServiceV1.findProfessionDetails(prCode);
    }

}
