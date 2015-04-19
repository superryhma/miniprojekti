package com.github.superryhma.miniprojekti.dao.impl.db;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.superryhma.miniprojekti.dao.ReferenceTypeDAO;
import com.github.superryhma.miniprojekti.dao.impl.db.models.AttributeType;
import com.github.superryhma.miniprojekti.dao.impl.db.models.ReferenceTypeDb;
import com.github.superryhma.miniprojekti.dbc.Dbc;
import com.github.superryhma.miniprojekti.models.ReferenceType;

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
        List<ReferenceTypeDb> typesList = ReferenceTypeDb.findAll();
        Set<ReferenceType> typesSet = new HashSet<>();
        String name;
        for (ReferenceTypeDb type : typesList) {
            name = (String) type.get("name");
            typesSet.add(new ReferenceType(name, getFields(name, true),
                    getFields(name, false)));
        }
        Dbc.close();
        return typesSet;
    }

    private Set<String> getFields(String type, boolean required) {
        Dbc.open();
        ReferenceTypeDb rt = ReferenceTypeDb.findFirst("name = ?", type);
        List<AttributeType> typesList = rt.get(AttributeType.class,
                "required = ?", required);
        Set<String> typesSet = new HashSet<String>();
        for (AttributeType t : typesList) {
            typesSet.add(t.getString("name"));
        }
        Dbc.close();
        return typesSet;
    }

}
