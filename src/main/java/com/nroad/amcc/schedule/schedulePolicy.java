package com.nroad.amcc.schedule;

import com.nroad.amcc.api.QuartzCrawlDataServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class schedulePolicy {

    @Autowired
    private QuartzCrawlDataServiceV1 quartzCrawlDataServiceV1;

    @Scheduled(cron = "0 0 */12 * * ?")  //每隔12小时执行一次
    public void crawlPolicy() {

        quartzCrawlDataServiceV1.crawlShanXiPolicy();
    }
}
