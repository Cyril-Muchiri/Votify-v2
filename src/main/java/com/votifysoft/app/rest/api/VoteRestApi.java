package com.votifysoft.app.rest.api;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.votifysoft.app.beans.AnswersBeanI;

@Path("/votes")
public class VoteRestApi {

    @EJB
    private AnswersBeanI answersBean;

    @POST
    @Path("/submit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response submitVote(@QueryParam("participant") String participant, @QueryParam("answerId") int answerId) {
        try {

            answersBean.registerVote(participant, answerId);

            return Response.status(Response.Status.OK)
                    .entity("Vote submitted successfully for answerId: " + answerId)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to submit the vote.")
                    .build();
        }
    }
}
