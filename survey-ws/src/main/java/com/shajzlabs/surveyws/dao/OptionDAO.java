package com.shajzlabs.surveyws.dao;

import com.shajzlabs.surveyws.model.Option;
import com.shajzlabs.surveyws.model.mapper.OptionMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(OptionMapper.class)
public interface OptionDAO {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS options (id BIGSERIAL PRIMARY KEY, questionId BIGINT, optionText VARCHAR)")
    void createTableIfNotExist();

    @SqlUpdate("INSERT INTO options(questionId, optionText) VALUES (:questionId, :optionText) RETURNING id")
    @GetGeneratedKeys
    int insert(@Bind("questionId") int questionId, @Bind("optionText") String option);

    @SqlQuery("SELECT * FROM options WHERE id = :id")
    Option findOptionById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM options WHERE questionId = :questionId")
    List<Option> findOptionByQuestionId(@Bind("questionId") int questionId);

    @SqlQuery("SELECT * FROM options")
    List<Option> listOptions();
}
