package com.passerelle.admin.resources;

import io.dropwizard.auth.Auth;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import com.passerelle.admin.core.Greeting;
import com.passerelle.admin.core.User;

@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("ADMIN")
    public String getGreeting(@Auth User user) {
        return "Hello world!";
    }
    
    @Path("/path_param/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getNamedGreeting(@PathParam(value = "name") String name) {
        return "Hello " + name;
    }
    
    @Path("/query_param")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getNamedStringWithParam(@DefaultValue("world") @QueryParam("name") String name) {
        return "Hello " + name;
    }
    
    @Path("/hello_json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting getJSONGreeting() {
        return new Greeting("Hello world!");
    }
    
    public String getTailoredGreetingWithQueryParam(@QueryParam("name") Optional<String> name) {
        if (name.isPresent()) {
            return "Hello " + name.get();
        } 
        else {
            return "Hello world";
        }
        //The same can be accomplished using or(...) method to provide the default value
        //return "Hello " + name.or("world");
    }
}