package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AreaTop3ProfessionRepository extends JpaRepository<AreaTop3Profession, String> {

    @Query(value = "select * from kb_area_top_three where area_id=?1",nativeQuery = true)
    List<AreaTop3Profession> findAllByArea(String id);
}
