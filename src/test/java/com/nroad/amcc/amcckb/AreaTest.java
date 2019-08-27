package com.nroad.amcc.amcckb;

import com.nroad.amcc.kb.AreaAdmitNumber;
import com.nroad.amcc.kb.AreaAdmitNumberRepository;
import com.nroad.amcc.kb.ProfessionDetails;
import com.nroad.amcc.kb.ProfessionDetailsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaTest {

    @Autowired
    AreaAdmitNumberRepository areaAdmitNumberRepository;

    @Autowired
    ProfessionDetailsRepository professionDetailsRepository;

    @Test
    public void saveareatop3_7() {
        AreaAdmitNumber areaAdmitNumber = new AreaAdmitNumber();
        areaAdmitNumber.setAdmitNumber(60);
        areaAdmitNumber.setAreaName("西安");
        areaAdmitNumber.setId(UUID.randomUUID().toString());
        areaAdmitNumber.setPrCode("120203K");
        areaAdmitNumber.setPrTitle("会计学");
        areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        areaAdmitNumberRepository.save(areaAdmitNumber);
    }

    @Test
    public void saveareatop3_8() {
        AreaAdmitNumber areaAdmitNumber = new AreaAdmitNumber();
        areaAdmitNumber.setAdmitNumber(50);
        areaAdmitNumber.setAreaName("西安");
        areaAdmitNumber.setId(UUID.randomUUID().toString());
        areaAdmitNumber.setPrCode("120204");
        areaAdmitNumber.setPrTitle("财务管理");
        areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        areaAdmitNumberRepository.save(areaAdmitNumber);
    }

    @Test
    public void saveareatop3_9() {
        AreaAdmitNumber areaAdmitNumber = new AreaAdmitNumber();
        areaAdmitNumber.setAdmitNumber(40);
        areaAdmitNumber.setAreaName("西安");
        areaAdmitNumber.setId(UUID.randomUUID().toString());
        areaAdmitNumber.setPrCode("120207");
        areaAdmitNumber.setPrTitle("审计学");
        areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        areaAdmitNumberRepository.save(areaAdmitNumber);
    }

    @Test
    public void saveareatop3_10() {
        AreaAdmitNumber areaAdmitNumber = new AreaAdmitNumber();
        areaAdmitNumber.setAdmitNumber(30);
        areaAdmitNumber.setAreaName("西安");
        areaAdmitNumber.setId(UUID.randomUUID().toString());
        areaAdmitNumber.setPrCode("080902");
        areaAdmitNumber.setPrTitle("软件工程");
        areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        areaAdmitNumberRepository.save(areaAdmitNumber);
    }

    @Test
    public void saveareatop3_11() {
        AreaAdmitNumber areaAdmitNumber = new AreaAdmitNumber();
        areaAdmitNumber.setAdmitNumber(20);
        areaAdmitNumber.setAreaName("西安");
        areaAdmitNumber.setId(UUID.randomUUID().toString());
        areaAdmitNumber.setPrCode("080703");
        areaAdmitNumber.setPrTitle("通信工程");
        areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        areaAdmitNumberRepository.save(areaAdmitNumber);
    }

    @Test
    public void saveareatop3_21() {
        List<ProfessionDetails> professionDetailsList = professionDetailsRepository.findAll();
        for (int i = 0; i < professionDetailsList.size(); i++) {
            AreaAdmitNumber areaAdmitNumber = new AreaAdmitNumber();
            areaAdmitNumber.setAdmitNumber(50 + i);
            areaAdmitNumber.setAreaName("西安");
            areaAdmitNumber.setId(UUID.randomUUID().toString());
            areaAdmitNumber.setPrCode(professionDetailsList.get(i).getPrCode());
            areaAdmitNumber.setPrTitle(professionDetailsList.get(i).getPrTitle());
            areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        areaAdmitNumberRepository.save(areaAdmitNumber);
        }

    }


}
