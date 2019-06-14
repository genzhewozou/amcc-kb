package com.nroad.amcc.support.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nroad.amcc.kb.Databank;

public interface DatabankJpaRepository extends JpaRepository<Databank, String>{

	public List<Databank> findAllByTenantId(String tenantId);
	
	public List<Databank> findAllByTenantIdAndPid(String tenantId,String pid);	
	
	@Query("select t from Databank t where t.tenantId=?1 and (t.name like %?2% or t.content like %?2%)")
	public List<Databank> findAllByTenantIdAndContent(String orgId,String word);
	
}
