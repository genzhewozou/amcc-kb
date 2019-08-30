package com.nroad.amcc.amcckb;

import com.nroad.amcc.kb.AreaAdmitNumberRepository;
import com.nroad.amcc.kb.LastYearScore;
import com.nroad.amcc.kb.LastYearScoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LastYearScoreTest {

    @Autowired
    LastYearScoreRepository lastYearScoreRepository;

    @Test
    public void saveareatop3_1() {
        for (int i = 0; i < 10; i++) {
            LastYearScore lastYearScore = new LastYearScore();
            lastYearScore.setClassCategory("理工");
            lastYearScore.setId(UUID.randomUUID().toString());
            lastYearScore.setScore(360);
            lastYearScore.setArea("陕西省");
            lastYearScore.setTenantId("f295e6c5-1672-4515-a2a9-88b6c93370fe");
            lastYearScoreRepository.save(lastYearScore);
        }
        for (int i = 0; i < 50; i++) {
            LastYearScore lastYearScore = new LastYearScore();
            lastYearScore.setClassCategory("文史");
            lastYearScore.setId(UUID.randomUUID().toString());
            lastYearScore.setScore(360+i);
            lastYearScore.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//            lastYearScoreRepository.save(lastYearScore);
        }

    }

}
