package com.nroad.amcc.amcckb;

import com.nroad.amcc.kb.StudentsScore;
import com.nroad.amcc.kb.StudentsScoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentsScoreTest {

    @Autowired
    StudentsScoreRepository studentsScoreRepository;

    @Test
    public void save_1() {
        StudentsScore studentsScore = new StudentsScore();
        studentsScore.setId(UUID.randomUUID().toString());
        studentsScore.setClassCategory("理工");
        studentsScore.setPrCode("120207");
        studentsScore.setScore(356);
        studentsScore.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        studentsScoreRepository.save(studentsScore);
    }

    @Test
    public void save_2() {
        StudentsScore studentsScore = new StudentsScore();
        studentsScore.setId(UUID.randomUUID().toString());
        studentsScore.setClassCategory("理工");
        studentsScore.setPrCode("120207");
        studentsScore.setScore(353);
        studentsScore.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        studentsScoreRepository.save(studentsScore);
    }

    @Test
    public void save_3() {
        StudentsScore studentsScore = new StudentsScore();
        studentsScore.setId(UUID.randomUUID().toString());
        studentsScore.setClassCategory("理工");
        studentsScore.setPrCode("120207");
        studentsScore.setScore(344);
        studentsScore.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        studentsScoreRepository.save(studentsScore);
    }

    @Test
    public void save_4() {
        StudentsScore studentsScore = new StudentsScore();
        studentsScore.setId(UUID.randomUUID().toString());
        studentsScore.setClassCategory("理工");
        studentsScore.setPrCode("120207");
        studentsScore.setScore(350);
        studentsScore.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        studentsScoreRepository.save(studentsScore);
    }

    @Test
    public void save_5() {
        StudentsScore studentsScore = new StudentsScore();
        studentsScore.setId(UUID.randomUUID().toString());
        studentsScore.setClassCategory("理工");
        studentsScore.setPrCode("120207");
        studentsScore.setScore(340);
        studentsScore.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        studentsScoreRepository.save(studentsScore);
    }

    @Test
    public void save_6() {
        StudentsScore studentsScore = new StudentsScore();
        studentsScore.setId(UUID.randomUUID().toString());
        studentsScore.setClassCategory("理工");
        studentsScore.setPrCode("120207");
        studentsScore.setScore(362);
        studentsScore.setTenantId("d7ee6efb-a965-4446-8080-683feee76395");
//        studentsScoreRepository.save(studentsScore);
    }

}
