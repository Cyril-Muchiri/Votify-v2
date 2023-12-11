package com.votifysoft.app.rest.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public abstract class BaseRestApi {

    Response respond(String string, Status created){
        return Response.status(Response.Status.OK).entity(new RestResponseWrapper()).build();
    }

    Response respond(Object object){
        return Response.status(Response.Status.OK).entity(object).build();
    }

    Response respond(RestResponseWrapper wrapper){
        return Response.status(wrapper.isSuccess()?Response.Status.OK:Response.Status.INTERNAL_SERVER_ERROR).entity(wrapper).build();
    }
}
