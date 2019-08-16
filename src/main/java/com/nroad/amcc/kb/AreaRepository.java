package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface AreaRepository extends JpaRepository<Area, String> {

    @Query(value = "select * from kb_area where name=?1",nativeQuery = true)
    Area findByName(String name);
}
