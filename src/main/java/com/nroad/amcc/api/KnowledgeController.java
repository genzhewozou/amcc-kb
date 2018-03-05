package com.nroad.amcc.api;

import com.nroad.amcc.kb.KnowledgeBase;
import com.nroad.amcc.utils.TreeUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    private final Logger logger = LoggerFactory.getLogger(KnowledgeController.class);

    @Autowired
    private KnowledgeService knowledgeService;

    @ApiOperation("查询知识库目录")
    @ResponseBody
    @GetMapping(value = "/findColumns")
    public KnowledgeBase findAllColumn( @RequestParam("organizationId")String organizationId){
        logger.info("Create KnowledgeBase Request : {}");
        List<KnowledgeBase> knowledgeBases= knowledgeService.findByOrganizationId(organizationId);
        KnowledgeBase kb = new KnowledgeBase();
        for (KnowledgeBase k:knowledgeBases){
            k.setKnowledegLevel(null);
            k.setKnowledegContent(null);
            if("0".equals(k.getParentId())){
               kb=k;//kb为顶层id
            }
        }
        TreeUtils.createTree(knowledgeBases, kb, "id", "parentId", "children");
        if (kb.getId()==null||kb.getId().equals("")){
            return null;
        }
        return kb;
    }

    @ApiOperation("创建知识库目录")
    @ResponseBody
    @PostMapping("/createColumns")
    public KnowledgeBase createKBColumn(@RequestParam("parentId")String pId,
                                            @RequestParam("knowledegName")String kName,
                                        @RequestParam("organizationId")String organizationId) {
        logger.info("Create KnowledgeBaseColumn Request : {}",pId ,kName,organizationId);
        KnowledgeBase created = knowledgeService.creatKnowledgeColumn(pId,kName,organizationId);
        logger.info("Create KnowledgeBaseColumn Success, Response : {}", created);
        return created;
    }

    @ApiOperation("修改知识库目录")
    @ResponseBody
    @PostMapping("/updateColumns")
    public KnowledgeBase updateKBColumn(@RequestParam("id")String id,
                                        @RequestParam("knowledegName")String kName) {
        logger.info("Create KnowledgeBaseColumn Request : {}",id,kName);
        KnowledgeBase kb = knowledgeService.updateKnowledgeBaseColumn(id,kName);
        logger.info("Create KnowledgeBaseColumn Success, Response : {}", kb);
        return kb;
    }

    @ApiOperation("删除知识库目录")
    @ResponseBody
    @PostMapping("/deleteColumns")
    public void deleteKBColumn(@RequestParam("id")String id) {
        logger.info("Delete KnowledgeBaseColumn Request : {}",id);
        knowledgeService.deleteKnowledgeBaseColumn(id);
        logger.info("Delete KnowledgeBaseColumn Success");
    }

    @ApiOperation("创建知识库")
    @ResponseBody
    @PostMapping("/createArticle")
    public KnowledgeBase createKB(@RequestParam("id")String id,
                                        @RequestParam("knowledegContent")String kBContent) {
        logger.info("Create KnowledgeBase Request : {}",id ,kBContent);
        KnowledgeBase created = knowledgeService.creatKnowledge(id,kBContent);
        logger.info("Create KnowledgeBase Success, Response : {}", created);
        return created;
    }

    @ApiOperation("更新知识库")
    @ResponseBody
    @PostMapping("/updateArticle")
    public KnowledgeBase updateKB(@RequestParam("id")String id,
                                  @RequestParam("knowledegContent")String kBContent) {
        logger.info("Update KnowledgeBase Request : {}",id,kBContent);
        KnowledgeBase updated = knowledgeService.updateKnowledgeBase(id,kBContent);
        logger.info("Update KnowledgeBase Success, Response : {}", updated);
        return updated;
    }

    @ApiOperation("通过id查询知识库")
    @ResponseBody
    @PostMapping("/findArticleById")
    public KnowledgeBase findKB(@RequestParam("id")String id) {
        logger.info("Find KnowledgeBase Request : {}",id);
        KnowledgeBase kb = knowledgeService.findKnowledgeBase(id);
        logger.info("Find KnowledgeBase Success, Response : {}", kb);
        return kb;
    }

    @ApiOperation("删除知识库文章")
    @ResponseBody
    @PostMapping("/deleteArticle")
    public KnowledgeBase deleteKB(@RequestParam("id")String id) {
        logger.info("Delete KnowledgeBase Request : {}",id);
        KnowledgeBase kb = knowledgeService.deleteKnowledgeBase(id);
        logger.info("Delete KnowledgeBase Success, Response : {}", kb);
        return kb;
    }

    @ApiOperation("通过内容查找文章")
    @ResponseBody
    @PostMapping("/findByContent")
    public List<KnowledgeBase> findByContent(@RequestParam("organizationId") String organizationId,@RequestParam("knowledegContent") String content){
        logger.info("Search KnowledgeBase Request : {}",organizationId,content);
        System.out.println("-----------------------------------"+content);
        return knowledgeService.findByKnowledegcontentLike(organizationId,content);
    }
}
