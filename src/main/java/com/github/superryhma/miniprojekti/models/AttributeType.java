package com.github.superryhma.miniprojekti.models;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class AttributeType {

	@Id
	@GeneratedValue
	protected int id;

	protected String name;

	@OneToMany(mappedBy = "attribute_type")
	private List<AttributeTypeAssociation> referenceTypes;

	public AttributeType() {
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

	public List<AttributeTypeAssociation> getReferenceTypes() {
		return referenceTypes;
	}

	public void setReferenceTypes(List<AttributeTypeAssociation> referenceTypes) {
		this.referenceTypes = referenceTypes;
	}
	
}
