package com.shajzlabs.surveyws.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class OptionResponse {
    @JsonProperty
    private Integer optionId;

    @JsonProperty
    private String optionText;

    public OptionResponse() {
        // Jackson deserialization
    }

    public OptionResponse(int optionId, String optionText) {
        this.optionId = optionId;
        this.optionText = optionText;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public String getOptionText() {
        return optionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionResponse that = (OptionResponse) o;

        if (!optionId.equals(that.optionId)) return false;
        return optionText.equals(that.optionText);
    }

    @Override
    public int hashCode() {
        int result = optionId.hashCode();
        result = 31 * result + optionText.hashCode();
        return result;
    }
}
