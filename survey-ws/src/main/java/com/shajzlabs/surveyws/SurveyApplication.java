package com.shajzlabs.surveyws;

import com.github.arteam.jdbi3.JdbiFactory;
import com.shajzlabs.surveyws.dao.AnswerDAO;
import com.shajzlabs.surveyws.dao.OptionDAO;
import com.shajzlabs.surveyws.dao.QuestionDAO;
import com.shajzlabs.surveyws.dao.StatsDAO;
import com.shajzlabs.surveyws.model.QuestionsFactory;
import com.shajzlabs.surveyws.model.mapper.AnswerMapper;
import com.shajzlabs.surveyws.model.mapper.OptionMapper;
import com.shajzlabs.surveyws.model.mapper.QuestionMapper;
import com.shajzlabs.surveyws.model.mapper.StatsMapper;
import com.shajzlabs.surveyws.resource.SurveyAnswerResource;
import com.shajzlabs.surveyws.resource.SurveyQuestionResource;
import com.shajzlabs.surveyws.resource.SurveyStatsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.io.File;
import java.util.EnumSet;

public final class SurveyApplication extends Application<ApplicationConfiguration> {
    private static final int EXIT_CODE_INVALID_ARGS = -1;
    private static final int EXIT_CODE_INVALID_CONFIG = -2;

    public static void main(final String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Required parameters: " + "[Path to web service configuration file]");
            System.exit(EXIT_CODE_INVALID_ARGS);
        }

        final String dwConfigPath = args[0];
        final File dwFile = new File(dwConfigPath);
        if (!dwFile.isFile()) {
            System.out.println("Cannot find or read web service configuration at {" + dwFile.getAbsolutePath() + "}");
            System.exit(EXIT_CODE_INVALID_CONFIG);
        }

        new SurveyApplication().run("server", dwConfigPath);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {

    }

    @Override
    public void run(ApplicationConfiguration config, Environment environment) throws Exception {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
          environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        final Jdbi jdbi = new JdbiFactory().build(environment, config.getDataSourceFactory(), "postgresql");

        jdbi.registerRowMapper(new OptionMapper());
        jdbi.registerRowMapper(new QuestionMapper());
        jdbi.registerRowMapper(new AnswerMapper());
        jdbi.registerRowMapper(new StatsMapper());

        final OptionDAO optionDAO = jdbi.onDemand(OptionDAO.class);
        final QuestionDAO questionDAO = jdbi.onDemand(QuestionDAO.class);
        final AnswerDAO answerDAO = jdbi.onDemand(AnswerDAO.class);
        final StatsDAO statsDAO = jdbi.onDemand(StatsDAO.class);

        optionDAO.createTableIfNotExist();
        questionDAO.createTableIfNotExist();
        answerDAO.createTableIfNotExist();

        QuestionsFactory.createSampleQuestions(questionDAO, optionDAO);

        environment.jersey().register(new SurveyQuestionResource(questionDAO, optionDAO));
        environment.jersey().register(new SurveyAnswerResource(answerDAO));
        environment.jersey().register(new SurveyStatsResource(questionDAO, answerDAO, optionDAO, statsDAO));
    }
}
