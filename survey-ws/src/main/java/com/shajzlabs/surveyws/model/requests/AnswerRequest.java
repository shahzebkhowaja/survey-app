package com.shajzlabs.surveyws.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class AnswerRequest {
    @JsonProperty("questionId")
    private Integer questionId;

    @JsonProperty("optionIds")
    private List<Integer> optionIds;

    @JsonProperty("answerText")
    private String answerText;

    public AnswerRequest() {
        // Jackson deserialization
    }

    public AnswerRequest(Integer questionId, List<Integer> optionIds, String answer) {
        this.questionId = questionId;
        this.optionIds = optionIds;
        this.answerText = answer;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public List<Integer> getOptionIds() {
        return optionIds;
    }

    public String getAnswerText() {
        return answerText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerRequest that = (AnswerRequest) o;

        if (!questionId.equals(that.questionId)) return false;
        if (!optionIds.equals(that.optionIds)) return false;
        return answerText.equals(that.answerText);
    }

    @Override
    public int hashCode() {
        int result = questionId.hashCode();
        result = 31 * result + optionIds.hashCode();
        result = 31 * result + answerText.hashCode();
        return result;
    }
}
