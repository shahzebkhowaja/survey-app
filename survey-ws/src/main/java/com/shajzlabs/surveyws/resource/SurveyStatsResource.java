package com.shajzlabs.surveyws.resource;

import com.shajzlabs.surveyws.dao.AnswerDAO;
import com.shajzlabs.surveyws.dao.OptionDAO;
import com.shajzlabs.surveyws.dao.QuestionDAO;
import com.shajzlabs.surveyws.dao.StatsDAO;
import com.shajzlabs.surveyws.model.response.StatsResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/stats")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class SurveyStatsResource {
  private final QuestionDAO questionDAO;
  private final AnswerDAO answerDAO;
  private final OptionDAO optionDAO;
  private final StatsDAO statsDAO;

  public SurveyStatsResource(QuestionDAO questionDAO, AnswerDAO answerDAO, OptionDAO optionDAO, StatsDAO statsDAO) {
    this.questionDAO = questionDAO;
    this.answerDAO = answerDAO;
    this.optionDAO = optionDAO;
    this.statsDAO = statsDAO;
  }

  @GET
  public List<StatsResponse> getSurveyStats() {
    return StatsResponse.createFrom(questionDAO, answerDAO, optionDAO, statsDAO);
  }
}
