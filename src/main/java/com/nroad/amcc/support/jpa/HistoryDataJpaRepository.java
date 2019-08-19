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

}
