package com.github.superryhma.miniprojekti.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Reference {

    private int id;
    private String type;
    private String bibtexname;
    private Date createdAt;
    private Date updatedAt;
    private Set<Attribute> attributes;
    private Set<String> tags;

    public Reference(String type, String bibtexname, Date createdAt, Date updatedAt, Set<Attribute> attributes, Set<String> tags) {
        this.type = type;
        this.bibtexname = bibtexname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getBibtexname() {
        return bibtexname;
    }

    public void setBibtexname(String bibtexname) {
        this.bibtexname = bibtexname;
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
}
