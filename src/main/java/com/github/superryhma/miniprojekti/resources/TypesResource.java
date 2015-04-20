package com.github.superryhma.miniprojekti.resources;

import com.github.superryhma.miniprojekti.dao.ReferenceTypeDAO;
import com.github.superryhma.miniprojekti.dao.impl.db.ReferenceTypeDAODBImpl;
import com.github.superryhma.miniprojekti.models.ReferenceType;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Set;

@Path("types")
@Produces(MediaType.APPLICATION_JSON)
public class TypesResource {

    private static ReferenceTypeDAO referenceTypeDAO = new ReferenceTypeDAODBImpl();

    @GET
    public Response getTypes() {
        Set<ReferenceType> types = referenceTypeDAO.getTypes();
        JSONObject jobj1 = new JSONObject();
        jobj1.put("success", true);
        JSONObject jobj = jobj1;
        JSONArray jarr = new JSONArray();
        for (ReferenceType at : types) {
            JSONObject j = new JSONObject();
            j.put("name", at.getName());
            j.put("required", new ArrayList<>(at.getRequiredAttributes()));
            j.put("optional", new ArrayList<>(at.getOptionalAttributes()));
            jarr.put(j);
        }
        jobj.put("types", jarr);
        return Response.status(200)
                .entity(jobj.toString())
                .build();
    }
}
