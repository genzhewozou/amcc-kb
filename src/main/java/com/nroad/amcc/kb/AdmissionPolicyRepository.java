package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface AdmissionPolicyRepository extends JpaRepository<AdmissionPolicy, String> {

    @Query(value = "select title from kb_admission_policy where title like %:keyWord%",nativeQuery = true)
    List<String> findByKeyWord(@Param("keyWord") String keyWord);

    @Query(value = "select * from kb_admission_policy where title =?1",nativeQuery = true)
    AdmissionPolicy findUrlByTitle(String title);
}
