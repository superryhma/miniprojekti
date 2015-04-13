package com.github.superryhma.miniprojekti.models;

import java.io.Serializable;



public class AttributeTypeAssociation implements Serializable {
	
	protected int reference_type;
	
	protected int attribute_type;
	
	protected Boolean required;
	
	protected ReferenceType referenceType;
	
	protected AttributeType attributeType;

	public AttributeTypeAssociation() {
	}

	public int getReference_type() {
		return reference_type;
	}

	public void setReference_type(int reference_type) {
		this.reference_type = reference_type;
	}

	public int getAttribute_type() {
		return attribute_type;
	}

	public void setAttribute_type(int attribute_type) {
		this.attribute_type = attribute_type;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public ReferenceType getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(ReferenceType referenceType) {
		this.referenceType = referenceType;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}
	
}
