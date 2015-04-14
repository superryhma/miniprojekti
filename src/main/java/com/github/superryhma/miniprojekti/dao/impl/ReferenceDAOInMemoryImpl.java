/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.superryhma.miniprojekti.dao.impl;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.models.Reference;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jgke
 */
@Component
public class ReferenceDAOInMemoryImpl implements ReferenceDAO {

    private Map<Integer, Reference> references;
    int nextId;

    @Autowired
    public ReferenceDAOInMemoryImpl() {
        references = new HashMap<>();
        nextId = 0;
    }

    @Override
    public List<Reference> getReferences() {
        return new ArrayList<>(references.values());
    }

    @Override
    public Reference getReferenceById(int id) {
        return references.getOrDefault(id, null);
    }

    @Override
    public Reference addReference(Reference reference) {
        reference.setId(nextId);
        reference.setCreatedAt(Date.from(Instant.now()));
        references.put(nextId++, reference);
        return reference;
    }

    @Override
    public Reference updateReference(int id, Reference reference) {
        return null;
    }

    @Override
    public boolean deleteReference(int id) {
        return false;
    }

}