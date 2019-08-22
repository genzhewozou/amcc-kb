package com.nroad.amcc.kb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "`kb_area_top_three`")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaTop3Profession {

    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Id
    @JsonProperty
    public String id;

    @Column
    @JsonProperty
    public String prTitle;

    @Column
    @JsonProperty
    public String prCode;

    @JsonProperty
    public double scoreRank;  //排名位次

    @Column
    @JsonProperty
    public double employmentSalary;  //就业薪资

    @Column
    @JsonProperty
    public double employmentRate;  //就业率

    public String employmentArea;  //就业最热地区

    @JsonProperty
    @Transient
    public double employmentAreaProportion;  //就业最热地区比例

    @Column
    @JsonProperty
    public String classCategory;  //科类

    @Column
    @JsonProperty
    public int admitNumber;  //录取人数

    @JsonProperty
    @Transient
    public Map<String, Integer> admissionMap;

    @JsonProperty
    @Transient
    public String boyProportion;  //男生比例

    @JsonProperty
    @Transient
    public String girlProportion;  //女生比例

    @JsonProperty
    @Transient
    public double surpassingProfessionNumber;  //就业薪资超越同校其他专业情况

    @Column
    @JsonProperty
    public String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrTitle() {
        return prTitle;
    }

    public void setPrTitle(String prTitle) {
        this.prTitle = prTitle;
    }

    public String getPrCode() {
        return prCode;
    }

    public void setPrCode(String prCode) {
        this.prCode = prCode;
    }

    public double getScoreRank() {
        return scoreRank;
    }

    public void setScoreRank(double scoreRank) {
        this.scoreRank = scoreRank;
    }

    public double getEmploymentSalary() {
        return employmentSalary;
    }

    public void setEmploymentSalary(double employmentSalary) {
        this.employmentSalary = employmentSalary;
    }

    public String getEmploymentArea() {
        return employmentArea;
    }

    public void setEmploymentArea(String employmentArea) {
        this.employmentArea = employmentArea;
    }

    public double getEmploymentRate() {
        return employmentRate;
    }

    public void setEmploymentRate(double employmentRate) {
        this.employmentRate = employmentRate;
    }

    public String getClassCategory() {
        return classCategory;
    }

    public void setClassCategory(String classCategory) {
        this.classCategory = classCategory;
    }

    public int getAdmitNumber() {
        return admitNumber;
    }

    public void setAdmitNumber(int admitNumber) {
        this.admitNumber = admitNumber;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Map<String, Integer> getAdmissionMap() {
        return admissionMap;
    }

    public void setAdmissionMap(Map<String, Integer> admissionMap) {
        this.admissionMap = admissionMap;
    }

    public String getBoyProportion() {
        return boyProportion;
    }

    public void setBoyProportion(String boyProportion) {
        this.boyProportion = boyProportion;
    }

    public String getGirlProportion() {
        return girlProportion;
    }

    public void setGirlProportion(String girlProportion) {
        this.girlProportion = girlProportion;
    }

    public double getSurpassingProfessionNumber() {
        return surpassingProfessionNumber;
    }

    public void setSurpassingProfessionNumber(double surpassingProfessionNumber) {
        this.surpassingProfessionNumber = surpassingProfessionNumber;
    }

    public double getEmploymentAreaProportion() {
        return employmentAreaProportion;
    }

    public void setEmploymentAreaProportion(double employmentAreaProportion) {
        this.employmentAreaProportion = employmentAreaProportion;
    }
}
