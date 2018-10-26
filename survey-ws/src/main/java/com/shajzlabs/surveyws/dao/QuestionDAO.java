package com.shajzlabs.surveyws.dao;

import com.shajzlabs.surveyws.model.Question;
import com.shajzlabs.surveyws.model.mapper.QuestionMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(QuestionMapper.class)
public interface QuestionDAO {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS questions (id BIGSERIAL PRIMARY KEY, questionText VARCHAR, questionType VARCHAR)")
    void createTableIfNotExist();

    @SqlUpdate("INSERT INTO questions(questionText, questionType) VALUES (:questionText, :questionType) RETURNING id")
    @GetGeneratedKeys
    int insert(@Bind("questionText") String questionText, @Bind("questionType") String questionType);

    @SqlQuery("SELECT * FROM questions WHERE id = :id")
    Question findQuesionById(@Bind("id") long id);

    @SqlQuery("SELECT DISTINCT questions.id, questions.questionText FROM questions JOIN answers ON questions.id = answers.questionId ORDER BY questions.id ASC;")
    List<Question> listQuestionsFromAnswers();

    @SqlQuery("SELECT * FROM questions")
    List<Question> listQuestions();
}
