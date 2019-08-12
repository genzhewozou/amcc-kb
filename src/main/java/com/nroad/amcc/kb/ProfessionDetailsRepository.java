package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface ProfessionDetailsRepository extends JpaRepository<ProfessionDetails, String> {

    @Query(value = "select * FROM kb_profession_details where pr_code=?1 and tenant_id=?2", nativeQuery = true)
    ProfessionDetails findByPrCode(String prCode, String tenantId);
}
