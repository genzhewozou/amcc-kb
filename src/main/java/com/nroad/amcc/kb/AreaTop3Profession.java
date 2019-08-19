package com.nroad.amcc.kb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @ManyToOne
    @JsonIgnore
    public Area area;

    @Column
    @JsonProperty
    public double scoreRank;  //排名位次

    @Column
    @JsonProperty
    public double employmentSalary;  //就业薪资

    @Column
    @JsonProperty
    public double employmentRate;  //就业率

    @Column
    @JsonProperty
    public String classCategory;  //科类

    @Column
    @JsonProperty
    public int admitNumber;  //录取人数

    @Column
    @JsonProperty
    public int professionRank;  //专业位次

    @Column
    @JsonProperty
    public String tenantId;  //专业位次

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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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

    public int getProfessionRank() {
        return professionRank;
    }

    public void setProfessionRank(int professionRank) {
        this.professionRank = professionRank;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
