package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface ProfessionDetailsRepository extends JpaRepository<ProfessionDetails, String> {

    @Query(value = "select * FROM kb_profession_details where pr_code=?1 and tenant_id=?2", nativeQuery = true)
    ProfessionDetails findByPrCode(String prCode, String tenantId);

    @Query(value = "select count(DISTINCT pr_code) from kb_profession_details where tenant_id=?1", nativeQuery = true)
    int getAllPrCode(String tenantId);

    @Query(value = "select count(id) FROM kb_profession_details where average_salary<=?1 and tenant_id=?2", nativeQuery = true)
    int getSurpassingProfessionNumber(double salary, String tenantId);
}
