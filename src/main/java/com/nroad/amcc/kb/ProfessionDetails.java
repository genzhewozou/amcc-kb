package com.nroad.amcc.kb;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public String classCategory;  //招生科类

    @Column
    public String professionCategory;  //专业类别

    @Column
    public double averageSalary;  //平均薪资

    @Column
    public String professionDirection;  //专业方向

    @Column(columnDefinition = "varchar(500)")
    public String professionCourses;  //专业课程

    @Column(columnDefinition = "varchar(500)")
    public String careerDirection;  //就业方向

    @Column(columnDefinition = "varchar(500)")
    public String teacherPower;  //师资力量

    @Column
    public String boyProportion;  //男生比例

    @Column
    public String girlProportion;  //女生比例

    @Column(columnDefinition = "varchar(500)")
    public String testableCertificate;  //可考证书

    @Column(columnDefinition = "varchar(500)")
    public String cooperativeInstitution;  //合作机构

    @OneToMany(mappedBy = "professionDetails")
    private List<ContractedArea> contractedAreas = new ArrayList<>();

    @Column
    @JsonProperty
    public double employmentRate;  //就业率

    @JsonProperty
    @Transient
    public double scoreRank;  //排名位次

    @Transient
    @Column
    public String employmentArea;  //就业最热地区

    @JsonProperty
    @Transient
    public double employmentAreaProportion;  //就业最热地区比例

    @JsonProperty
    @Transient
    public Map<String, Integer> admissionMap;

    @JsonProperty
    @Transient
    public double surpassingProfessionNumber;  //就业薪资超越同校其他专业情况

    @JsonProperty
    @Transient
    public int areaAdmitNumber;  //区域专业录取人数

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

    public String getClassCategory() {
        return classCategory;
    }

    public void setClassCategory(String classCategory) {
        this.classCategory = classCategory;
    }

    public String getProfessionCategory() {
        return professionCategory;
    }

    public void setProfessionCategory(String professionCategory) {
        this.professionCategory = professionCategory;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public String getProfessionDirection() {
        return professionDirection;
    }

    public void setProfessionDirection(String professionDirection) {
        this.professionDirection = professionDirection;
    }

    public String getProfessionCourses() {
        return professionCourses;
    }

    public void setProfessionCourses(String professionCourses) {
        this.professionCourses = professionCourses;
    }

    public String getCareerDirection() {
        return careerDirection;
    }

    public void setCareerDirection(String careerDirection) {
        this.careerDirection = careerDirection;
    }

    public String getTeacherPower() {
        return teacherPower;
    }

    public void setTeacherPower(String teacherPower) {
        this.teacherPower = teacherPower;
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

    public String getTestableCertificate() {
        return testableCertificate;
    }

    public void setTestableCertificate(String testableCertificate) {
        this.testableCertificate = testableCertificate;
    }

    public String getCooperativeInstitution() {
        return cooperativeInstitution;
    }

    public void setCooperativeInstitution(String cooperativeInstitution) {
        this.cooperativeInstitution = cooperativeInstitution;
    }

    public List<ContractedArea> getContractedAreas() {
        return contractedAreas;
    }

    public void setContractedAreas(List<ContractedArea> contractedAreas) {
        this.contractedAreas = contractedAreas;
    }

    public double getEmploymentRate() {
        return employmentRate;
    }

    public void setEmploymentRate(double employmentRate) {
        this.employmentRate = employmentRate;
    }

    public double getScoreRank() {
        return scoreRank;
    }

    public void setScoreRank(double scoreRank) {
        this.scoreRank = scoreRank;
    }

    public String getEmploymentArea() {
        return employmentArea;
    }

    public void setEmploymentArea(String employmentArea) {
        this.employmentArea = employmentArea;
    }

    public double getEmploymentAreaProportion() {
        return employmentAreaProportion;
    }

    public void setEmploymentAreaProportion(double employmentAreaProportion) {
        this.employmentAreaProportion = employmentAreaProportion;
    }

    public Map<String, Integer> getAdmissionMap() {
        return admissionMap;
    }

    public void setAdmissionMap(Map<String, Integer> admissionMap) {
        this.admissionMap = admissionMap;
    }

    public double getSurpassingProfessionNumber() {
        return surpassingProfessionNumber;
    }

    public void setSurpassingProfessionNumber(double surpassingProfessionNumber) {
        this.surpassingProfessionNumber = surpassingProfessionNumber;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public int getAreaAdmitNumber() {
        return areaAdmitNumber;
    }

    public void setAreaAdmitNumber(int areaAdmitNumber) {
        this.areaAdmitNumber = areaAdmitNumber;
    }
}
