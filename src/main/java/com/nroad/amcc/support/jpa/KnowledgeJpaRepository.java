package com.nroad.amcc.support.jpa;

import com.nroad.amcc.kb.KnowledgeBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KnowledgeJpaRepository extends JpaRepository<KnowledgeBase, String> {

	@Query(value = "select * from knowledge_base  where organization_id = :organizationId AND knowledeg_content like CONCAT('%',:content,'%')", nativeQuery = true)
	List<KnowledgeBase> findByKnowledegContentLike(@Param("organizationId") String organizationId,
			@Param("content") String content);

	List<KnowledgeBase> findByOrganizationId(String organizationId);

	List<KnowledgeBase> findAllByparentId(String pid);
}
