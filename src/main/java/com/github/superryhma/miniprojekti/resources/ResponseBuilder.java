package com.github.superryhma.miniprojekti.resources;

import com.github.superryhma.miniprojekti.models.AttributeType;
import com.github.superryhma.miniprojekti.models.Reference;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ResponseBuilder {
    private static JSONObject failureObject(String message) {
        JSONObject jobj = new JSONObject();
        jobj.put("success", false);
        jobj.put("error", message);
        return jobj;
    }
    public static JSONObject invalidReferenceField(String field) {
        return failureObject("Invalid field '" + field + "'");
    }
    public static JSONObject duplicateReferenceField(String field) {
        return failureObject("Duplicate field '" + field + "'");
    }
    public static JSONObject missingField(Set<String> fields) {
        if(fields.size() > 1) {
            List<String> fieldlist = new ArrayList<>(fields);
            String str = "'" + fieldlist.get(0) + "'";
            for(int i = 1; i < fieldlist.size() - 1; i++) {
                str += ", '" + fieldlist.get(i) + "'";
            }
            str += " and '" + fieldlist.get(fieldlist.size()-1) + "'";
            return failureObject("Missing fields " + str);
        }
        return failureObject("Missing field '" + fields.iterator().next() + "'");
    }
    public static JSONObject referenceNotFound() {
        return failureObject("Reference not found");
    }
    private static JSONObject successObject() {
        JSONObject jobj = new JSONObject();
        jobj.put("success", true);
        return jobj;
    }
    public static JSONObject successAddReference(Reference reference) {
        JSONObject jobj = successObject();
        jobj.put("id", reference.getId());
        return jobj;
    }
    public static JSONObject successGetReferenceById(Reference reference) {
        JSONObject jobj = new JSONObject();
        jobj.put("id", reference.getId());
        jobj.put("name", reference.getBibtexname());
        jobj.put("created_at", reference.getCreatedAt());
        jobj.put("type", reference.getType());
        jobj.put("fields", reference.getAttributes());
        jobj.put("tags", new ArrayList<>(reference.getTags()));
        return jobj;
    }
    public static JSONObject successGetReferences(List<Reference> references) {
        JSONObject jobj = successObject();
        JSONArray jarr = new JSONArray();
        for(Reference ref : references) {
            jarr.put(successGetReferenceById(ref));
        }
        jobj.put("reference", jarr);
        return jobj;
    }
    public static JSONObject getAPITypes(Set<AttributeType> attributeTypes) {
        JSONObject jobj = successObject();
        JSONArray jarr = new JSONArray();
        for(AttributeType at : attributeTypes) {
            JSONObject j = new JSONObject();
            j.put("name", at.getType());
            j.put("required", new ArrayList<>(at.getRequiredAttributes()));
            j.put("optional", new ArrayList<>(at.getOptionalAttributes()));
            jarr.put(j);
        }
        jobj.put("types", jarr);
        return jobj;
    }
}
