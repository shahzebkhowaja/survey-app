package com.shajzlabs.surveyws.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Answer {
    @JsonProperty
    private int id;

    @JsonProperty
    private String surveyId;

    @JsonProperty
    private int questionId;

    @JsonProperty
    private Integer optionId;

    @JsonProperty
    private String answerText;

    public Answer() {}

    public Answer(int id, String surveyId, int questionId, Integer optionId, String answerText) {
        super();
        this.id = id;
        this.surveyId = surveyId;
        this.questionId = questionId;
        this.optionId = optionId;
        this.answerText = answerText;
    }

    public int getId() {
        return id;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (id != answer.id) return false;
        if (questionId != answer.questionId) return false;
        if (!surveyId.equals(answer.surveyId)) return false;
        if (!optionId.equals(answer.optionId)) return false;
        return answerText.equals(answer.answerText);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + surveyId.hashCode();
        result = 31 * result + questionId;
        result = 31 * result + optionId.hashCode();
        result = 31 * result + answerText.hashCode();
        return result;
    }
}
