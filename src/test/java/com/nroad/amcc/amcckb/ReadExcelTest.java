package com.nroad.amcc.amcckb;

import com.nroad.amcc.kb.HistoryData;
import com.nroad.amcc.support.jpa.HistoryDataJpaRepository;
import com.nroad.amcc.support.utils.ExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadExcelTest {

    @Autowired
    HistoryDataJpaRepository historyDataJpaRepository;

//    @Test
//    public void ReadExcelAndSave(MultipartFile file) throws IOException {
//
//        String path = "D:\\data\\专业历史分数线(往三届).xlsx";
//
//        List<String> list = new ExcelUtil().read(file.getInputStream());
//
//        HistoryData historyData = new HistoryData();
//        int count = 0;
//        for (int i = 0; i < (list.size() / 10); i++) {
//            historyData.setId(UUID.randomUUID().toString());
//            historyData.setArea(list.get(i + count));
//            historyData.setClassCategory(list.get(i + count + 1));
//            historyData.setPrTitle(list.get(i + count + 2));
//            historyData.setPrCode(list.get(i + count + 3));
//            historyData.setMinScoreBefore3(list.get(i + count + 4));
//            historyData.setMinScoreBefore2(list.get(i + count + 5));
//            historyData.setMinScoreBefore1(list.get(i + count + 6));
//            historyData.setForecastScore(list.get(i + count + 7));
//            historyData.setAdmissionBatch(list.get(i + count + 8));
//            historyData.setPlanNumber(list.get(i + count + 9));
//            count += 9;
//            historyDataJpaRepository.save(historyData);
//        }
//        System.out.println(historyData.getMinScoreBefore2());
//    }

//    @Test
//    public void ReadExcelDetailsAndSave() throws IOException {
//
//        String path = "D:\\data\\西安欧亚学院专业详情.xlsx";
//
//        List<String> list = new ExcelUtil().readProfessionDetails(path);
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
//        }
//    }


}
