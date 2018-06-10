package com.nroad.amcc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dataAnalyzing")
public class DataAnalyzingController {

	/**
	 * 热频词分析
	 * @return
	 */
	@GetMapping("/queryHotWord")
	@PreAuthorize("hasRole('TENANT_ADMIN')")
	public Object hotWord(@RequestParam("stime") long stime,@RequestParam("etime") long etime) {
		
		List<HotWord> results=new ArrayList<>();
		Random ran=new Random();  
		for(int i=1;i<=100;i++) {
			HotWord e=new HotWord();
			e.setId(""+i);
			e.setWord("word"+i);
			e.setSearchCount(ran.nextInt(1000));
			results.add(e);
		}
		return results;
		
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
