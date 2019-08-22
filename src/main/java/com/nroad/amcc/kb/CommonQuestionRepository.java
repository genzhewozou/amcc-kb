package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommonQuestionRepository extends JpaRepository<CommonQuestion, String> {

    @Query(value = "select question from kb_common_question where question like %:keyWord%",nativeQuery = true)
    List<String> findByKeyWord(@Param("keyWord") String keyWord);

    @Query(value = "select answer from kb_common_question where question =?1",nativeQuery = true)
    String findAnswerByQuestion(String question);


}
