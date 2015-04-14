package com.github.superryhma.miniprojekti.dao;

import com.github.superryhma.miniprojekti.models.AttributeType;

import java.util.Set;

public interface ReferenceTypeDAO {
    Set<String> getRequiredFields(String type);
    Set<String> getOptionalFields(String type);
    Set<AttributeType> getTypes();
}