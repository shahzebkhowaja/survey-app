package com.shajzlabs.surveyws.model;

import com.shajzlabs.surveyws.dao.OptionDAO;
import com.shajzlabs.surveyws.dao.QuestionDAO;

public final class QuestionsFactory {

    private static final String[][] QUESTIONS = new String[][] {
      {"How likely is it that you would recommend this company to a friend or colleague?", "single_choice"},
      {"Overall, how satisfied or dissatisfied are you with our company?", "single_choice"},
      {"Which of the following words would you use to describe our products? Select all that apply.", "multiple_choice"},
      {"How well do our products meet your needs?", "single_choice"},
      {"How would you rate the quality of the product?", "single_choice"},
      {"How would you rate the value for money of the product?", "single_choice"},
      {"How responsive have we been to your questions or concerns about our products?", "single_choice"},
      {"How long have you been a customer of our company?", "single_choice"},
      {"How likely are you to purchase any of our products again?", "single_choice"},
      {"Do you have any other comments, questions, or concerns?", "text"}
    };

    private static final String[] OPTIONS = new String[] {
            "0,1,2,3,4,5,6,7,8,9,10",
            "Very satisfied,Somewhat satisfied,Neither satisfied nor dissatisfied,Somewhat dissatisfied,Very dissatisfied",
            "Reliable,High quality,Useful,Unique,Good value for money,Overpriced,Impractical,Ineffective,Poor quality,Unreliable",
            "Extremely well,Very well,Somewhat well,Not so well,Not at all well",
            "Very high quality,High quality,Neither high nor low quality,Low quality,Very low quality",
            "Excellent,Above average,Average,Below average,Poor",
            "Extremely responsive,Very responsive,Somewhat responsive,Not so responsive,Not at all responsive,Not applicable",
            "This is my first purchase,Less than six months,Six months to a year,1 - 2 years,3 or more years,I haven't made a purchase yet",
            "Extremely likely,Very likely,Somewhat likely,Not so likely,Not at all likely",
            ""
    };

    private QuestionsFactory() {}

    public static void createSampleQuestions(QuestionDAO questionDAO, OptionDAO optionDAO) {
        if (!optionDAO.listOptions().isEmpty() || !questionDAO.listQuestions().isEmpty())
            return;

        for (int idx = 0; idx < QUESTIONS.length; idx++) {
            final String question = QUESTIONS[idx][0];
            final String questionType = QUESTIONS[idx][1];
            final int questionId = questionDAO.insert(question, questionType);

            final String option = OPTIONS[idx];
            if (!option.isEmpty()) {
                final String[] tempOptions = option.split(",");

                for (String tempOption : tempOptions) {
                    optionDAO.insert(questionId, tempOption);
                }
            }
        }
    }
}
