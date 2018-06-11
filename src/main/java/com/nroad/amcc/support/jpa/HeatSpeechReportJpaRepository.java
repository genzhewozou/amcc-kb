package com.nroad.amcc.support.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nroad.amcc.kb.HeatSpeechReport;

public interface HeatSpeechReportJpaRepository extends JpaRepository<HeatSpeechReport, String>{

	@Query("select t from HeatSpeechReport t where t.hotWord=?1 and t.dtime=?2 and t.orgId=?3")
	List<HeatSpeechReport> findAllByHotWordAndDtime(String hotWord,Date dtime,String orgId);
	
	@Query("select t.hotWord,sum(t.queryCount) from HeatSpeechReport t where t.orgId=?1 and t.dtime>=?2 and t.dtime<?3 group by t.hotWord order by 2 desc")
	List<Object> findHotWords(String orgId,Date sDate,Date eDate);
	
}
