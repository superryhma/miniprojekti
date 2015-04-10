package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Reference {

	@Id
	@GeneratedValue
	protected int id;

	protected String bibtexname;

	@Column(name = "created_at")
	protected Date createdAt;

	@Column(name = "updated_at")
	protected Date updatedAt;

	//KORJAA
	protected ReferenceType referenceType;

	@OneToMany(mappedBy="reference")
	protected List<Attribute> fields;

	@OneToMany(mappedBy="reference")
	protected List<Tag> tags;

	public Reference() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return bibtexname;
	}

	public void setName(String name) {
		this.bibtexname = name;
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

	public ReferenceType getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(ReferenceType referenceType) {
		this.referenceType = referenceType;
	}

	public List<Attribute> getFields() {
		return fields;
	}

	public void setFields(ArrayList<Attribute> fields) {
		this.fields = fields;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}
}