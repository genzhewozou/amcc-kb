package com.nroad.amcc.support.View;


import java.util.Map;

public class ViewAreaTop3Profession {

    public String prTitle;

    public String prCode;

    public double employmentSalary;  //就业薪资

    public double employmentRate;  //就业率

    public String employmentArea;  //top1就业地区

    public double employmentAreaProportion;  //就业最热地区比例

    public Map<String, Integer> admissionMap;

    public String boyProportion;  //男生比例

    public String girlProportion;  //女生比例

    public double surpassingProfessionNumber;  //就业薪资超越同校其他专业情况

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

    public String getEmploymentArea() {
        return employmentArea;
    }

    public void setEmploymentArea(String employmentArea) {
        this.employmentArea = employmentArea;
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
