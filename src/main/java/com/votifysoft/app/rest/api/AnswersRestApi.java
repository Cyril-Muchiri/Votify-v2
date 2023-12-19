package com.votifysoft.app.rest.api;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import com.votifysoft.app.beans.AnswersBeanI;
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

@Path("/answers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnswersRestApi {

    @EJB
    private AnswersBeanI answersBean;

    @GET
    @Path("/fetchChoices")
    public Response fetchChoices() {
        boolean result = answersBean.fetchChoices();
        return Response.ok(result).build();
    }

    @POST
    @Path("/registerChoices")
    public Response registerChoices(Polls poll, List<Answers> topicChoices) {
        boolean result = answersBean.registerChoices(poll, topicChoices);
        return Response.ok(result).build();
    }

    @POST
    @Path("/registerVote/{participant}/{answerId}")
    public Response registerVote(@PathParam("participant") String participant,
                                 @PathParam("answerId") int answerId) {
        answersBean.registerVote(participant, answerId);
        return Response.ok().build();
    }
}
