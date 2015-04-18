package com.github.superryhma.miniprojekti.resources;

import com.github.superryhma.miniprojekti.models.Attribute;
import com.github.superryhma.miniprojekti.models.ReferenceType;
import com.github.superryhma.miniprojekti.models.Reference;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ResponseBuilder {
    private static JSONObject failureObject(int errorCode, String message) {
        JSONObject jobj = new JSONObject();
        jobj.put("success", false);
        jobj.put("error", errorCode);
        jobj.put("description", message);
        return jobj;
    }

    public static Response invalidReferenceField(String field) {
        return Response.status(400)
                .entity(failureObject(400, "Invalid field '" + field + "'").toString())
                .build();
    }

    public static Response missingField(Set<String> fields) {
        if (fields.size() > 1) {
            List<String> fieldlist = new ArrayList<>(fields);
            String str = "'" + fieldlist.get(0) + "'";
            for (int i = 1; i < fieldlist.size() - 1; i++) {
                str += ", '" + fieldlist.get(i) + "'";
            }
            str += " and '" + fieldlist.get(fieldlist.size() - 1) + "'";
            return Response.status(400)
                    .entity(failureObject(400, "Missing fields " + str).toString())
                    .build();
        }
        return Response.status(400)
                .entity(failureObject(400, "Missing field '" + fields.iterator().next() + "'").toString())
                .build();
    }

    public static Response referenceNotFound() {
        return Response.status(404)
                .entity(failureObject(404, "Reference not found").toString())
                .build();
    }

    private static JSONObject successObject() {
        JSONObject jobj = new JSONObject();
        jobj.put("success", true);
        return jobj;
    }

    public static Response successAddReference(Reference reference) {
        JSONObject jobj = successObject();
        jobj.put("id", reference.getId());
        return Response.status(200)
                .entity(jobj.toString())
                .build();
    }

    private static JSONObject referenceToJSON(Reference reference) {
        JSONObject jobj = new JSONObject();
        jobj.put("id", reference.getId());
        jobj.put("name", reference.getBibtexname());
        jobj.put("created_at", reference.getCreatedAt());
        jobj.put("type", reference.getType());
        JSONObject attrs = new JSONObject();
        for (Attribute attr : reference.getAttributes()) {
            attrs.put(attr.getAttributeType(), attr.getValue());
        }
        jobj.put("fields", attrs);
        jobj.put("tags", new ArrayList<>(reference.getTags()));
        return jobj;
    }

    public static Response successGetReferenceById(Reference reference) {
        return Response.status(200)
                .entity(referenceToJSON(reference))
                .build();
    }

    public static Response successGetReferences(List<Reference> references) {
        JSONObject jobj = successObject();
        JSONArray jarr = new JSONArray();
        for (Reference ref : references) {
            jarr.put(referenceToJSON(ref));
        }
        jobj.put("references", jarr);
        return Response.status(200)
                .entity(jobj.toString())
                .build();
    }

    public static Response getAPITypes(Set<ReferenceType> referenceTypes) {
        JSONObject jobj = successObject();
        JSONArray jarr = new JSONArray();
        for (ReferenceType at : referenceTypes) {
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
