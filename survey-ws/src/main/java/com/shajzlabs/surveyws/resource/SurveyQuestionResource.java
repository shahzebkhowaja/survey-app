package com.shajzlabs.surveyws.resource;

import com.shajzlabs.surveyws.dao.OptionDAO;
import com.shajzlabs.surveyws.dao.QuestionDAO;
import com.shajzlabs.surveyws.model.Question;
import com.shajzlabs.surveyws.model.response.QuestionResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/questions")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public final class SurveyQuestionResource {

    private final QuestionDAO questionDAO;
    private final OptionDAO optionDAO;

    public SurveyQuestionResource(QuestionDAO questionDAO, OptionDAO optionDAO) {
        this.questionDAO = questionDAO;
        this.optionDAO = optionDAO;
    }

    @GET
    public List<QuestionResponse> getAllQuestions() {
        final List<Question> questions = questionDAO.listQuestions();

        return QuestionResponse.createFrom(questions, optionDAO);
    }
}
