package com.github.superryhma.miniprojekti.dao.impl;

import com.github.superryhma.miniprojekti.dao.TypeDAO;
import com.github.superryhma.miniprojekti.models.AttributeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TypeDAOInMemoryImpl implements TypeDAO {
    private final Map<String, Set<String>> requiredTypes;
    private final Map<String, Set<String>> optionalTypes;

    public TypeDAOInMemoryImpl() {
        requiredTypes = new HashMap<>();
        optionalTypes = new HashMap<>();
        requiredTypes.put("article", new HashSet<>(Arrays.asList(
                "author", "title", "journal", "year", "volume")));
        optionalTypes.put("article", new HashSet<>(Arrays.asList(
                "number", "pages", "month", "note", "key")));
        requiredTypes.put("book", new HashSet<>(Arrays.asList(
                "author", "title", "year", "publisher")));
        optionalTypes.put("book", new HashSet<>(Arrays.asList(
                "volume", "series", "address", "edition", "month", "note", "key")));
    }

    @Override
    public Set<String> getRequiredFields(String type) {
        if(requiredTypes.containsKey(type))
            return requiredTypes.get(type);
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getOptionalFields(String type) {
        if(optionalTypes.containsKey(type))
            return optionalTypes.get(type);
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<AttributeType> getTypes() {
        Set<AttributeType> attributeTypes = new HashSet<>();
        for(String s : requiredTypes.keySet()) {
            attributeTypes.add(new AttributeType(s, requiredTypes.get(s), optionalTypes.get(s)));
        }
        return attributeTypes;
    }
}