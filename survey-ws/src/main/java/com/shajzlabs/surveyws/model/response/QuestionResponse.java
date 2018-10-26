package com.shajzlabs.surveyws.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.shajzlabs.surveyws.dao.OptionDAO;
import com.shajzlabs.surveyws.model.Option;
import com.shajzlabs.surveyws.model.Question;

import java.util.List;

public final class QuestionResponse {
  @JsonProperty
  private Integer questionId;

  @JsonProperty
  private String questionText;

  @JsonProperty
  private String questionType;

  @JsonProperty
  private List<OptionResponse> options;

  public QuestionResponse() {
    // Jackson deserialization
  }

  public QuestionResponse(int questionId, String questionText, String questionType, List<OptionResponse> options) {
    this.questionId = questionId;
    this.questionText = questionText;
    this.questionType = questionType;
    this.options = options;
  }

  public static List<QuestionResponse> createFrom(List<Question> questions, OptionDAO optionDAO) {
    final List<QuestionResponse> responseList = Lists.newArrayList();

    for (Question question : questions) {
      final List<OptionResponse> questionOptions = Lists.newArrayList();
      final List<Option> options = optionDAO.findOptionByQuestionId(question.getId());

      if (!options.isEmpty()) {
        for (Option option : options) {
          OptionResponse optionResponse = new OptionResponse(option.getId(), option.getOptionText());
          questionOptions.add(optionResponse);
        }
      }

      final QuestionResponse response = new QuestionResponse(question.getId(), question.getQuestionText(), question.getQuestionType(), questionOptions);
      responseList.add(response);
    }

    return responseList;
  }

  public int getQuestionId() {
    return questionId;
  }

  public String getQuestionText() {
    return questionText;
  }

  public String getQuestionType() {
    return questionType;
  }

  public List<OptionResponse> getOptions() {
    return options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QuestionResponse response = (QuestionResponse) o;

    if (!questionId.equals(response.questionId)) return false;
    if (!questionText.equals(response.questionText)) return false;
    if (!questionType.equals(response.questionType)) return false;
    return options.equals(response.options);
  }

  @Override
  public int hashCode() {
    int result = questionId.hashCode();
    result = 31 * result + questionText.hashCode();
    result = 31 * result + questionType.hashCode();
    result = 31 * result + options.hashCode();
    return result;
  }
}
