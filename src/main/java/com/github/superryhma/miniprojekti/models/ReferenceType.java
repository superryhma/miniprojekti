package com.github.superryhma.miniprojekti.models;

import java.util.Set;

public class ReferenceType {
    private String name;
    private Set<String> requiredAttributes;
    private Set<String> optionalAttributes;

    public ReferenceType() {
    }

    public ReferenceType(String name, Set<String> requiredAttributes, Set<String> optionalAttributes) {
        this.name = name;
        this.requiredAttributes = requiredAttributes;
        this.optionalAttributes = optionalAttributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        ReferenceType that = (ReferenceType) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
