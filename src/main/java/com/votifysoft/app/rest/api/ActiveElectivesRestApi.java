package com.votifysoft.app.rest.api;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

import com.votifysoft.app.beans.ActiveElectivesBeanI;
import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Nominees;

@Path("/getElectives")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActiveElectivesRestApi {

    @EJB
    private ActiveElectivesBeanI activeElectivesBean;

    @GET
    @Path("/showAllElectives")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Electives> fetchAllElectives() throws SQLException {
        return activeElectivesBean.fetchAllElectives();
    }

    @GET
    @Path("/showNominees")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Nominees> fetchAllNominees() throws SQLException {
        return activeElectivesBean.fetchAllNominees();
    }
}
