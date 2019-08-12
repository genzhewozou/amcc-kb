package com.nroad.amcc.support.jpa;

import com.nroad.amcc.kb.HistoryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;


@Component
public interface HistoryDataJpaRepository extends JpaRepository<HistoryData, String>, JpaSpecificationExecutor<HistoryData> {


}
