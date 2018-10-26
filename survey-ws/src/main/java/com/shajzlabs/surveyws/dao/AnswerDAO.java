package com.shajzlabs.surveyws.dao;

import com.shajzlabs.surveyws.model.Answer;
import com.shajzlabs.surveyws.model.mapper.AnswerMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(AnswerMapper.class)
public interface AnswerDAO {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS answers (id BIGSERIAL PRIMARY KEY, surveyId VARCHAR, questionId BIGINT, optionId BIGINT, answerText VARCHAR)")
    void createTableIfNotExist();

    @SqlUpdate("INSERT INTO answers(surveyId, questionId, optionId, answerText) VALUES (:surveyId, :questionId, :optionId, :answerText) RETURNING id")
    @GetGeneratedKeys
    int insert(@Bind("surveyId") String surveyId, @Bind("questionId") int questionId, @Bind("optionId") Integer optionIds, @Bind("answerText") String answer);

    @SqlQuery("SELECT * FROM answers WHERE id = :id")
    Answer findAnswerById(@Bind("id") int id);

    @SqlQuery("SELECT COUNT (DISTINCT surveyId) FROM answers WHERE questionId = :questionId AND (optionId IS NOT NULL OR answerText IS NOT NULL)")
    int findAnswerCount(@Bind("questionId") int questionId);

    @SqlQuery("SELECT * FROM answers")
    List<Answer> listAnswers();
}
