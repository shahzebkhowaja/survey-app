package com.shajzlabs.surveyws.model;

public final class Stats {
  private int questionId;
  private int optionId;
  private String optionText;
  private int optionCount;

  public Stats(int questionId, int optionId, String optionText, int optionCount) {
    this.questionId = questionId;
    this.optionId = optionId;
    this.optionText = optionText;
    this.optionCount = optionCount;
  }

  public int getQuestionId() {
    return questionId;
  }

  public int getOptionId() {
    return optionId;
  }

  public String getOptionText() {
    return optionText;
  }

  public int getOptionCount() {
    return optionCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Stats stats = (Stats) o;

    if (questionId != stats.questionId) return false;
    if (optionId != stats.optionId) return false;
    if (optionCount != stats.optionCount) return false;
    return optionText.equals(stats.optionText);
  }

  @Override
  public int hashCode() {
    int result = questionId;
    result = 31 * result + optionId;
    result = 31 * result + optionText.hashCode();
    result = 31 * result + optionCount;
    return result;
  }
}
