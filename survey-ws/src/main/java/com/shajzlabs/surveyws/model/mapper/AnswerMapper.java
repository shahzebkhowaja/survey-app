package com.shajzlabs.surveyws.model.mapper;

import com.shajzlabs.surveyws.model.Answer;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class AnswerMapper implements RowMapper<Answer> {
    @Override
    public Answer map(ResultSet resultSet, StatementContext context) throws SQLException {
        return new Answer(
                resultSet.getInt("id"),
                resultSet.getString("surveyId"),
                resultSet.getInt("questionId"),
                resultSet.getInt("optionId"),
                resultSet.getString("answerText")
        );
    }
}
