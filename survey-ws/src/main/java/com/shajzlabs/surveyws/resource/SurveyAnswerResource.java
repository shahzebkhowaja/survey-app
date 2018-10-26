package com.shajzlabs.surveyws.resource;

import com.shajzlabs.surveyws.dao.AnswerDAO;
import com.shajzlabs.surveyws.model.requests.AnswerRequest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/answers")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public final class SurveyAnswerResource {
    private final AnswerDAO answerDAO;

    public SurveyAnswerResource(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response submitAnswers(@Valid List<AnswerRequest> requests) {
        final String surveyId = UUID.randomUUID().toString();
        for (AnswerRequest request : requests) {
            if (request.getOptionIds() != null && !request.getOptionIds().isEmpty()) {
                for (int optionId : request.getOptionIds() ) {
                    answerDAO.insert(surveyId, request.getQuestionId(), optionId, request.getAnswerText());
                }
            }
            else {
                answerDAO.insert(surveyId, request.getQuestionId(), null, request.getAnswerText());
            }
        }

        return Response.ok(true).build();
    }
}
