package com.nroad.amcc.api;

import com.nroad.amcc.PlatformError;
import com.nroad.amcc.PlatformException;
import com.nroad.amcc.kb.HistoryData;
import com.nroad.amcc.support.View.ViewHistoryData;
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
    public List<String> getAllProfession(){
        return professionDataServiceV1.findAllProfession(AuthenticationUtil.getTenantId());
    }

    @GetMapping(value = "/queryProfession")
    @ApiOperation(value = "查询专业列表", notes = "根据area、classCategory、prTitle、prCode")
    public Page<HistoryData> findProfession(@RequestParam(value = "area", required = false) String area,
                                            @RequestParam(value = "classCategory", required = false) String classCategory,
                                            @RequestParam(value = "profession", required = false) String profession,
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

        return professionDataServiceV1.findProfession(new PageRequest(page, size), area, classCategory, prTitle, prCode, AuthenticationUtil.getTenantId());
    }

    @GetMapping(value = "/query/professionDetails")
    @ApiOperation(value = "查询专业详情", notes = "根据prCode")
    public ViewProfessionDetails getProfessionDetails(@RequestParam("prCode") String prCode) {
        return professionDataServiceV1.findProfessionDetails(prCode);
    }

    @GetMapping(value = "/query/recommend")
    @ApiOperation(value = "专业推荐", notes = "根据预测分数")
    public Page<ViewHistoryData> professionalRecommend(@RequestParam("source") String score, @RequestParam("page") int page,
                                                       @RequestParam("size") int size) {
        return professionDataServiceV1.professionalRecommend(new PageRequest(page, size),score);
    }

    @GetMapping(value = "/query/area")
    @ApiOperation(value = "查询所选市Top3专业", notes = "根据区域名字")
    public void selectArea(@RequestParam("area") String area) {

    }
}
