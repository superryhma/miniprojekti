/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.superryhma.miniprojekti.dao;

import com.github.superryhma.miniprojekti.models.Reference;
import java.util.List;

/**
 *
 * @author jgke
 */
public interface ReferenceDAO {
    List<Reference> getReferences();
    Reference getReferenceById(int id);
    Reference addReference(Reference reference);
    Reference updateReference(int id, Reference reference);
    boolean deleteReference(int id);
}
