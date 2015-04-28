/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.superryhma.miniprojekti;

import com.github.superryhma.miniprojekti.utils.Sequence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.model.MultipleFailureException;

/**
 *
 * @author markus
 */
public class SequenceTest {
    
    Sequence sequence;
    
    public SequenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        sequence = new Sequence();
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void firstCharacters(){
        Assert.assertEquals("", sequence.next());
        for(char c = 'a'; c <= 'z'; c++){
            String result = sequence.next();
            Assert.assertEquals("" + c, result);
        }
    }
    
    @Test
    public void secondCharacters(){
        sequence.next();
        for(char c = 'a'; c <= 'z'; c++){
            sequence.next();
        }

        for(char c = 'a'; c <= 'z'; c++){
            String result = sequence.next();
            Assert.assertEquals("a" + c, result);
        }
        for(char c = 'a'; c <= 'z'; c++){
            String result = sequence.next();
            Assert.assertEquals("b" + c, result);
        }
    }
}
