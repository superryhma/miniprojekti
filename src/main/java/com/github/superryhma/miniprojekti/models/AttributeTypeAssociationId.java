package com.github.superryhma.miniprojekti.models;

import java.io.Serializable;

import javax.persistence.Id;

public class AttributeTypeAssociationId implements Serializable {

	protected int reference_type;

	protected int attribute_type;

	public int hashCode() {
		return (int) (reference_type + attribute_type);
	}

	public boolean equals(Object object) {
		if (object instanceof AttributeTypeAssociationId) {
			AttributeTypeAssociationId otherId = (AttributeTypeAssociationId) object;
			return (otherId.reference_type == this.reference_type)
					&& (otherId.attribute_type == this.attribute_type);
		}
		return false;
	}

}
