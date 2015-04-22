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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("references")
@Produces(MediaType.APPLICATION_JSON)
public class ReferencesResource {

    private static ReferenceDAO referenceDAO = new ReferenceDAODBImpl();
    private static ReferenceTypeDAO referenceTypeDAO = new ReferenceTypeDAODBImpl();
    protected String path = "/api/references/";

    @GET
    public Response getReferences() {
        JSONObject jobj = getSuccessObject();
        JSONArray jarr = new JSONArray();
        for (Reference ref : referenceDAO.getReferences()) {
            jarr.put(ref.toJSON());
        }
        jobj.put("references", jarr);
        return getResponse(jobj, 200);
    }

    private JSONObject getSuccessObject() {
        JSONObject jobj1 = new JSONObject();
        jobj1.put("success", true);
        return jobj1;
    }

    private Response.ResponseBuilder getReferencesAsBiBTeXBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Reference reference : referenceDAO.getReferences()) {
            stringBuilder.append(reference.toBiBTeX());
            stringBuilder.append('\n');
        }
        return Response.status(200).entity(stringBuilder.toString());
    }

    @GET
    @Produces("text/x-bibtex")
    public Response getReferencesAsBiBTeX() {
        return getReferencesAsBiBTeXBuilder().build();
    }

    @GET
    @Path("/references.bib")
    @Produces("text/x-bibtex")
    public Response getReferencesAsBiBTeXFile() {
        return getReferencesAsBiBTeXBuilder().header("Content-Disposition", "attachment").build();
    }

    @GET
    @Path("/{id}")
    public Response getReferenceById(@PathParam("id") int id) {
        Reference ref = referenceDAO.getReferenceById(id);
        if (ref != null) {
            return getResponse(ref.toJSON(), 200);
        }
        return getResponse(referenceNotFound(), 404);
    }

    private JSONObject referenceNotFound() {
        JSONObject jobj = getErrorObject(404, "Reference not found");
        return jobj;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReferenceById(@PathParam("id") int id) {
        if(!referenceDAO.deleteReference(id)) {
            return getResponse(referenceNotFound(), 404);
        }
        return getResponse(getSuccessObject(), 200);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReferenceById(String reference, @PathParam("id") int id) {
        JSONObject ref = new JSONObject(reference);
        JSONObject refFields = ref.getJSONObject("fields");
        Set<Attribute> attributes = new HashSet<>();
        Set<String> requiredAttrs = new HashSet<>(referenceTypeDAO.getRequiredFields(ref.getString("type")));
        Set<String> allAttrs = new HashSet<>(referenceTypeDAO.getOptionalFields(ref.getString("type")));
        allAttrs.addAll(requiredAttrs);
        for (String key : refFields.keySet()) {
            if (requiredAttrs.contains(key)) {
                requiredAttrs.remove(key);
            }
            if (!allAttrs.contains(key)) {
                return getResponse(getErrorObject(400, "Invalid field '" + key + "'"), 400);
            }
            allAttrs.remove(key);
            attributes.add(new Attribute(key, refFields.get(key).toString()));
        }
        if (requiredAttrs.size() > 0) {
            if (requiredAttrs.size() > 1) {
                List<String> fieldList = new ArrayList<>(requiredAttrs);
                String str = "'" + fieldList.get(0) + "'";
                for (int i = 1; i < fieldList.size() - 1; i++) {
                    str += ", '" + fieldList.get(i) + "'";
                }
                str += " and '" + fieldList.get(fieldList.size() - 1) + "'";
                return getResponse(getErrorObject(400, "Missing fields " + str), 400);
            }
            return getResponse(getErrorObject(400, "Missing field '" + requiredAttrs.iterator().next() + "'"), 400);
        }
        Set<String> tags = new HashSet<>();
        JSONArray arr = ref.getJSONArray("tags");
        for (int i = 0; i < arr.length(); i++) {
            tags.add(arr.getString(i));
        }
        Reference ref1 = new Reference(ref.getString("type"),
                ref.getString("name"),
                Date.from(Instant.now()), null, attributes, tags);
        ref1.setId(id);
        JSONObject jobj11 = getSuccessObject();
        jobj11.put("id", referenceDAO.updateReference(id, ref1).getId());
        return getResponse(jobj11, 200);
    }

    @GET
    @Path("/{id}")
    @Produces("text/x-bibtex")
    public Response getReferenceByIDAsBiBTeX(@PathParam("id") int id) {
        Reference ref = referenceDAO.getReferenceById(id);
        if (ref != null) {
            return getResponse(ref.toBiBTeX(), 200);
        }
        return getResponse(referenceNotFound(), 404);
    }

    private Response getResponse(Object jobj, int status) {
        return Response.status(status)
                .entity(jobj.toString())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReference(String reference) {
        JSONObject ref = new JSONObject(reference);
        JSONObject refFields = ref.getJSONObject("fields");
        Set<Attribute> attributes = new HashSet<>();
        Set<String> requiredAttrs = new HashSet<>(referenceTypeDAO.getRequiredFields(ref.getString("type")));
        Set<String> allAttrs = new HashSet<>(referenceTypeDAO.getOptionalFields(ref.getString("type")));
        allAttrs.addAll(requiredAttrs);
        for (String key : refFields.keySet()) {
            if (requiredAttrs.contains(key)) {
                requiredAttrs.remove(key);
            }
            if (!allAttrs.contains(key)) {
                return getResponse(getErrorObject(400, "Invalid field '" + key + "'"), 400);
            }
            allAttrs.remove(key);
            attributes.add(new Attribute(key, refFields.get(key).toString()));
        }
        if (requiredAttrs.size() > 0) {
            if (requiredAttrs.size() > 1) {
                List<String> fieldList = new ArrayList<>(requiredAttrs);
                String str = "'" + fieldList.get(0) + "'";
                for (int i = 1; i < fieldList.size() - 1; i++) {
                    str += ", '" + fieldList.get(i) + "'";
                }
                str += " and '" + fieldList.get(fieldList.size() - 1) + "'";
                return getResponse(getErrorObject(400, "Missing fields " + str), 400);
            }
            return getResponse(getErrorObject(400, "Missing field '" + requiredAttrs.iterator().next() + "'"), 400);
        }
        Set<String> tags = new HashSet<>();
        JSONArray arr = ref.getJSONArray("tags");
        for (int i = 0; i < arr.length(); i++) {
            tags.add(arr.getString(i));
        }
        Reference ref1 = new Reference(ref.getString("type"),
                ref.getString("name"),
                Date.from(Instant.now()), null, attributes, tags);
        JSONObject jobj11 = getSuccessObject();
        jobj11.put("id", referenceDAO.addReference(ref1).getId());
        return getResponse(jobj11, 200);
    }

    private JSONObject getErrorObject(int errorCode, String description) {
        JSONObject jobj1 = new JSONObject();
        jobj1.put("success", false);
        jobj1.put("error", errorCode);
        jobj1.put("description", description);
        return jobj1;
    }
}
