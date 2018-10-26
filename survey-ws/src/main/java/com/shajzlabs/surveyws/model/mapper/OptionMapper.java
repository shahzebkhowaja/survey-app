package com.shajzlabs.surveyws.model.mapper;

import com.shajzlabs.surveyws.model.Option;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class OptionMapper implements RowMapper<Option> {
    @Override
    public Option map(ResultSet resultSet, StatementContext context) throws SQLException {
        return new Option(
                resultSet.getInt("id"),
                resultSet.getInt("questionId"),
                resultSet.getString("optionText")
        );
    }
}
