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
    public void getArea(){
        String name = "西安";

        Area area = areaRepository.findByName(name);

        List<AreaTop3Profession> areaTop3Professions = areaTop3ProfessionRepository.findAllByArea(area.getId());
        area.setAreaTop3Professions(areaTop3Professions);
        area.setName(name);
        System.out.println(area.getAreaTop3Professions());
    }

    @Test
    public void savearea(){

       Area area = new Area();
       area.setName("西安");
       area.setId(UUID.randomUUID().toString());
       areaRepository.save(area);
        Area areaa = new Area();
        areaa.setName("咸阳");
        areaa.setId(UUID.randomUUID().toString());
        areaRepository.save(areaa);
    }

    @Test
    public void saveareatop3(){

        Area area = areaRepository.findByName("咸阳");
        AreaTop3Profession areaTop3Profession = new AreaTop3Profession();
        areaTop3Profession.setId(UUID.randomUUID().toString());
        areaTop3Profession.setArea(area);
        areaTop3Profession.setName("电子商务");
        areaTop3Profession.setRank(3);
        areaTop3ProfessionRepository.save(areaTop3Profession);
    }

}
