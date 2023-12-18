package com.votifysoft.app.rest.api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

import com.votifysoft.app.beans.ActivePollsBean;
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

@Path("/active-polls")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActivePollsRestApi {

    @Inject
    private ActivePollsBean activePollsBean;

    @GET
    @Path("/fetch-all-polls")
    public List<Polls> fetchAllPolls() throws SQLException {
        return activePollsBean.fetchAllPolls();
    }

    @GET
    @Path("/fetch-all-poll-choices")
    public List<Answers> fetchAllPollChoices() throws SQLException {
        return activePollsBean.fetchAllPollChoices();
    }
}
