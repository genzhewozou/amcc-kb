package com.nroad.amcc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nroad.amcc.kb.HeatSpeech;
import com.nroad.amcc.support.configuration.AuthenticationUtil;


@RestController
@RequestMapping("/api/v1")
public class HeatSpeechControllerV1 {
    private final Logger logger = LoggerFactory.getLogger(HeatSpeechControllerV1.class);

    private final HeatSpeechServiceV1 heatSpeechService;
    
    @Autowired
    public HeatSpeechControllerV1(HeatSpeechServiceV1 heatSpeechService) {
        Assert.notNull(heatSpeechService, "HeatSpeechServiceV1 can not be null");
        this.heatSpeechService = heatSpeechService;
    }

    /**
     * Create HeatSpeech
     *
     * @param name HeatSpeech of name, Can not be blank
     * @return HeatSpeech
     */
    @PostMapping("/heat/speech")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public HeatSpeech createHeatSpeech(@RequestParam("name") String name) {
        Assert.hasText(name, "HeatSpeech name can not be blank");

        logger.info("Create heat speech request, HeatSpeech name : {}", name);

        HeatSpeech heatSpeech = heatSpeechService.createHeatSpeech(new HeatSpeech(name,AuthenticationUtil.getTenantId()));

        logger.info("Create heat speech response, HeatSpeech info : {}", heatSpeech.toString());

        return heatSpeech;
    }

    /**
     * Create HeatSpeech
     *
     * @param id HeatSpeech of id, Can not be blank
     */
    @DeleteMapping("/heat/speech/{id:.+}")
    @PreAuthorize("hasRole('TENANT_ADMIN')")
    public void deleteHeatSpeech(@PathVariable("id") String id) {
        Assert.hasText(id, "Script id can not be blank");

        logger.info("Delete heat speech request, Script id : {}", id);

        heatSpeechService.deleteHeatSpeech(id);

        logger.info("Create heat speech response success");
    }
}
