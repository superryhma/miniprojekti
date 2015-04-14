package com.github.superryhma.miniprojekti.models;

import java.util.List;

public class ReferenceType {

	protected int id;
	protected String name;
	protected List<String> required;
	protected List<String> optional;

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

	public List<String> getRequired() {
		return required;
	}

	public void setRequired(List<String> required) {
		this.required = required;
	}

	public List<String> getOptional() {
		return optional;
	}

	public void setOptional(List<String> optional) {
		this.optional = optional;
	}
	
}
