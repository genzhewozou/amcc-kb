package com.nroad.amcc.kb.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HastSpeechDeletedEvent extends HastSpeechEvent {

    private static final String EVENT_NAME = "crm.hast.speech.deleted";

    @JsonProperty
    private final String id;

    @JsonCreator
    public HastSpeechDeletedEvent(@JsonProperty("id") String id) {
        super(id);
        this.id = id;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "HastSpeechDeletedEvent{" +
                "id='" + id + '\'' +
                '}';
    }
}
