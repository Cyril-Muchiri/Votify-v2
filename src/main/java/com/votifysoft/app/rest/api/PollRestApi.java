package com.votifysoft.app.rest.api;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.votifysoft.app.beans.PollBean;
import com.votifysoft.model.entity.Polls;

@Path("/polls")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PollRestApi extends BaseRestApi {

    @EJB
    private PollBean pollBean;

    @POST
    @Path("/register")
    public Response registerTopic(Polls pollTopic) {
        Integer generatedPollId = pollBean.registerTopic(pollTopic);

        if (generatedPollId != null) {
            return respond("Topic registered successfully with ID: " + generatedPollId, Response.Status.CREATED);
        } else {
            return respond("Failed to register the topic.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/latest")
    public Response getLatestPoll() {
        Polls latestPoll = pollBean.getLatestPoll();

        if (latestPoll != null) {
            return respond(latestPoll);
        } else {
            return respond("No polls found.", Response.Status.NOT_FOUND);
        }
    }
}
