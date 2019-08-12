package com.nroad.amcc.kb;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "`kb_history_data`")
public class HistoryData {

    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Id
    public String id;

    @Column
    public String area;

    @Column
    public String classCategory;

    @Column
    public String prTitle;

    @Column
    public String prCode;

    @Column
    public String minScoreBefore1;

    @Column
    public String minScoreBefore2;

    @Column
    public String minScoreBefore3;

    @Column
    public String forecastScore;

    @Column
    public String admissionBatch;  //录取批次

    @Column
    public String planNumber;

    @Column
    public String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getClassCategory() {
        return classCategory;
    }

    public void setClassCategory(String classCategory) {
        this.classCategory = classCategory;
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

    public String getMinScoreBefore1() {
        return minScoreBefore1;
    }

    public void setMinScoreBefore1(String minScoreBefore1) {
        this.minScoreBefore1 = minScoreBefore1;
    }

    public String getMinScoreBefore2() {
        return minScoreBefore2;
    }

    public void setMinScoreBefore2(String minScoreBefore2) {
        this.minScoreBefore2 = minScoreBefore2;
    }

    public String getMinScoreBefore3() {
        return minScoreBefore3;
    }

    public void setMinScoreBefore3(String minScoreBefore3) {
        this.minScoreBefore3 = minScoreBefore3;
    }

    public String getForecastScore() {
        return forecastScore;
    }

    public void setForecastScore(String forecastScore) {
        this.forecastScore = forecastScore;
    }

    public String getAdmissionBatch() {
        return admissionBatch;
    }

    public void setAdmissionBatch(String admissionBatch) {
        this.admissionBatch = admissionBatch;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
