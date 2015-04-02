package com.github.superryhma;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("references")
@Produces(MediaType.APPLICATION_JSON)
public class References {
    @GET
    public String getReferences() {
        return "";
    }
    
    @GET
    @Path("/{id}")
    public String getReferenceById(@PathParam("id") int id) {
        return "";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addReference(String msg) {
        return "";
    }
}
