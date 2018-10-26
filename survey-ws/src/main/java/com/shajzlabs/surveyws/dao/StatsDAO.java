package com.shajzlabs.surveyws.dao;

import com.shajzlabs.surveyws.model.Stats;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface StatsDAO {

  @SqlQuery("SELECT questions.id as questionId, optionId, optionText, COUNT(optionId) as optionCount FROM answers\n" +
    "JOIN options on options.id = answers.optionId\n" +
    "JOIN questions on questions.id = answers.questionId\n" +
    "WHERE optionId IS NOT NULL \n" +
    "GROUP BY optionId, optionText, questions.id\n" +
    "ORDER BY optionId ASC;")
  List<Stats> listStats();
}
