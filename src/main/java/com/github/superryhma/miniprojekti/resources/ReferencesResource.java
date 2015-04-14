package com.github.superryhma.miniprojekti.resources;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.dao.TypeDAO;
import com.github.superryhma.miniprojekti.dao.impl.ReferenceDAOInMemoryImpl;
import com.github.superryhma.miniprojekti.dao.impl.TypeDAOInMemoryImpl;
import com.github.superryhma.miniprojekti.models.Attribute;
import com.github.superryhma.miniprojekti.models.Reference;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Path("references")
@Produces(MediaType.APPLICATION_JSON)
public class ReferencesResource {

    private static ReferenceDAO referenceDAO = new ReferenceDAOInMemoryImpl();
    private static TypeDAO typeDAO = new TypeDAOInMemoryImpl();
    protected String path = "/api/references/";
    
    @GET
    public String getReferences() throws Exception {
        return ResponseBuilder.successGetReferences(referenceDAO.getReferences()).toString();
    }

    @GET
    @Path("/{id}")
    public String getReferenceById(@PathParam("id") int id) {
        Reference ref = referenceDAO.getReferenceById(id);
        if(ref != null)
            return ResponseBuilder.successGetReferenceById(ref).toString();
        return ResponseBuilder.referenceNotFound().toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addReference(String reference) {
        JSONObject jobj = new JSONObject(reference);
        JSONObject jfields = jobj.getJSONObject("fields");
        Set<Attribute> attr = new HashSet<>();
        Set<String> requiredAttributes = new HashSet<>(typeDAO.getRequiredFields(jobj.getString("type")));
        Set<String> allAttributes = new HashSet<>(typeDAO.getOptionalFields(jobj.getString("type")));
        allAttributes.addAll(requiredAttributes);
        for(String key : jfields.keySet()) {
            if(requiredAttributes.contains(key)) {
                requiredAttributes.remove(key);
            }
            if(!allAttributes.contains(key)) {
                return ResponseBuilder.invalidReferenceField(key).toString();
            }
            allAttributes.remove(key);
            attr.add(new Attribute(key, jfields.getString(key)));
        }
        if(requiredAttributes.size() > 0) {
            return ResponseBuilder.missingField(requiredAttributes).toString();
        }
        Set<String> tags = new HashSet<>();
        JSONArray arr = jobj.getJSONArray("tags");
        for(int i = 0; i < arr.length(); i++) {
            tags.add(arr.getString(i));
        }
        Reference ref = new Reference(jobj.getString("type"), jobj.getString("name"), Date.from(Instant.now()), null, attr, tags);
        return ResponseBuilder.successAddReference(referenceDAO.addReference(ref)).toString();
    }
}
