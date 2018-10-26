package com.shajzlabs.surveyws.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Question {
    @JsonProperty
    private Integer id;

    @JsonProperty
    private String questionText;

    @JsonProperty
    private String questionType;

    public Question() {
        // Jackson Des
    }

    public Question(int id, String questionText, String questionType) {
        super();
        this.id = id;
        this.questionText = questionText;
        this.questionType = questionType;
    }

    public int getId() {
          return id;
      }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionType() {
    return questionType;
  }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Question question = (Question) o;

      if (!id.equals(question.id)) return false;
      if (!questionText.equals(question.questionText)) return false;
      return questionType.equals(question.questionType);
    }

    @Override
    public int hashCode() {
      int result = id.hashCode();
      result = 31 * result + questionText.hashCode();
      result = 31 * result + questionType.hashCode();
      return result;
    }
}
