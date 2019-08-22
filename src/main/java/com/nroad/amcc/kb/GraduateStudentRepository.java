package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GraduateStudentRepository extends JpaRepository<GraduateStudent, String> {

    @Query(value = "SELECT DISTINCT graduate_area from kb_graduate_student", nativeQuery = true)
    List<String> getAllArea();


    @Query(value = "SELECT count(id)  from kb_graduate_student where pr_code=?1 and graduate_area=?2 and tenant_id=?3", nativeQuery = true)
    int getAllAreaCount(String prCode, String area, String tenantId);

    @Query(value = "SELECT count(id) from kb_graduate_student where high_school=?1 and tenant_id=?2", nativeQuery = true)
    int getAlumni(String school, String tenantId);

}
