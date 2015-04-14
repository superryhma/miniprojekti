package com.github.superryhma.miniprojekti.models;

public class Attribute {

	protected int id;
	protected int reference;
	protected int attribute_type;
	protected String value;

	public Attribute() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public int getAttribute_type() {
		return attribute_type;
	}

	public void setAttribute_type(int attribute_type) {
		this.attribute_type = attribute_type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}