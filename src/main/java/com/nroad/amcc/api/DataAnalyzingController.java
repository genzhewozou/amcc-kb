package com.nroad.amcc.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nroad.amcc.support.jpa.HeatSpeechReportJpaRepository;
import com.nroad.amcc.support.utils.DateUtil;

@RestController
@RequestMapping("/api/v1/dataAnalyzing")
public class DataAnalyzingController {

	@Autowired
	private HeatSpeechReportJpaRepository heatSpeechReportJpaRepository;
	
	/**
	 * 热频词分析
	 * @return
	 */
	@GetMapping("/queryHotWord")
	// @PreAuthorize("hasRole('TENANT_ADMIN')")
	public Object hotWord(@RequestParam(value="stime",required=false,defaultValue="0") long stime,
			@RequestParam(value="etime",required=false,defaultValue="0") long etime,
			@RequestParam("orgId") String orgId) {
		Date sDate=DateUtil.getResetTime(new Date(stime), 0, 0, 0);;
		Date eDate=DateUtil.getResetTime(new Date(etime==0L?System.currentTimeMillis():etime), 23, 59, 59);
		List<HotWord> results=new ArrayList<>();
		List<Object> objList=heatSpeechReportJpaRepository.findHotWords(orgId,sDate,eDate);
		int index=1;
		for(Object result:objList) {
			HotWord e=toHotWord(result);
			if(e!=null) {
				e.setId(""+index++);
				results.add(e);
			}
		}
		return results;
	}
	
	private HotWord toHotWord(Object result) {
		if(result!=null && result instanceof Object[]) {
			HotWord e=new HotWord();
			
			Object[] row = (Object[]) result;
			e.setWord(""+row[0]);
			e.setSearchCount(Integer.parseInt(""+row[1]));
			return e;
		}
		return null;
	  }
	
	class HotWord{
		String id;
		String word;
		int searchCount;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		public int getSearchCount() {
			return searchCount;
		}
		public void setSearchCount(int searchCount) {
			this.searchCount = searchCount;
		}
	}

}
