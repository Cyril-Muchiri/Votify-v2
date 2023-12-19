package com.votifysoft.app.rest.api;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

import com.votifysoft.app.beans.ActivePollsBean;
import com.votifysoft.app.beans.ActivePollsBeanI;
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

@Path("/active-polls")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActivePollsRestApi {

    @EJB
    private ActivePollsBeanI activePollsBean;

    @GET
    @Path("/fetch-all-polls")
    public List<Polls> fetchAllPolls() {
        try {
            return activePollsBean.fetchAllPolls();
        } catch (SQLException e) {
            // Handle exception appropriately
            e.printStackTrace();
            throw new InternalServerErrorException("Failed to fetch all polls");
        }
    }

    @GET
    @Path("/fetch-all-poll-choices")
    public List<Answers> fetchAllPollChoices() {
        try {
            return activePollsBean.fetchAllPollChoices();
        } catch (SQLException e) {
            // Handle exception appropriately
            e.printStackTrace();
            throw new InternalServerErrorException("Failed to fetch all poll choices");
        }
    }
}
