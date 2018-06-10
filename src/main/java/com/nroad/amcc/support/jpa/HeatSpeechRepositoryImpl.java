package com.nroad.amcc.support.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.nroad.amcc.kb.HeatSpeech;
import com.nroad.amcc.kb.HeatSpeechRepository;

import java.util.List;

@Component
public class HeatSpeechRepositoryImpl implements HeatSpeechRepository {
    private final HeatSpeechJpaRepository heatSpeechJpaRepository;

    @Autowired
    public HeatSpeechRepositoryImpl(HeatSpeechJpaRepository heatSpeechJpaRepository) {
        Assert.notNull(heatSpeechJpaRepository, "HeatSpeechJpaRepository can not be null");
        this.heatSpeechJpaRepository = heatSpeechJpaRepository;
    }

    @Override
    public List<HeatSpeech> findHeatSpeechList() {
        return heatSpeechJpaRepository.findAll();
    }

    @Override
    public HeatSpeech findHeatSpeech(String id) {
        return heatSpeechJpaRepository.findOne(id);
    }

    @Override
    public HeatSpeech createHeatSpeech(HeatSpeech heatSpeech) {
        return heatSpeechJpaRepository.save(heatSpeech);
    }

    @Override
    public void deleteHeatSpeech(String id) {
        heatSpeechJpaRepository.delete(id);
    }
}
