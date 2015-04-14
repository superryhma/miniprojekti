package com.github.superryhma.miniprojekti.models;

public class Attribute {

	private String attributeType;
	private String value;

	public Attribute(String attributeType, String value) {
		this.attributeType = attributeType;
		this.value = value;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Attribute attribute = (Attribute) o;

		return !(attributeType != null ? !attributeType.equals(attribute.attributeType) : attribute.attributeType != null);

	}

	@Override
	public int hashCode() {
		return attributeType != null ? attributeType.hashCode() : 0;
	}
}