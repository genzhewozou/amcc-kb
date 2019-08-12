package com.nroad.amcc.kb;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "`kb_profession_details`")
public class ProfessionDetails {

    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Id
    public String id;

    @Column
    public String prTitle;

    @Column
    public String prCode;

    @Column
    public String studyYears;  //修业年限

    @Column
    public String grantDegree;  //授予学位

    @Column
    public String professionCategory;  //专业类别

    @Column
    public String employmentSalaryBefore1;  //1年前就业薪资

    @Column
    public String employmentRateBefore1;  //1年前就业率

    @Column
    public String employmentSalaryBefore2;

    @Column
    public String employmentRateBefore2;

    @Column
    public String employmentSalaryBefore3;

    @Column
    public String employmentRateBefore3;

    @Column
    public String careerDirection;  //就业方向

    @Column
    public String professionCourses;  //专业课程

    @Column
    public String constructionSituation;  //建设情况

    @Column
    public String boyProportion;  //男生比例

    @Column
    public String girlProportion;

    @Column
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

    public String getStudyYears() {
        return studyYears;
    }

    public void setStudyYears(String studyYears) {
        this.studyYears = studyYears;
    }

    public String getGrantDegree() {
        return grantDegree;
    }

    public void setGrantDegree(String grantDegree) {
        this.grantDegree = grantDegree;
    }

    public String getProfessionCategory() {
        return professionCategory;
    }

    public void setProfessionCategory(String professionCategory) {
        this.professionCategory = professionCategory;
    }

    public String getEmploymentSalaryBefore1() {
        return employmentSalaryBefore1;
    }

    public void setEmploymentSalaryBefore1(String employmentSalaryBefore1) {
        this.employmentSalaryBefore1 = employmentSalaryBefore1;
    }

    public String getEmploymentRateBefore1() {
        return employmentRateBefore1;
    }

    public void setEmploymentRateBefore1(String employmentRateBefore1) {
        this.employmentRateBefore1 = employmentRateBefore1;
    }

    public String getEmploymentSalaryBefore2() {
        return employmentSalaryBefore2;
    }

    public void setEmploymentSalaryBefore2(String employmentSalaryBefore2) {
        this.employmentSalaryBefore2 = employmentSalaryBefore2;
    }

    public String getEmploymentRateBefore2() {
        return employmentRateBefore2;
    }

    public void setEmploymentRateBefore2(String employmentRateBefore2) {
        this.employmentRateBefore2 = employmentRateBefore2;
    }

    public String getEmploymentSalaryBefore3() {
        return employmentSalaryBefore3;
    }

    public void setEmploymentSalaryBefore3(String employmentSalaryBefore3) {
        this.employmentSalaryBefore3 = employmentSalaryBefore3;
    }

    public String getEmploymentRateBefore3() {
        return employmentRateBefore3;
    }

    public void setEmploymentRateBefore3(String employmentRateBefore3) {
        this.employmentRateBefore3 = employmentRateBefore3;
    }

    public String getCareerDirection() {
        return careerDirection;
    }

    public void setCareerDirection(String careerDirection) {
        this.careerDirection = careerDirection;
    }

    public String getProfessionCourses() {
        return professionCourses;
    }

    public void setProfessionCourses(String professionCourses) {
        this.professionCourses = professionCourses;
    }

    public String getConstructionSituation() {
        return constructionSituation;
    }

    public void setConstructionSituation(String constructionSituation) {
        this.constructionSituation = constructionSituation;
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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
