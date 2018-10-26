package com.shajzlabs.surveyws.model.mapper;

import com.shajzlabs.surveyws.model.Stats;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class StatsMapper implements RowMapper<Stats> {

  @Override
  public Stats map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Stats(
      rs.getInt("questionId"),
      rs.getInt("optionId"),
      rs.getString("optionText"),
      rs.getInt("optionCount")
    );
  }
}
