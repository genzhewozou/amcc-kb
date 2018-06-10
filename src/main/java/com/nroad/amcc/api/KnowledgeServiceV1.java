package com.nroad.amcc.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nroad.amcc.PlatformError;
import com.nroad.amcc.kb.KnowledgeBase;
import com.nroad.amcc.kb.KnowledgeBaseException;
import com.nroad.amcc.support.jpa.KnowledgeJpaRepository;

@Service
public class KnowledgeServiceV1 {

    @Autowired
    private KnowledgeJpaRepository knowledgeJpaRepository;

    public KnowledgeBase creatKnowledgeColumn(String pid,String kName,String organizationId){
        KnowledgeBase knowledgeBase = knowledgeJpaRepository.findOne(pid);
        if(knowledgeBase==null){
            throw new KnowledgeBaseException(PlatformError.PARENTID_IS_EXIST);
        }
        KnowledgeBase kb = new KnowledgeBase();
        kb.setParentId(pid);
        kb.setKnowledegName(kName);
        kb.setOrganizationId(organizationId);
        return knowledgeJpaRepository.saveAndFlush(kb);
    }

    public KnowledgeBase updateKnowledgeBaseColumn(String id,String kName) {
        if (StringUtils.isEmpty(id)){
            throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASEID_CAN_NOT_BE_NULL);
        }
        KnowledgeBase knowledgeBase=knowledgeJpaRepository.findOne(id);
        if(knowledgeBase==null){
            throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASE_IS_NULL);
        }
        knowledgeBase.setKnowledegName(kName);
        return knowledgeJpaRepository.saveAndFlush(knowledgeBase);
    }

    public void deleteKnowledgeBaseColumn(String id) {
        List<KnowledgeBase> knowledgeBases = knowledgeJpaRepository.findAll();
        if (StringUtils.isEmpty(id)){
            throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASEID_CAN_NOT_BE_NULL);
        }
        deleteChildren(id,knowledgeBases);
        knowledgeJpaRepository.delete(id);
    }

    public void deleteChildren(String id,List<KnowledgeBase> knowledgeBases){
        for (KnowledgeBase kb : knowledgeBases){
            if(id.equals(kb.getParentId())){
               deleteChildren(kb.getId(),knowledgeBases);
                knowledgeJpaRepository.delete(kb.getId());
            }
        }
    }

    public KnowledgeBase  findKnowledgeBase(String id){
        if (StringUtils.isEmpty(id)){
            throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASEID_CAN_NOT_BE_NULL);
        }
        KnowledgeBase knowledgeBase = knowledgeJpaRepository.findOne(id);
        if(knowledgeBase==null){
            throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASE_IS_NULL);
    }
    return knowledgeBase;
    }

    public KnowledgeBase  deleteKnowledgeBase(String id){
        if (org.springframework.util.StringUtils.isEmpty(id)){
            new KnowledgeBaseException(PlatformError.KNOWLEDGEBASEID_CAN_NOT_BE_NULL);
        }
        KnowledgeBase knowledgeBase = knowledgeJpaRepository.findOne(id);
        if(knowledgeBase==null){
            throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASE_IS_NULL);
        }
        knowledgeBase.setKnowledegContent(null);
        return knowledgeJpaRepository.saveAndFlush(knowledgeBase);
    }

    public KnowledgeBase creatKnowledge(String id,String content){
        if (StringUtils.isEmpty(id)){
            throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASEID_CAN_NOT_BE_NULL);
        }
        KnowledgeBase knowledgeBase = knowledgeJpaRepository.getOne(id);
        if(knowledgeBase==null){
            throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASE_IS_NULL);
        }
        knowledgeBase.setId(id);
        knowledgeBase.setKnowledegContent(content);
        return knowledgeJpaRepository.saveAndFlush(knowledgeBase);
    }
    
    public KnowledgeBase creatKnowledge(String pId, String organizationId, String kName, String kBContent){
        
        if(!knowledgeJpaRepository.exists(pId)) {
        	 throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASE_IS_NULL);
        }
        KnowledgeBase instance=new KnowledgeBase();
        instance.setKnowledegContent(kBContent);
        instance.setKnowledegName(kName);
        instance.setOrganizationId(organizationId);
        instance.setParentId(pId);
        
        return knowledgeJpaRepository.saveAndFlush(instance);
        
    }

    public KnowledgeBase updateKnowledgeBase(String id,String kBContent) {
        if (StringUtils.isEmpty(id)){
            throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASEID_CAN_NOT_BE_NULL);
        }
        KnowledgeBase knowledgeBase=knowledgeJpaRepository.findOne(id);
        if(knowledgeBase==null){
            throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASE_IS_NULL);
        }
        knowledgeBase.setKnowledegContent(kBContent);
        return knowledgeJpaRepository.saveAndFlush(knowledgeBase);
    }

    public  List<KnowledgeBase> findByKnowledegcontentLike(String organizationId,String content){
        if (org.springframework.util.StringUtils.isEmpty(content)){
            new KnowledgeBaseException(PlatformError.FIND_WORDS_CAN_NOT_BE_NULL);
        }
        return knowledgeJpaRepository.findByKnowledegContentLike(organizationId,content);
    }

    public List<KnowledgeBase>  findByOrganizationId(String organizationId){
        return knowledgeJpaRepository.findByOrganizationId(organizationId);
    }

	public List<KnowledgeBase> queryArticles(String pid) {
		return knowledgeJpaRepository.findAllByparentId(pid);
	}
}
