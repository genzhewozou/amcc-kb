package com.nroad.amcc.api;

import com.nroad.amcc.kb.KnowledgeBase;
import com.nroad.amcc.support.jpa.KnowledgeJpaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeService {

    @Autowired
    private KnowledgeJpaRepository knowledgeJpaRepository;

    public KnowledgeBase creatKnowledgeColumn(String pid,String kName,String organizationId){
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        knowledgeBase.setParentId(pid);
        knowledgeBase.setKnowledegName(kName);
        knowledgeBase.setOrganizationId(organizationId);
        return knowledgeJpaRepository.saveAndFlush(knowledgeBase);
    }

    public KnowledgeBase updateKnowledgeBaseColumn(String id,String kName) {

        KnowledgeBase knowledgeBase=knowledgeJpaRepository.findOne(id);
        if(knowledgeBase==null){
            return null;
        }
        knowledgeBase.setKnowledegName(kName);
        return knowledgeJpaRepository.saveAndFlush(knowledgeBase);
    }

    //删除知识库目录
    public void deleteKnowledgeBaseColumn(String id) {
        List<KnowledgeBase> knowledgeBases = knowledgeJpaRepository.findAll();
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
    KnowledgeBase knowledgeBase = knowledgeJpaRepository.findOne(id);
    return knowledgeBase;
    }

    public KnowledgeBase  deleteKnowledgeBase(String id){
        KnowledgeBase knowledgeBase = knowledgeJpaRepository.findOne(id);
        knowledgeBase.setKnowledegContent(null);
        return knowledgeJpaRepository.saveAndFlush(knowledgeBase);
    }

    public KnowledgeBase creatKnowledge(String id,String content){
        KnowledgeBase knowledgeBase = knowledgeJpaRepository.getOne(id);
        knowledgeBase.setId(id);
        knowledgeBase.setKnowledegContent(content);
        return knowledgeJpaRepository.saveAndFlush(knowledgeBase);
    }

    public KnowledgeBase updateKnowledgeBase(String id,String kBContent) {

        KnowledgeBase knowledgeBase=knowledgeJpaRepository.findOne(id);
        if(knowledgeBase==null){
            return null;
        }
        knowledgeBase.setKnowledegContent(kBContent);
        return knowledgeJpaRepository.saveAndFlush(knowledgeBase);
    }

    public  List<KnowledgeBase> findByKnowledegcontentLike(String organizationId,String content){
        return knowledgeJpaRepository.findByKnowledegContentLike(organizationId,content);
    }

    public List<KnowledgeBase>  findByOrganizationId(String organizationId){
        return knowledgeJpaRepository.findByOrganizationId(organizationId);
    }
}
