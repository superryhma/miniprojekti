package com.github.superryhma.miniprojekti.models;

import com.github.superryhma.miniprojekti.dao.ReferenceTypeDAO;
import com.github.superryhma.miniprojekti.utils.BibtexUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.*;

public class Reference {

    private int id;
    private String type;
    private String bibtexName;
    private Date createdAt;
    private Date updatedAt;
    private Set<Attribute> attributes;
    private Set<String> tags;

    public Reference(String type, String bibtexName, Date createdAt, Date updatedAt,
            Set<Attribute> attributes, Set<String> tags) {
        this.type = type;
        this.bibtexName = bibtexName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.attributes = attributes;
        this.tags = tags;
    }

    public Reference() {

    }

    public Reference(String json, ReferenceTypeDAO referenceTypeDAO) throws ReferenceException {
        JSONObject reference = new JSONObject(json);
        JSONObject refFields = reference.getJSONObject("fields");
        Set<Attribute> attributes = new HashSet<>();
        Set<String> requiredAttrs = new HashSet<>(referenceTypeDAO.getRequiredFields(reference.getString("type")));
        Set<String> allAttrs = new HashSet<>(referenceTypeDAO.getOptionalFields(reference.getString("type")));
        allAttrs.addAll(requiredAttrs);
        for (String key : refFields.keySet()) {
            if (requiredAttrs.contains(key)) {
                requiredAttrs.remove(key);
            }
            if (!allAttrs.contains(key)) {
                throw new ReferenceException("Invalid field '" + key + "'");
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
                throw new ReferenceException("Missing fields " + str);
            }
            throw new ReferenceException("Missing field '" + requiredAttrs.iterator().next() + "'");
        }
        Set<String> tags = new HashSet<>();
        JSONArray arr = reference.getJSONArray("tags");
        for (int i = 0; i < arr.length(); i++) {
            tags.add(arr.getString(i));
        }
        if(!reference.has("name")) {
            reference.put("name", "missing-name");
        }
        this.type = reference.getString("type");
        this.bibtexName = reference.getString("name");
        this.createdAt = Date.from(Instant.now());
        this.updatedAt = null;
        this.attributes = attributes;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBibtexName() {
        return bibtexName;
    }

    public void setBibtexName(String bibtexName) {
        this.bibtexName = bibtexName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public JSONObject toJSON() {
        JSONObject jobj = new JSONObject();
        jobj.put("id", getId());
        jobj.put("name", getBibtexName());
        jobj.put("created_at", getCreatedAt());
        jobj.put("updated_at", getUpdatedAt());
        jobj.put("type", getType());
        JSONObject attrs = new JSONObject();
        for (Attribute attr : getAttributes()) {
            attrs.put(attr.getAttributeType(), attr.getValue());
        }
        jobj.put("fields", attrs);
        jobj.put("tags", new ArrayList<>(getTags()));
        jobj.put("success", true);
        return jobj;
    }

    public String toBiBTeX() {
        StringBuilder str = new StringBuilder();
        str.append('@');
        str.append(getType().substring(0, 1).toUpperCase());
        str.append(getType().substring(1));
        str.append('{');
        str.append(getBibtexName());
        str.append(",\n");
        TreeMap<String, String> attrs = new TreeMap<>();
        for(Attribute attr : getAttributes())
            attrs.put(attr.getAttributeType(), attr.getValue());
        if(getAttributes().size() > 0) {
            str.append("  ");
            StringJoiner stringJoiner = new StringJoiner(",\n  ");
            for(String attribute : attrs.keySet()) {
                stringJoiner.add(attribute + " = \"" +
                        attrs.get(attribute) + "\"");
            }
            str.append(stringJoiner);
            str.append("\n");
        }
        str.append('}');
        return BibtexUtils.escapeBiBTeXString(str.toString());
    }

    public class ReferenceException extends Throwable {
        private String errorMessage;

        public ReferenceException(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
