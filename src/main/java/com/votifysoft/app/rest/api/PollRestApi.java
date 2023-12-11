package com.votifysoft.app.rest.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.votifysoft.app.beans.AnswersBeanI;
import com.votifysoft.app.beans.PollBeanI;
import com.votifysoft.app.beans.UserBeanI;
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;
import com.votifysoft.model.entity.User;

@Path("/topics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PollRestApi extends BaseRestApi {

    @EJB
    private PollBeanI topicBean;

    @EJB
    private AnswersBeanI answersBean;

    @EJB
    private UserBeanI userBeanI;

    @POST
    @Path("/register")
    public Response registerTopic(Polls poll) {
        try {
            int userId = getUserIdFromSessionOrRequest(); 
            User creator = userBeanI.getUserById(userId);
            poll.setCreator(creator);
            int pollId = topicBean.registerTopic(poll);

            Polls latestPoll = topicBean.getLatestPoll();

            List<Answers> answersList = poll.getAnswers();
            answersBean.registerChoices(latestPoll, answersList);

            return respond("Topic registered successfully with ID: " + pollId, Response.Status.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return respond("Failed to register the topic.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    private int getUserIdFromSessionOrRequest() {
//for test purposes let the creatorid be 1
        return 1; 
    }
}

