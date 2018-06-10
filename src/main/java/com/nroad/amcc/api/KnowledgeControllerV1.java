package com.nroad.amcc.api;

import com.nroad.amcc.PlatformError;
import com.nroad.amcc.kb.KnowledgeBase;
import com.nroad.amcc.kb.KnowledgeBaseException;
import com.nroad.amcc.support.utils.TreeUtils;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class KnowledgeControllerV1 {
	private final Logger logger = LoggerFactory.getLogger(KnowledgeControllerV1.class);

	@Autowired
	private KnowledgeServiceV1 knowledgeService;

	@ApiOperation("知识库查询目录")
	@ResponseBody
	@GetMapping(value = "/findColumns")
	public KnowledgeBase findAllColumn(@RequestParam("organizationId") String organizationId) {
		logger.info("Find KnowledgeBaseColumn Request : {}", organizationId);
		if (organizationId == null || organizationId.equals("")) {
			throw new KnowledgeBaseException(PlatformError.ORGANIZATIONID_CAN_NOT_BE_NULL);
		}
		List<KnowledgeBase> knowledgeBases = knowledgeService.findByOrganizationId(organizationId);
		KnowledgeBase kb = new KnowledgeBase();
		for (KnowledgeBase k : knowledgeBases) {
			k.setKnowledegLevel(null);
			k.setKnowledegContent(null);
			if ("0".equals(k.getParentId())) {
				kb = k;
			}
		}
		if (kb.getId() == null || kb.getId().equals("")) {
			throw new KnowledgeBaseException(PlatformError.KNOWLEDGEBASE_IS_NULL);
		}
		TreeUtils.createTree(knowledgeBases, kb, "id", "parentId", "children");
		logger.info("Find KnowledgeBaseColumn Success, Response : {}", kb);
		return kb;
	}

	@ApiOperation("知识库创建目录")
	@ResponseBody
	@PostMapping("/createColumns")
	public KnowledgeBase createKBColumn(@RequestParam("parentId") String pId,
			@RequestParam("knowledegName") String kName, 
			@RequestParam("organizationId") String organizationId) {
		logger.info("Create KnowledgeBaseColumn Request : {}", pId, kName, organizationId);
		KnowledgeBase created = knowledgeService.creatKnowledgeColumn(pId, kName, organizationId);
		logger.info("Create KnowledgeBaseColumn Success, Response : {}", created);
		return created;
	}

	@ApiOperation("知识库修改目录")
	@ResponseBody
	@PostMapping("/updateColumns")
	public KnowledgeBase updateKBColumn(@RequestParam("id") String id, 
			@RequestParam("knowledegName") String kName) {
		logger.info("Update KnowledgeBaseColumn Request : {}", id, kName);
		KnowledgeBase kb = knowledgeService.updateKnowledgeBaseColumn(id, kName);
		logger.info("Update KnowledgeBaseColumn Success, Response : {}", kb);
		return kb;
	}

	@ApiOperation("知识库删除目录")
	@ResponseBody
	@PostMapping("/deleteColumns")
	public void deleteKBColumn(@RequestParam("id") String id) {
		logger.info("Delete KnowledgeBaseColumn Request : {}", id);
		knowledgeService.deleteKnowledgeBaseColumn(id);
		logger.info("Delete KnowledgeBaseColumn Success");
	}

	@ApiOperation("知识库创建文章")
	@ResponseBody
	@PostMapping("/createArticle")
	public KnowledgeBase createKB(@RequestParam("id") String id, 
			@RequestParam("knowledegContent") String kBContent) {
		logger.info("Create KnowledgeBase Request : {}", id, kBContent);
		KnowledgeBase created = knowledgeService.creatKnowledge(id, kBContent);
		logger.info("Create KnowledgeBase Success, Response : {}", created);
		return created;
	}
	
	@ApiOperation("知识库创建文章")
	@ResponseBody
	@PostMapping("/article")
	public KnowledgeBase createArticle(@RequestParam("parentId") String pId,
			@RequestParam("organizationId") String organizationId,
			@RequestParam("knowledegName") String kName, 
			@RequestParam("knowledegContent") String kBContent) {
		logger.info("Create createArticle Request : kName={}", kName );
		KnowledgeBase created = knowledgeService.creatKnowledge(pId, organizationId,kName,kBContent);
		logger.info("Create KnowledgeBase Success, Response : {}", created);
		return created;
	}

	@ApiOperation("知识库更新文章")
	@ResponseBody
	@PostMapping("/updateArticle")
	public KnowledgeBase updateKB(@RequestParam("id") String id, 
			@RequestParam("knowledegContent") String kBContent) {
		logger.info("Update KnowledgeBase Request : {}", id, kBContent);
		KnowledgeBase updated = knowledgeService.updateKnowledgeBase(id, kBContent);
		logger.info("Update KnowledgeBase Success, Response : {}", updated);
		return updated;
	}

	@ApiOperation("知识库获取文章详情")
	@ResponseBody
	@PostMapping("/findArticleById")
	public KnowledgeBase findKB(@RequestParam("id") String id) {
		logger.info("Find KnowledgeBase Request By Id : {}", id);
		KnowledgeBase kb = knowledgeService.findKnowledgeBase(id);
		logger.info("Find KnowledgeBase Success, Response By Id: {}", kb);
		return kb;
	}

	@ApiOperation("知识库删除文章")
	@ResponseBody
	@PostMapping("/deleteArticle")
	public KnowledgeBase deleteKB(@RequestParam("id") String id) {
		logger.info("Delete KnowledgeBase Request : {}", id);
		KnowledgeBase kb = knowledgeService.deleteKnowledgeBase(id);
		logger.info("Delete KnowledgeBase Success, Response : {}", kb);
		return kb;
	}

	@ApiOperation("知识库搜索")
	@ResponseBody
	@PostMapping("/findByContent")
	public List<KnowledgeBase> findByContent(@RequestParam("organizationId") String organizationId,
			@RequestParam("knowledegContent") String content) {
		logger.info("Find KnowledgeBase Request By Words: {}", organizationId, content);
		List<KnowledgeBase> knowledgeBases = knowledgeService.findByKnowledegcontentLike(organizationId, content);
		logger.info("Delete KnowledgeBase Success, Response : {}", knowledgeBases);
		return knowledgeBases;
	}
	
	@ApiOperation("知识库获取文章列表")
	@ResponseBody
	@GetMapping("/article")
	public List<KnowledgeBase> queryArticles(@RequestParam("pid") String pid) {
		List<KnowledgeBase> knowledgeBases = knowledgeService.queryArticles(pid);
		return knowledgeBases;
	}
}
