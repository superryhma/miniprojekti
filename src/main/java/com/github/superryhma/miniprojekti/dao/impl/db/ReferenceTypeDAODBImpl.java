package com.github.superryhma.miniprojekti.dao.impl.db;

import com.github.superryhma.miniprojekti.dao.ReferenceTypeDAO;
import com.github.superryhma.miniprojekti.dao.impl.db.models.AttributeType;
import com.github.superryhma.miniprojekti.dao.impl.db.models.ReferenceTypeDb;
import com.github.superryhma.miniprojekti.dbc.Dbc;
import com.github.superryhma.miniprojekti.models.ReferenceType;

import java.util.Set;
import java.util.stream.Collectors;

public class ReferenceTypeDAODBImpl implements ReferenceTypeDAO {

    @Override
    public Set<String> getRequiredFields(String type) {
        return getFields(type, true);
    }

    @Override
    public Set<String> getOptionalFields(String type) {
        return getFields(type, false);
    }

    @Override
    public Set<ReferenceType> getTypes() {
        Dbc.open();
        Set<ReferenceType> typesSet = ReferenceTypeDb.findAll().stream()
                .map(type -> (String) type.get("name"))
                .map(this::loadReferenceType)
                .collect(Collectors.toSet());
        Dbc.close();
        return typesSet;
    }

    private ReferenceType loadReferenceType(String name) {
        return new ReferenceType(name, getFields(name, true), getFields(name, false));
    }

    private Set<String> getFields(String type, boolean required) {
        Dbc.open();
        Set<String> typesSet = ReferenceTypeDb.findFirst("name = ?", type)
                .get(AttributeType.class, "required = ?", required).stream()
                .map(t -> t.getString("name"))
                .collect(Collectors.toSet());
        Dbc.close();
        return typesSet;
    }

}
