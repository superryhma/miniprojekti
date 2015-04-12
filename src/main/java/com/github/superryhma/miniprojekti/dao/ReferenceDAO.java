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
    public List<Reference> getReferences();
    public Reference getReferenceById(int id);
    public Reference addReference(Reference reference);
}
