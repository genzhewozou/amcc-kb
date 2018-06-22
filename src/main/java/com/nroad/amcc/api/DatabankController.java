package com.nroad.amcc.api;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nroad.amcc.kb.Databank;
import com.nroad.amcc.kb.HeatSpeechReport;
import com.nroad.amcc.support.jpa.DatabankJpaRepository;
import com.nroad.amcc.support.jpa.HeatSpeechReportJpaRepository;
import com.nroad.amcc.support.utils.DateUtil;
import com.nroad.amcc.support.utils.TreeUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/databank")
public class DatabankController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DatabankJpaRepository databankJpaRepository;

	@Autowired
	private HeatSpeechReportJpaRepository heatSpeechReportJpaRepository;

	@GetMapping(value = "/query")
	@ApiOperation(value = "查询资料信息", notes = "根据orgId、word")
	public Object findAllColumn(@RequestParam("orgId") String orgId, @RequestParam("word") String word) {
		Date dtime = DateUtil.getResetTime(new Date(), 0, 0, 0);
		List<HeatSpeechReport> reportList = heatSpeechReportJpaRepository.findAllByHotWordAndDtime(word, dtime, orgId);
		HeatSpeechReport instance = null;
		if (reportList.size() == 0) {
			instance = new HeatSpeechReport();
			instance.setDtime(dtime);
			instance.setHotWord(word);
			instance.setOrgId(orgId);
			instance.setQueryCount(1);
		} else {
			instance = reportList.get(0);
			instance.setQueryCount(instance.getQueryCount() + 1);
		}
		heatSpeechReportJpaRepository.save(instance);
		return databankJpaRepository.findAllByOrgIdAndContent(orgId, word);
	}

	@GetMapping(value = "/findAll")
	@ApiOperation(value = "获取资料库列表", notes = "根据orgId")
	@PreAuthorize("hasRole('TENANT_ADMIN')")
	public Object findAllColumn(@RequestParam("orgId") String orgId) {
		List<Databank> rootList = databankJpaRepository.findAllByOrgIdAndPid(orgId, "0");
		if (rootList.size() != 0) {
			List<Databank> databankList = databankJpaRepository.findAllByOrgId(orgId);
			for (Databank it : databankList) {
				if(StringUtils.isNotEmpty(it.getContent())) {
					it.setContent("yes");
				}
			}
			for (Databank root : rootList) {
				TreeUtils.createTree(databankList, root, "id", "pid", "children");
			}
		}
		return rootList;
	}

	@GetMapping(value = "/findAllChildren")
	@ApiOperation(value = "获取当前资料下一级列表", notes = "根据id、orgId")
	@PreAuthorize("hasRole('TENANT_ADMIN')")
	public Object findAllChildren(@RequestParam("id") String id, @RequestParam("orgId") String orgId) {
		return databankJpaRepository.findAllByOrgIdAndPid(orgId, id);
	}

	@GetMapping("/{id:.+}")
	@ApiOperation(value = "获取资料信息", notes = "根据ID")
	public Object get(@PathVariable("id") String id) {
		return databankJpaRepository.findOne(id);
	}

	@PostMapping("/")
	@ApiOperation(value = "创建资料信息", notes = "")
	@PreAuthorize("hasRole('TENANT_ADMIN')")
	public Databank create(@RequestParam(value = "orgId", required = true) String orgId,
			@RequestParam(value = "pid", defaultValue = "0") String pid,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "content", required = false) String content) {
		Databank instance = new Databank();
		instance.setOrgId(orgId);
		instance.setPid(pid);
		instance.setName(name);
		if (StringUtils.isNotEmpty(content)) {
			instance.setContent(content);
		}
		String level = "0";
		if (!"0".equals(pid)) {
			level = "" + (Integer.valueOf(databankJpaRepository.getOne(pid).getLevel()) + 1);
		}
		instance.setLevel(level);
		return databankJpaRepository.save(instance);
	}

	@PostMapping("/{id:.+}")
	@ApiOperation(value = "更新资料信息", notes = "")
	@PreAuthorize("hasRole('TENANT_ADMIN')")
	public Databank update(@PathVariable("id") String id, @RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "content", required = false) String content) {
		Databank instance = databankJpaRepository.findOne(id);
		if (StringUtils.isNotEmpty(name)) {
			instance.setName(name);
		}
		if (StringUtils.isNotEmpty(content)) {
			instance.setContent(content);
		}
		return databankJpaRepository.save(instance);
	}

	@DeleteMapping("/{id:.+}")
	@ApiOperation(value = "删除资料信息", notes = "根据ID")
	@PreAuthorize("hasRole('TENANT_ADMIN')")
	public void delete(@PathVariable("id") String id) {
		logger.info("删除资料信息。id={}", id);
		List<Databank> databankList = databankJpaRepository.findAll();
		deleteChildren(id, databankList);
		databankJpaRepository.delete(id);
	}

	private void deleteChildren(String id, List<Databank> databankList) {
		for (Databank it : databankList) {
			if (id.equals(it.getPid())) {
				deleteChildren(it.getId(), databankList);
				databankJpaRepository.delete(it.getId());
			}
		}
	}

}
