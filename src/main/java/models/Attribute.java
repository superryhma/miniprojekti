package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Attribute {

	@Id
	@GeneratedValue
	protected int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reference")
	protected Reference reference;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute_type")
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