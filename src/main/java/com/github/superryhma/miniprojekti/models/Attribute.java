package com.github.superryhma.miniprojekti.models;

public class Attribute {

    private final String attributeType;
    private final String value;

    public Attribute(String attributeType, String value) {
        this.attributeType = attributeType;
        this.value = value;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Attribute attribute = (Attribute) o;

        if (attributeType != null) {
            return attributeType.equals(attribute.attributeType);
        } else {
            return attribute.attributeType == null;
        }
    }

    @Override
    public int hashCode() {
        return attributeType != null ? attributeType.hashCode() : 0;
    }
}
