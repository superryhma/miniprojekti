package com.github.superryhma.miniprojekti.dao.impl;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.models.Attribute;
import com.github.superryhma.miniprojekti.models.Reference;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class ReferenceDAOInMemoryImplTest {

    private ReferenceDAO dao;

    @Before
    public void setUp() {
        dao = new ReferenceDAOInMemoryImpl();
    }

    @Test
    public void emptyDatabaseReturnsEmpty() {
        assertEquals(dao.getReferences().size(), 0);
    }

    @Test
    public void canAddReferenceToDatabase() {
        Reference ref = new Reference("article", "test", Date.from(Instant.now()), null, new HashSet<>(), new HashSet<>());
        dao.addReference(ref);
        assertEquals(dao.getReferences().size(), 1);
    }
}