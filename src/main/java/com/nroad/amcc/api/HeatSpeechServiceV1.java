package com.nroad.amcc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.nroad.amcc.kb.HeatSpeech;
import com.nroad.amcc.kb.HeatSpeechRepository;
import com.nroad.amcc.kb.event.HastSpeechCreatedEvent;
import com.nroad.amcc.kb.event.HastSpeechDeletedEvent;
import com.nroad.amcc.support.stream.KbService;

@Service
public class HeatSpeechServiceV1 {
    private final HeatSpeechRepository heatSpeechRepository;
    private final KbService kbService;

    @Autowired
    public HeatSpeechServiceV1(HeatSpeechRepository heatSpeechRepository, KbService kbService) {
        Assert.notNull(heatSpeechRepository, "HeatSpeechRepository can not be null");
        Assert.notNull(kbService, "KbService can not be null");
        this.heatSpeechRepository = heatSpeechRepository;
        this.kbService = kbService;
    }
    
    /**
     * Create HeatSpeech
     *
     * @param heatSpeech HeatSpeech of info, Can not be blank
     * @return HeatSpeech
     */
    public HeatSpeech createHeatSpeech(HeatSpeech heatSpeech) {
        Assert.notNull(heatSpeech, "HeatSpeech id can not be null");

        if (heatSpeechRepository.findHeatSpeechList().stream().anyMatch(s -> s.getName().equals(heatSpeech.getName()))) {
            throw new RuntimeException("");
        }

        kbService.sendMessage(new HastSpeechCreatedEvent(heatSpeech));

        return heatSpeechRepository.createHeatSpeech(heatSpeech);
    }

    /**
     * Create HeatSpeech
     *
     * @param id HeatSpeech of id, Can not be blank
     */
    public void deleteHeatSpeech(String id) {
        Assert.hasText(id, "HeatSpeech id can not be blank");

        heatSpeechRepository.deleteHeatSpeech(id);

        kbService.sendMessage(new HastSpeechDeletedEvent(id));
    }
}
