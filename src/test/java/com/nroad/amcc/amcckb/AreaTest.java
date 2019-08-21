package com.nroad.amcc.amcckb;

import com.nroad.amcc.kb.AreaAdmitNumber;
import com.nroad.amcc.kb.AreaAdmitNumberRepository;
import com.nroad.amcc.kb.AreaTop3Profession;
import com.nroad.amcc.kb.AreaTop3ProfessionRepository;
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
    AreaTop3ProfessionRepository areaTop3ProfessionRepository;

    @Autowired
    AreaAdmitNumberRepository areaAdmitNumberRepository;

    @Test
    public void saveareatop3_1() {

        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setPrTitle("审计学");
        areaTop3Profession.setPrCode("120207");
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(65.62);
        areaTop3Profession.setEmploymentSalary(8500.33);
        areaTop3Profession.setAdmitNumber(30);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_2() {

        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setPrTitle("软件工程");
        areaTop3Profession.setPrCode("080902");
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(81.22);
        areaTop3Profession.setEmploymentSalary(6983.47);
        areaTop3Profession.setAdmitNumber(40);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_3() {

        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setPrTitle("电子信息工程");
        areaTop3Profession.setPrCode("080701");
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(86.94);
        areaTop3Profession.setEmploymentSalary(53547.36);
        areaTop3Profession.setAdmitNumber(50);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_4() {

        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setPrTitle("物联网工程");
        areaTop3Profession.setPrCode("080905");
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(78.35);
        areaTop3Profession.setEmploymentSalary(4961.64);
        areaTop3Profession.setAdmitNumber(60);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_5() {

        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setPrTitle("金融学");
        areaTop3Profession.setPrCode("020301K");
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(69.41);
        areaTop3Profession.setEmploymentSalary(4562.78);
        areaTop3Profession.setAdmitNumber(70);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_6() {

        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setPrTitle("电子商务");
        areaTop3Profession.setPrCode("120801");
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(75.62);
        areaTop3Profession.setEmploymentSalary(7456.21);
        areaTop3Profession.setAdmitNumber(80);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_12() {

        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setPrTitle("经济统计学");
        areaTop3Profession.setPrCode("020102");
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(75.62);
        areaTop3Profession.setEmploymentSalary(7456.21);
        areaTop3Profession.setAdmitNumber(80);
        areaTop3Profession.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_7() {
        AreaAdmitNumber areaAdmitNumber =new AreaAdmitNumber();
        areaAdmitNumber.setAdmitNumber(60);
        areaAdmitNumber.setAreaName("西安");
        areaAdmitNumber.setId(UUID.randomUUID().toString());
        areaAdmitNumber.setPrCode("120203K");
        areaAdmitNumber.setPrTitle("会计学");
        areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
        areaAdmitNumberRepository.save(areaAdmitNumber);
    }

    @Test
    public void saveareatop3_8() {
        AreaAdmitNumber areaAdmitNumber =new AreaAdmitNumber();
        areaAdmitNumber.setAdmitNumber(50);
        areaAdmitNumber.setAreaName("西安");
        areaAdmitNumber.setId(UUID.randomUUID().toString());
        areaAdmitNumber.setPrCode("120204");
        areaAdmitNumber.setPrTitle("财务管理");
        areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
        areaAdmitNumberRepository.save(areaAdmitNumber);
    }

    @Test
    public void saveareatop3_9() {
        AreaAdmitNumber areaAdmitNumber =new AreaAdmitNumber();
        areaAdmitNumber.setAdmitNumber(40);
        areaAdmitNumber.setAreaName("西安");
        areaAdmitNumber.setId(UUID.randomUUID().toString());
        areaAdmitNumber.setPrCode("120207");
        areaAdmitNumber.setPrTitle("审计学");
        areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
        areaAdmitNumberRepository.save(areaAdmitNumber);
    }

    @Test
    public void saveareatop3_10() {
        AreaAdmitNumber areaAdmitNumber =new AreaAdmitNumber();
        areaAdmitNumber.setAdmitNumber(30);
        areaAdmitNumber.setAreaName("西安");
        areaAdmitNumber.setId(UUID.randomUUID().toString());
        areaAdmitNumber.setPrCode("080902");
        areaAdmitNumber.setPrTitle("软件工程");
        areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
        areaAdmitNumberRepository.save(areaAdmitNumber);
    }
    @Test
    public void saveareatop3_11() {
        AreaAdmitNumber areaAdmitNumber =new AreaAdmitNumber();
        areaAdmitNumber.setAdmitNumber(20);
        areaAdmitNumber.setAreaName("西安");
        areaAdmitNumber.setId(UUID.randomUUID().toString());
        areaAdmitNumber.setPrCode("080703");
        areaAdmitNumber.setPrTitle("通信工程");
        areaAdmitNumber.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
        areaAdmitNumberRepository.save(areaAdmitNumber);
    }


}
