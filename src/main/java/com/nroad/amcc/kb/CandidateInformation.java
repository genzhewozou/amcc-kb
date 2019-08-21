package com.nroad.amcc.kb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "`kb_candidate_information`")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidateInformation {

    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Id
    @JsonProperty
    public String id;

    @Column
    @JsonProperty
    public String name;

    @Column
    @JsonProperty
    public int chineseScore;

    @Column
    @JsonProperty
    public int mathScore;

    @Column
    @JsonProperty
    public int englishScore;

    @Column
    @JsonProperty
    public int comprehensiveScore;

    @Column
    @JsonProperty
    public String highSchool;

    @Column
    @JsonProperty
    public int alumniNumber;  //历届高中校友人数

    @Column
    @JsonProperty
    public String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChineseScore() {
        return chineseScore;
    }

    public void setChineseScore(int chineseScore) {
        this.chineseScore = chineseScore;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(int englishScore) {
        this.englishScore = englishScore;
    }

    public int getComprehensiveScore() {
        return comprehensiveScore;
    }

    public void setComprehensiveScore(int comprehensiveScore) {
        this.comprehensiveScore = comprehensiveScore;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public int getAlumniNumber() {
        return alumniNumber;
    }

    public void setAlumniNumber(int alumniNumber) {
        this.alumniNumber = alumniNumber;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
