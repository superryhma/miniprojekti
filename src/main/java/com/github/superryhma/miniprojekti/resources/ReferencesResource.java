package com.github.superryhma.miniprojekti.resources;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.dao.ReferenceTypeDAO;
import com.github.superryhma.miniprojekti.dao.impl.db.ReferenceDAODBImpl;
import com.github.superryhma.miniprojekti.dao.impl.db.ReferenceTypeDAODBImpl;
import com.github.superryhma.miniprojekti.models.Reference;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("references")
@Produces(MediaType.APPLICATION_JSON)
public class ReferencesResource {

    private static ReferenceDAO referenceDAO = new ReferenceDAODBImpl();
    private static ReferenceTypeDAO referenceTypeDAO = new ReferenceTypeDAODBImpl();

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReference(String reference) {
        Reference ref;
        try {
            ref = new Reference(reference, referenceTypeDAO);
        } catch (Reference.ReferenceException e) {
            return getResponse(getErrorObject(400, e.getErrorMessage()), 400);
        }
        JSONObject jobj = getSuccessObject();
        try {
            jobj.put("id", referenceDAO.addReference(ref).getId());
        // } catch (PSQLException e) {
        } catch (Exception e) {
            return getResponse(getErrorObject(400, e.getMessage()), 400);
        }
        return getResponse(jobj, 200);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReferenceById(String reference, @PathParam("id") int id) {
        Reference ref = null;
        try {
            ref = new Reference(reference, referenceTypeDAO);
        } catch (Reference.ReferenceException e) {
            return getResponse(getErrorObject(400, e.getErrorMessage()), 400);
        }
        ref.setId(id);
        JSONObject jobj = getSuccessObject();
        try {
            jobj.put("id", referenceDAO.updateReference(id, ref).getId());
        } catch (Exception e) {
            return getResponse(getErrorObject(400, e.getMessage()), 400);
        }
        return getResponse(jobj, 200);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReferenceById(@PathParam("id") int id) {
        if(!referenceDAO.deleteReference(id)) {
            return getResponse(referenceNotFound(), 404);
        }
        return getResponse(getSuccessObject(), 200);
    }

    private JSONObject getSuccessObject() {
        JSONObject jobj1 = new JSONObject();
        jobj1.put("success", true);
        return jobj1;
    }

    private JSONObject getErrorObject(int errorCode, String description) {
        JSONObject jobj1 = new JSONObject();
        jobj1.put("success", false);
        jobj1.put("error", errorCode);
        jobj1.put("description", description);
        return jobj1;
    }

    private Response getResponse(Object jobj, int status) {
        return Response.status(status)
                .entity(jobj.toString())
                .build();
    }

    private Response.ResponseBuilder getReferencesAsBiBTeXBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Reference reference : referenceDAO.getReferences()) {
            stringBuilder.append(reference.toBiBTeX());
            stringBuilder.append('\n');
        }
        return Response.status(200).entity(stringBuilder.toString());
    }

    private JSONObject referenceNotFound() {
        JSONObject jobj = getErrorObject(404, "Reference not found");
        return jobj;
    }
}
