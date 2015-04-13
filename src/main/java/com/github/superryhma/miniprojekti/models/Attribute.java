package com.github.superryhma.miniprojekti.models;

import java.io.Serializable;


public class Attribute implements Serializable {


	protected int id;

	protected Reference reference;

	protected AttributeType attribute_type;

	protected String value;

	public Attribute() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public AttributeType getAttribute_type() {
		return attribute_type;
	}

	public void setAttribute_type(AttributeType attribute_type) {
		this.attribute_type = attribute_type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}