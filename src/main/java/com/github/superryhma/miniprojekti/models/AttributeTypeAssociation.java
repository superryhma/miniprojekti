package com.github.superryhma.miniprojekti.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Dependency")
@IdClass(AttributeTypeAssociationId.class)
public class AttributeTypeAssociation implements Serializable {
	
	@Id
	protected int reference_type;
	
	@Id
	protected int attribute_type;
	
	@Column(name="required")
	protected Boolean required;
	
	@JoinColumn(name = "reference_type", updatable = false, insertable = false)
	protected ReferenceType referenceType;
	
	@JoinColumn(name = "attribute_type", updatable = false, insertable = false)
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
