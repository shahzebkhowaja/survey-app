package com.shajzlabs.surveyws.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Option {
    @JsonProperty
    private int id;

    @JsonProperty
    private int questionId;

    @JsonProperty
    private String optionText;

    public Option(int id, int questionId, String optionText) {
        this.id = id;
        this.questionId = questionId;
        this.optionText = optionText;
    }

    public int getId() {
        return id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getOptionText() {
        return optionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option that = (Option) o;

        if (id != that.id) return false;
        if (questionId != that.questionId) return false;
        return optionText.equals(that.optionText);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + questionId;
        result = 31 * result + optionText.hashCode();
        return result;
    }
}
