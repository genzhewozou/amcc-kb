package com.nroad.amcc.amcckb;

import com.nroad.amcc.kb.Area;
import com.nroad.amcc.kb.AreaRepository;
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
    AreaRepository areaRepository;

    @Test
    public void getArea() {
        String name = "西安";

        Area area = areaRepository.findByName(name);

        List<AreaTop3Profession> areaTop3Professions = areaTop3ProfessionRepository.findAllByArea(area.getId());
        area.setAreaTop3Professions(areaTop3Professions);
        area.setName(name);
        System.out.println(area.getAreaTop3Professions());
    }

    @Test
    public void savearea() {

        Area area = new Area();
        area.setName("西安");
        area.setId(UUID.randomUUID().toString());
//       areaRepository.save(area);
        Area areaa = new Area();
        areaa.setName("咸阳");
        areaa.setId(UUID.randomUUID().toString());
//        areaRepository.save(areaa);
        Area areaaa = new Area();
        areaaa.setName("全国");
//        areaRepository.save(areaaa);
    }

    @Test
    public void saveareatop3_1() {

        Area area = areaRepository.findByName("西安");
        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setArea(area);
        areaTop3Profession.setPrTitle("审计学");
        areaTop3Profession.setPrCode("120207");
        areaTop3Profession.setProfessionRank(3);
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(65.62);
        areaTop3Profession.setEmploymentSalary(8500.33);
        areaTop3Profession.setAdmitNumber(30);
//        areaTop3Profession.setMaxScore(379);
//        areaTop3Profession.setMinScore(308);
        areaTop3Profession.setProfessionRank(1);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_2() {

        Area area = areaRepository.findByName("西安");
        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setArea(area);
        areaTop3Profession.setPrTitle("软件工程");
        areaTop3Profession.setPrCode("080902");
        areaTop3Profession.setProfessionRank(1);
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(81.22);
        areaTop3Profession.setEmploymentSalary(6983.47);
        areaTop3Profession.setAdmitNumber(40);
//        areaTop3Profession.setMaxScore(369);
//        areaTop3Profession.setMinScore(303);
        areaTop3Profession.setProfessionRank(2);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_3() {

        Area area = areaRepository.findByName("西安");
        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setArea(area);
        areaTop3Profession.setPrTitle("电子信息工程");
        areaTop3Profession.setPrCode("080701");
        areaTop3Profession.setProfessionRank(2);
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(86.94);
        areaTop3Profession.setEmploymentSalary(53547.36);
        areaTop3Profession.setAdmitNumber(50);
//        areaTop3Profession.setMaxScore(385);
//        areaTop3Profession.setMinScore(301);
        areaTop3Profession.setProfessionRank(3);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_4() {

        Area area = areaRepository.findByName("咸阳");
        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setArea(area);
        areaTop3Profession.setPrTitle("物联网工程");
        areaTop3Profession.setPrCode("080905");
        areaTop3Profession.setProfessionRank(3);
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(78.35);
        areaTop3Profession.setEmploymentSalary(4961.64);
        areaTop3Profession.setAdmitNumber(60);
//        areaTop3Profession.setMaxScore(369);
//        areaTop3Profession.setMinScore(307);
        areaTop3Profession.setProfessionRank(1);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_5() {

        Area area = areaRepository.findByName("咸阳");
        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setArea(area);
        areaTop3Profession.setPrTitle("金融学");
        areaTop3Profession.setPrCode("020301K");
        areaTop3Profession.setProfessionRank(1);
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(69.41);
        areaTop3Profession.setEmploymentSalary(4562.78);
        areaTop3Profession.setAdmitNumber(70);
//        areaTop3Profession.setMaxScore(359);
//        areaTop3Profession.setMinScore(304);
        areaTop3Profession.setProfessionRank(3);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_6() {

        Area area = areaRepository.findByName("咸阳");
        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setArea(area);
        areaTop3Profession.setPrTitle("电子商务");
        areaTop3Profession.setPrCode("120801");
        areaTop3Profession.setProfessionRank(2);
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(75.62);
        areaTop3Profession.setEmploymentSalary(7456.21);
        areaTop3Profession.setAdmitNumber(80);
        areaTop3Profession.setProfessionRank(2);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

    @Test
    public void saveareatop3_7() {

        Area area = areaRepository.findByName("全国");
        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setArea(area);
        areaTop3Profession.setPrTitle("经济统计学");
        areaTop3Profession.setPrCode("020102");
        areaTop3Profession.setClassCategory("理工");
        areaTop3Profession.setEmploymentRate(75.62);
        areaTop3Profession.setEmploymentSalary(7456.21);
        areaTop3Profession.setAdmitNumber(80);
        areaTop3Profession.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

}
