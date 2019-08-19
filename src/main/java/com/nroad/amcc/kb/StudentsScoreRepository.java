package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentsScoreRepository extends JpaRepository<StudentsScore, String> {

    @Query(value = "select count(id) from kb_students_score where class_category=?1 AND pr_code=?2 AND tenant_id=?3", nativeQuery = true)
    int getTotalNumber(String classCategory, String prCode, String tenantId);

    @Query(value = "select count(id) from kb_students_score where class_category=?1 AND pr_code=?2 AND tenant_id=?3 and score<=?4", nativeQuery = true)
    int getSurpassingNumber(String classCategory, String prCode, String tenantId, int score);
}
