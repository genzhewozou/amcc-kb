package com.nroad.amcc.api;

import com.nroad.amcc.kb.AdmissionPolicy;
import com.nroad.amcc.kb.AdmissionPolicyRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class QuartzCrawlDataServiceV1 {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdmissionPolicyRepository admissionPolicyRepository;

    private static final String PHONE_AREA = "http://www.sxsksglzx.com/zcwj1/ptgk.htm";

    public void crawlShanXiPolicy() {

        List<String> titleList = new ArrayList<>();
        List<AdmissionPolicy> exitData = admissionPolicyRepository.findAll();
        if (exitData != null && exitData.size() != 0) {
            for (int m = 0; m < exitData.size(); m++) {
                titleList.add(exitData.get(m).getTitle());
            }
        }

        List<AdmissionPolicy> admissionPolicies = getShanXiPolicy();

        if (admissionPolicies.size() == 0) {
            return;
        }

        for (int i = 0; i < admissionPolicies.size(); i++) {
            if (exitData != null && titleList.contains(admissionPolicies.get(i).getTitle())) {
                continue;
            } else
                admissionPolicyRepository.saveAndFlush(admissionPolicies.get(i));
        }

    }

    private List<AdmissionPolicy> getShanXiPolicy() {

        List<AdmissionPolicy> admissionPolicies = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(PHONE_AREA).get();
            Elements tables = doc.getElementsByClass("guihua");
            //根据li标签来划分
            Elements td = tables.select("li");

            for (int j = 0; j < td.size(); j++) {

                AdmissionPolicy admissionPolicy = new AdmissionPolicy();

                //获取到标签中的内容
                String text = td.get(j).text();
                String date = text.substring(0, 10);
                String title = text.substring(10);
                log.info(title);
                //获取A标签的href 网址  select 获取到当前A标签 attr href 获取到地址
                String s = td.get(j).select("a").attr("href");
                String url = "http://www.sxsksglzx.com" + s.substring(2);
                log.info(url);

                admissionPolicy.setArea("陕西省");
                admissionPolicy.setId(UUID.randomUUID().toString());
                admissionPolicy.setDate(date);
                admissionPolicy.setTitle(title);
                admissionPolicy.setUrl(url);

                admissionPolicies.add(admissionPolicy);
            }
        } catch (IOException e) {
        }
        return admissionPolicies;
    }
}
