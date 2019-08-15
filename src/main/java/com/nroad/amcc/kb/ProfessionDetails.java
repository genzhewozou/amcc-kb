package com.nroad.amcc.kb;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    public String averageSalary;  //平均薪资

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

    public String getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(String averageSalary) {
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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
