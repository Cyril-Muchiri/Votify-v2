package com.votifysoft.app.rest.api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

import com.votifysoft.app.beans.ActiveElectivesBean;
import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Nominees;

@Path("/active-electives")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActiveElectivesResource {

    @Inject
    private ActiveElectivesBean activeElectivesBean;

    @GET
    @Path("/fetch-all-electives")
    public List<Electives> fetchAllElectives() throws SQLException {
        return activeElectivesBean.fetchAllElectives();
    }

    @GET
    @Path("/fetch-all-nominees")
    public List<Nominees> fetchAllNominees() throws SQLException {
        return activeElectivesBean.fetchAllNominees();
    }
}
