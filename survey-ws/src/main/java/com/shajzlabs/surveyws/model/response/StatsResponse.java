package com.shajzlabs.surveyws.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shajzlabs.surveyws.dao.AnswerDAO;
import com.shajzlabs.surveyws.dao.OptionDAO;
import com.shajzlabs.surveyws.dao.QuestionDAO;
import com.shajzlabs.surveyws.dao.StatsDAO;
import com.shajzlabs.surveyws.model.Question;
import com.shajzlabs.surveyws.model.Stats;

import java.util.List;
import java.util.Map;

public final class StatsResponse {

  @JsonProperty
  private Integer questionId;

  @JsonProperty
  private String questionText;

  @JsonProperty
  private List<OptionTextCount> optionTextCounts;

  public StatsResponse(Integer questionId, String questionText, List<OptionTextCount> optionTextCounts) {
    this.questionId = questionId;
    this.questionText = questionText;
    this.optionTextCounts = optionTextCounts;
  }

  public static List<StatsResponse> createFrom(QuestionDAO questionDAO, AnswerDAO answerDAO, OptionDAO optionDAO, StatsDAO statsDAO) {
    final List<StatsResponse> statsResponses = Lists.newArrayList();

    final Map<Integer, Question> questionsMap = Maps.newHashMap();
    for (Question question : questionDAO.listQuestions()) {
      questionsMap.put(question.getId(), question);
    }

    final Map<Integer, List<OptionTextCount>> statsMap = Maps.newTreeMap();
    for (Stats stats : statsDAO.listStats()) {
      final List<OptionTextCount> optionTextCounts;

      if (!statsMap.containsKey(stats.getQuestionId())) {
        optionTextCounts = Lists.newArrayList();
      } else {
        optionTextCounts = statsMap.get(stats.getQuestionId());
      }

      optionTextCounts.add(new OptionTextCount(stats.getOptionText(), stats.getOptionCount()));
      statsMap.put(stats.getQuestionId(), optionTextCounts);
    }

    for (Map.Entry<Integer, List<OptionTextCount>> entry : statsMap.entrySet()) {
      final StatsResponse response = new StatsResponse(entry.getKey(), questionsMap.get(entry.getKey()).getQuestionText(), entry.getValue());
      statsResponses.add(response);
    }

    return statsResponses;
  }

  public Integer getQuestionId() {
    return questionId;
  }

  public String getQuestionText() {
    return questionText;
  }

  public List<OptionTextCount> getOptionTextCounts() {
    return optionTextCounts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    StatsResponse stats = (StatsResponse) o;

    if (!questionId.equals(stats.questionId)) return false;
    if (!questionText.equals(stats.questionText)) return false;
    return optionTextCounts.equals(stats.optionTextCounts);
  }

  @Override
  public int hashCode() {
    int result = questionId.hashCode();
    result = 31 * result + questionText.hashCode();
    result = 31 * result + optionTextCounts.hashCode();
    return result;
  }

  public static class OptionTextCount {
    @JsonProperty
    private String name; // Option Text

    @JsonProperty
    private Integer value; // Option Count

    public OptionTextCount(String name, Integer value) {
      this.name = name;
      this.value = value;
    }

    public String getName() {
      return name;
    }

    public Integer getValue() {
      return value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      OptionTextCount that = (OptionTextCount) o;

      if (!name.equals(that.name)) return false;
      return value.equals(that.value);
    }

    @Override
    public int hashCode() {
      int result = name.hashCode();
      result = 31 * result + value.hashCode();
      return result;
    }
  }
}
