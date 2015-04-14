package com.github.superryhma.miniprojekti.models;

import java.util.Date;
import java.util.Set;

public class Reference {

	protected int id;
	protected String bibtexname;
	protected Date createdAt;
	protected Date updatedAt;
	protected Set<Attribute> attributes;
	protected Set<Tag> tags;
	protected ReferenceType type;
	protected Date created_at;
	protected Date updated_at;

	public Reference() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public ReferenceType getType() {
		return type;
	}

	public void setType(ReferenceType type) {
		this.type = type;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
}
