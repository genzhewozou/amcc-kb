package com.nroad.amcc.amcckb;

import com.nroad.amcc.kb.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmccCrmApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    HistoryDataJpaRepository historyDataJpaRepository;

    @Autowired
    ProfessionDetailsRepository professionDetailsRepository;

    @Autowired
    ContractedAreaRepository contractedAreaRepository;

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

    @Test
    public void save_profession_details() {
        ProfessionDetails professionDetails = new ProfessionDetails();

        professionDetails.setId(UUID.randomUUID().toString());
        professionDetails.setProfessionCourses("a");
        professionDetailsRepository.save(professionDetails);
    }

    @Test
    public void save_contracted_area() {
        ProfessionDetails professionDetails = professionDetailsRepository.findOne("297eb1fd6c8a72a7016c8a72c7e20000");
        for (int i = 0; i < 3; i++) {
            ContractedArea contractedArea = new ContractedArea();
            contractedArea.setId(UUID.randomUUID().toString());
            contractedArea.setName("" + i);
            contractedArea.setProportion("82.99" + i);
            contractedArea.setProfessionDetails(professionDetails);
            contractedAreaRepository.save(contractedArea);
        }
    }
}
