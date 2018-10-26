package com.shajzlabs.surveyws.model.mapper;

import com.shajzlabs.surveyws.model.Question;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Question(
                rs.getInt("id"),
                rs.getString("questionText"),
                rs.getString("questionType")
        );
    }
}
