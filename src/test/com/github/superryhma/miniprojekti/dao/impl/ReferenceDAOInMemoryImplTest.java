package com.github.superryhma.miniprojekti.dao.impl;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import org.junit.Before;
import org.junit.Test;

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
}