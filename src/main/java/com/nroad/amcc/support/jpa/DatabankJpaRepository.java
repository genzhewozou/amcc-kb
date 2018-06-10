package com.nroad.amcc.support.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nroad.amcc.kb.Databank;

public interface DatabankJpaRepository extends JpaRepository<Databank, String>{

	public List<Databank> findAllByOrgId(String orgId);
	
	public List<Databank> findAllByOrgIdAndPid(String orgId,String pid);	
	
	@Query("select t from Databank t where t.orgId=?1 and t.content like %?2%")
	public List<Databank> findAllByOrgIdAndContent(String orgId,String word);
	
}
