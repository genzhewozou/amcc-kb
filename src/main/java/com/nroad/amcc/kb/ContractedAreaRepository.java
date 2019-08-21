package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ContractedAreaRepository extends JpaRepository<ContractedArea, String> {

    @Query(value = "select * from kb_contracted_area where profession_details_id=?1 ORDER BY proportion desc  limit 3",nativeQuery = true)
    List<ContractedArea> findAllByProfession(String id);

}
