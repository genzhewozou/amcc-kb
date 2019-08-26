package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface AdmissionPolicyRepository extends JpaRepository<AdmissionPolicy, String> {

    @Query(value = "select title from kb_admission_policy where title like %?1% and area=?2", nativeQuery = true)
    List<String> findByKeyWord(String keyWord,String area);

    @Query(value = "select * from kb_admission_policy where title =?1", nativeQuery = true)
    AdmissionPolicy findUrlByTitle(String title);
}
