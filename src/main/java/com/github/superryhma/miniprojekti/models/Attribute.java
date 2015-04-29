package com.github.superryhma.miniprojekti.models;

public class Attribute {

    private final String attributeType;
    private final String value;

    public Attribute(String attributeType, String value) {
        if (attributeType == null || value == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
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

        return attributeType.equals(attribute.attributeType);

    }

    @Override
    public int hashCode() {
        return attributeType.hashCode();
    }
}

