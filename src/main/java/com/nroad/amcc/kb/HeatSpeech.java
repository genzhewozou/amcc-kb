package com.nroad.amcc.kb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "`kb_heat_speech`")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeatSpeech {

    @Id
    @JsonProperty
    private String id;

    @Column
    @JsonProperty
    private String name;

    @Column
    @JsonProperty
    private OffsetDateTime createdAt;
    
    @Column
    @JsonProperty
    private String tenantId;

    public HeatSpeech() {

    }

    public HeatSpeech(String name,String tenantId) {
        this.name = name;
        this.createdAt = OffsetDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.tenantId=tenantId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "HeatSpeech{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
