package com.votifysoft.app.rest.api;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.votifysoft.app.beans.AuthBeanI;
import com.votifysoft.model.entity.User;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Provider
@Priority(1)
public class RestAuthFilterApi implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @EJB
    private AuthBeanI authBean;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();

        if (method.isAnnotationPresent(DenyAll.class) || !method.isAnnotationPresent(RolesAllowed.class)) {
            abort(requestContext, "End point not allowed");
            return;
        }

        // GET HEADERS
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

        // Get Authorization header
        List<String> authorization = headers.get("Authorization");

        // if there is no Authorization header abort
        if (authorization == null || authorization.isEmpty() || authorization.get(0) == null) {
            abort(requestContext, "Authentication not provided");
            return;
        }

        String basicAuth = authorization.get(0);

        // if Authorization header value does not contain basic abort
        if (!basicAuth.contains("Basic")) {
            abort(requestContext, "Basic authentication is required!");
            return;
        }

        // remove the Basic from authorization to remain with base64 encode username and
        // password
        String base64Auth = basicAuth.replace("Basic ", "").trim();

        System.out.println("Encoded username and password " + base64Auth);

        byte[] decodedUserAndPwd = Base64.getDecoder().decode(base64Auth);
        System.out.println(new String(decodedUserAndPwd, StandardCharsets.UTF_8));

        String[] usernameAndPwd = new String(decodedUserAndPwd, StandardCharsets.UTF_8).split(":");

        User user = new User();
        user.setUserEmail(usernameAndPwd[0]);
        user.setPassword(usernameAndPwd[1]);

        try {
            user = authBean.authenticate(user);

        } catch (Exception e) {
            abort(requestContext, e.getMessage());
        }
    }

    private void abort(ContainerRequestContext requestContext, String message) {
        requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                .entity(new RestResponseWrapper(false, message))
                .type(MediaType.APPLICATION_JSON).build());
    }

}
