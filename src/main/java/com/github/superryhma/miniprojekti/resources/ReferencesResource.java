package com.github.superryhma.miniprojekti.resources;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.dao.impl.ReferenceDAOInMemoryImpl;
import com.github.superryhma.miniprojekti.dao.impl.ReferenceDAOdbImpl;
import com.github.superryhma.miniprojekti.models.Reference;
import com.github.superryhma.miniprojekti.models.Tag;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@Path("references")
@Produces(MediaType.APPLICATION_JSON)
public class ReferencesResource {

    private final static ReferenceDAO referenceDAO = new ReferenceDAOdbImpl();
    protected String path = "/api/references/";
    
    @GET
    public String getReferences() throws Exception {
        JSONArray jobj = new JSONArray();
        for(Reference ref : referenceDAO.getReferences())
            jobj.put(new JSONObject(ref));
        return jobj.toString();
    }

    @GET
    @Path("/{id}")
    public String getReferenceById(@PathParam("id") int id) {
        Reference ref = referenceDAO.getReferenceById(id);
        JSONObject jobj;
        if(ref != null)
            jobj = new JSONObject(ref);
        else
            jobj = new JSONObject(JSONObject.NULL);
        return jobj.toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addReference(String reference) {
        Reference ref = new Reference();
        JSONObject jobj = new JSONObject(reference);
        ref.setBibtexname(jobj.getString("name"));
        return new JSONObject(referenceDAO.addReference(ref)).toString();
    }
}
