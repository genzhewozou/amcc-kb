package com.nroad.amcc.amcckb;

import com.nroad.amcc.kb.AreaAdmitNumberRepository;
import com.nroad.amcc.kb.GraduateStudent;
import com.nroad.amcc.kb.GraduateStudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraduateStudentTest {

    @Autowired
    GraduateStudentRepository graduateStudentRepository;

    @Test
    public void saveareatop3_1() {
        GraduateStudent graduateStudent = new GraduateStudent();
        graduateStudent.setGraduateArea("陕西");
        graduateStudent.setHighSchool("沙洲中学");
        graduateStudent.setId(UUID.randomUUID().toString());
        graduateStudent.setPrCode("120207");
        graduateStudent.setPrTitle("审计学");
//        graduateStudentRepository.save(graduateStudent);
    }
    @Test
    public void saveareatop3_2() {
        GraduateStudent graduateStudent = new GraduateStudent();
        graduateStudent.setGraduateArea("陕西");
        graduateStudent.setHighSchool("沙洲中学");
        graduateStudent.setId(UUID.randomUUID().toString());
        graduateStudent.setPrCode("120207");
        graduateStudent.setPrTitle("审计学");
//        graduateStudentRepository.save(graduateStudent);
    }
    @Test
    public void saveareatop3_3() {
        GraduateStudent graduateStudent = new GraduateStudent();
        graduateStudent.setGraduateArea("江苏");
        graduateStudent.setHighSchool("沙洲中学");
        graduateStudent.setId(UUID.randomUUID().toString());
        graduateStudent.setPrCode("120207");
        graduateStudent.setPrTitle("审计学");
//        graduateStudentRepository.save(graduateStudent);
    }
    @Test
    public void saveareatop3_4() {
        GraduateStudent graduateStudent = new GraduateStudent();
        graduateStudent.setGraduateArea("江苏");
        graduateStudent.setHighSchool("沙洲中学");
        graduateStudent.setId(UUID.randomUUID().toString());
        graduateStudent.setPrCode("120207");
        graduateStudent.setPrTitle("审计学");
//        graduateStudentRepository.save(graduateStudent);
    }
    @Test
    public void saveareatop3_5() {
        GraduateStudent graduateStudent = new GraduateStudent();
        graduateStudent.setGraduateArea("江苏");
        graduateStudent.setHighSchool("沙洲中学");
        graduateStudent.setId(UUID.randomUUID().toString());
        graduateStudent.setPrCode("120207");
        graduateStudent.setPrTitle("审计学");
//        graduateStudentRepository.save(graduateStudent);
    }
    @Test
    public void saveareatop3_6() {
        GraduateStudent graduateStudent = new GraduateStudent();
        graduateStudent.setGraduateArea("安徽");
        graduateStudent.setHighSchool("沙洲中学");
        graduateStudent.setId(UUID.randomUUID().toString());
        graduateStudent.setPrCode("120207");
        graduateStudent.setPrTitle("审计学");
//        graduateStudentRepository.save(graduateStudent);
    }
    @Test
    public void saveareatop3_7() {
        GraduateStudent graduateStudent = new GraduateStudent();
        graduateStudent.setGraduateArea("安徽");
        graduateStudent.setHighSchool("沙洲中学");
        graduateStudent.setId(UUID.randomUUID().toString());
        graduateStudent.setPrCode("120207");
        graduateStudent.setPrTitle("审计学");
//        graduateStudentRepository.save(graduateStudent);
    }
    @Test
    public void saveareatop3_8() {
        GraduateStudent graduateStudent = new GraduateStudent();
        graduateStudent.setGraduateArea("安徽");
        graduateStudent.setHighSchool("沙洲中学");
        graduateStudent.setId(UUID.randomUUID().toString());
        graduateStudent.setPrCode("120207");
        graduateStudent.setPrTitle("审计学");
//        graduateStudentRepository.save(graduateStudent);
    }
    @Test
    public void saveareatop3_9() {
        GraduateStudent graduateStudent = new GraduateStudent();
        graduateStudent.setGraduateArea("安徽");
        graduateStudent.setHighSchool("沙洲中学");
        graduateStudent.setId(UUID.randomUUID().toString());
        graduateStudent.setPrCode("120207");
        graduateStudent.setPrTitle("审计学");
//        graduateStudentRepository.save(graduateStudent);
    }
}
