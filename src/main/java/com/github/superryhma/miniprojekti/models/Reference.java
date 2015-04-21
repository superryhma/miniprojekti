package com.github.superryhma.miniprojekti.models;

import com.github.superryhma.miniprojekti.utils.BibtexUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;

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
        if(getAttributes().size() > 0) {
            str.append("  ");
            StringJoiner stringJoiner = new StringJoiner(",\n  ");
            for(Attribute attribute : getAttributes()) {
                stringJoiner.add(attribute.getAttributeType() +
                        " = \"" + attribute.getValue() + "\"");
            }
            str.append(stringJoiner);
            str.append("\n");
        }
        str.append('}');
        return BibtexUtils.escapeBiBTeXString(str.toString());
    }
}
