package com.votifysoft.app.rest.api;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.votifysoft.app.beans.ElectiveBeanI;
import com.votifysoft.model.entity.Electives;

@Path("/electives")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ElectiveRestApi {

    @EJB
    private ElectiveBeanI electiveBean;

    @POST
    @Path("/register-elective")
    public Integer registerElective(Electives elective) {
        return electiveBean.registerElective(elective);
    }

    @GET
    @Path("/get-latest-elective")
    public Electives getLatestElective() {
        return electiveBean.getLatestElective();
    }
}
