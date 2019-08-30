package com.nroad.amcc.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/quartzCrawl")
public class QuartzCrawlDataControllerV1 {

    @Autowired
    private QuartzCrawlDataServiceV1 quartzCrawlDataServiceV1;

    @PostMapping("/shanXi/policy")
    @ApiOperation(value = "爬取招生政策", notes = "")
    public void crawlShanXiPolicy() {
        quartzCrawlDataServiceV1.crawlShanXiPolicy();
    }

}