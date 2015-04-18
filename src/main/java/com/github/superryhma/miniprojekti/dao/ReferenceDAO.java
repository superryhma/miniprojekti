package com.github.superryhma.miniprojekti.dao;

import com.github.superryhma.miniprojekti.models.Reference;

import java.util.List;

public interface ReferenceDAO {
    List<Reference> getReferences();

    Reference getReferenceById(int id);

    Reference addReference(Reference reference);

    Reference updateReference(int id, Reference reference);

    boolean deleteReference(int id);
}
