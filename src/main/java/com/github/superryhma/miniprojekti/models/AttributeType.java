package com.github.superryhma.miniprojekti.models;

import java.util.Set;

public class AttributeType {
    private String type;
    private Set<String> requiredAttributes;
    private Set<String> optionalAttributes;

    public AttributeType(String type, Set<String> requiredAttributes, Set<String> optionalAttributes) {
        this.type = type;
        this.requiredAttributes = requiredAttributes;
        this.optionalAttributes = optionalAttributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getRequiredAttributes() {
        return requiredAttributes;
    }

    public void setRequiredAttributes(Set<String> requiredAttributes) {
        this.requiredAttributes = requiredAttributes;
    }

    public Set<String> getOptionalAttributes() {
        return optionalAttributes;
    }

    public void setOptionalAttributes(Set<String> optionalAttributes) {
        this.optionalAttributes = optionalAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeType that = (AttributeType) o;

        return !(type != null ? !type.equals(that.type) : that.type != null);

    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }
}
