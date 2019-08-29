package com.nroad.amcc.support.jpa;

import com.nroad.amcc.kb.HistoryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface HistoryDataJpaRepository extends JpaRepository<HistoryData, String>, JpaSpecificationExecutor<HistoryData> {

    @Query(value = "select DISTINCT pr_title FROM kb_profession_details where tenant_id=?1", nativeQuery = true)
    List<String> findAllProfession(String tenantId);

    @Query(value = "select pr_code from kb_history_data where forecast_score <=?1 and tenant_id=?2 and area like ?3 and class_category=?4", nativeQuery = true)
    List<String> findAllProfessionByScore(int score, String tenantId, String area, String classCategory);

    @Query(value = "SELECT DISTINCT forecast_score from kb_history_data WHERE pr_code=?1 AND area=?2 AND tenant_id=?3 and class_category=?4", nativeQuery = true)
    List<Integer> findScore(String prCode, String area, String tenantId, String classCategory);

    @Query(value = "select admission_batch FROM kb_history_data where pr_code = ?1 and area=?2 and class_category=?3 and tenant_id=?4", nativeQuery = true)
    List<String> findAllBatch(String prCode, String area, String classCategory, String tenantId);

    @Query(value = "select plan_number FROM kb_history_data where pr_code = ?1 and area=?2 and class_category=?3 and tenant_id=?4 and admission_batch=?5", nativeQuery = true)
    int findNUmber(String prCode, String area, String classCategory, String tenantId, String admission);

}
