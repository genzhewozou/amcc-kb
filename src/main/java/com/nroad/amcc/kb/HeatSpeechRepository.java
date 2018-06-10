package com.nroad.amcc.kb;

import java.util.List;

public interface HeatSpeechRepository {

    /**
     * Find HeatSpeech List
     *
     * @return List<HeatSpeech>
     */
    List<HeatSpeech> findHeatSpeechList();

    /**
     * Find HeatSpeech
     *
     * @param id HeatSpeech of id, Can not be blank
     * @return HeatSpeech
     */
    HeatSpeech findHeatSpeech(String id);

    /**
     * Create HeatSpeech
     *
     * @param heatSpeech HeatSpeech of name, Can not be blank
     * @return HeatSpeech
     */
    HeatSpeech createHeatSpeech(HeatSpeech heatSpeech);


    /**
     * Create HeatSpeech
     *
     * @param id HeatSpeech of id, Can not be blank
     */
    void deleteHeatSpeech(String id);
}
