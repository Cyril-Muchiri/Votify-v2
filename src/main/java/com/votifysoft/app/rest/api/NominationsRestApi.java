package com.votifysoft.app.rest.api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import com.votifysoft.app.beans.NominationBean;
import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Nominees;

@Path("/nomination")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NominationsRestApi {

    @Inject
    private NominationBean nominationBean;

    @GET
    @Path("/fetchChoices")
    public Response fetchChoices() {
        boolean result = nominationBean.fetchChoices();
        return Response.ok(result).build();
    }

    @POST
    @Path("/registerNominee")
    public Response registerNominee(Electives elective, List<Nominees> nomineeList, List<String> photopaths) {
        boolean result = nominationBean.registerNominee(elective, nomineeList, photopaths);
        return Response.ok(result).build();
    }

    @POST
    @Path("/registerVote/{participant}/{nomineeId}")
    public Response registerVote(@PathParam("participant") String participant,
                                 @PathParam("nomineeId") int nomineeId) {
        nominationBean.registerVote(participant, nomineeId);
        return Response.ok().build();
    }
}
