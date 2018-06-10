package com.nroad.amcc.kb.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nroad.amcc.kb.HeatSpeech;

public class HastSpeechCreatedEvent extends HastSpeechEvent {

    private static final String EVENT_NAME = "crm.hast.speech.created";

    @JsonProperty
    private final HeatSpeech heatSpeech;

    @JsonCreator
    public HastSpeechCreatedEvent(@JsonProperty("heatSpeech") HeatSpeech heatSpeech) {
        super(heatSpeech.getId());
        this.heatSpeech = heatSpeech;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    public HeatSpeech getHeatSpeech() {
        return heatSpeech;
    }

    @Override
    public String toString() {
        return "HastSpeechCreatedEvent{" +
                "heatSpeech=" + heatSpeech +
                '}';
    }
}
