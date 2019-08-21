package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LastYearScoreRepository extends JpaRepository<LastYearScore, String> {

    @Query(value = "select count(id) from kb_last_year_score where class_category=?1 and score=?2 and tenant_id =?3 and area=?4", nativeQuery = true)
    Integer getCountScoreByClassCategory(String classCategory, int score, String tenantId, String area);

    @Query(value = "select DISTINCT score from kb_last_year_score where tenant_id =?1 and area=?2 ORDER BY score", nativeQuery = true)
    List<Integer> getAllScore(String tenantId, String area);

    @Query(value = "select count(DISTINCT score) from kb_last_year_score where class_category=?1 and tenant_id =?2 and area=?3", nativeQuery = true)
    Integer getCountNumber(String classCategory, String tenantId, String area);

    @Query(value = "select count(DISTINCT score) from kb_last_year_score where class_category=?1 and tenant_id =?2 and area=?3 and score<=?4", nativeQuery = true)
    Integer getsurpassingNumber(String classCategory, String tenantId, String area, int score);
}
