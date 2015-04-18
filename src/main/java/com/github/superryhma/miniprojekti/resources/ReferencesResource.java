package com.github.superryhma.miniprojekti.resources;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.dao.ReferenceTypeDAO;
import com.github.superryhma.miniprojekti.dao.impl.db.ReferenceDAODBImpl;
import com.github.superryhma.miniprojekti.dao.impl.db.ReferenceTypeDAODBImpl;
import com.github.superryhma.miniprojekti.models.Attribute;
import com.github.superryhma.miniprojekti.models.Reference;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Path("references")
@Produces(MediaType.APPLICATION_JSON)
public class ReferencesResource {

    private static ReferenceDAO referenceDAO = new ReferenceDAODBImpl();
    private static ReferenceTypeDAO referenceTypeDAO = new ReferenceTypeDAODBImpl();
    protected String path = "/api/references/";

    @GET
    public Response getReferences() throws Exception {
        return ResponseBuilder.successGetReferences(referenceDAO.getReferences());
    }

    @GET
    @Path("/{id}")
    public Response getReferenceById(@PathParam("id") int id) {
        Reference ref = referenceDAO.getReferenceById(id);
        if (ref != null)
            return ResponseBuilder.successGetReferenceById(ref);
        return ResponseBuilder.referenceNotFound();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReference(String reference) {
        JSONObject jobj = new JSONObject(reference);
        JSONObject jfields = jobj.getJSONObject("fields");
        Set<Attribute> attr = new HashSet<>();
        Set<String> requiredAttributes = new HashSet<>(referenceTypeDAO.getRequiredFields(jobj.getString("type")));
        Set<String> allAttributes = new HashSet<>(referenceTypeDAO.getOptionalFields(jobj.getString("type")));
        allAttributes.addAll(requiredAttributes);
        for (String key : jfields.keySet()) {
            if (requiredAttributes.contains(key)) {
                requiredAttributes.remove(key);
            }
            if (!allAttributes.contains(key)) {
                return ResponseBuilder.invalidReferenceField(key);
            }
            allAttributes.remove(key);
            attr.add(new Attribute(key, jfields.get(key).toString()));
        }
        if (requiredAttributes.size() > 0) {
            return ResponseBuilder.missingField(requiredAttributes);
        }
        Set<String> tags = new HashSet<>();
        JSONArray arr = jobj.getJSONArray("tags");
        for (int i = 0; i < arr.length(); i++) {
            tags.add(arr.getString(i));
        }
        Reference ref = new Reference(jobj.getString("type"),
                jobj.getString("name"),
                Date.from(Instant.now()), null, attr, tags);
        return ResponseBuilder.successAddReference(referenceDAO.addReference(ref));
    }
}
