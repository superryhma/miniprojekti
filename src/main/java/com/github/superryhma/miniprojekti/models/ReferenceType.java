package com.github.superryhma.miniprojekti.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ReferenceType implements Serializable {

	@Id
	@GeneratedValue
	protected int id;
	
	protected String name;
	
	@OneToMany(mappedBy="reference_type")
	protected List<AttributeTypeAssociation> attibuteTypes;

	public ReferenceType() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AttributeTypeAssociation> getAttibuteTypes() {
		return attibuteTypes;
	}

	public void setAttibuteTypes(List<AttributeTypeAssociation> attibuteTypes) {
		this.attibuteTypes = attibuteTypes;
	}
	
}
