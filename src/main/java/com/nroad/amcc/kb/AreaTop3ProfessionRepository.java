package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AreaTop3ProfessionRepository extends JpaRepository<AreaTop3Profession, String> {

    @Query(value = "select * from kb_area_top_three where area_id=?1",nativeQuery = true)
    List<AreaTop3Profession> findAllByArea(String id);

    @Query(value = "SELECT * from kb_area_top_three where pr_code=?1 AND profession_rank=?2",nativeQuery = true)
    AreaTop3Profession findByPrCodeAndRank(String prCode,int professionRank);
}
