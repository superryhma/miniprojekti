package com.github.superryhma.miniprojekti;

import com.github.superryhma.miniprojekti.utils.BibtexUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author markus
 */
public class BibtexUtilsTest {
    
    public BibtexUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void noEscapeCharacters(){
        String s = "normal text";
        assertEquals(s, BibtexUtils.escapeBiBTeXString(s));
    }

    @Test
    public void onlyEscapeCharacters(){
        String s = "ÄÅÖåå";
        String expected = "\\\"{A}\\AA\\\"{O}\\aa\\aa";
        String result = BibtexUtils.escapeBiBTeXString(s);
        
        assertEquals(expected, result);
        
    }

    
    @Test
    public void hasEscapeCharacters(){
        String s = "Väinö Jääskeläinen";
        String expected = "V\\\"{a}in\\\"{o} J\\\"{a}\\\"{a}skel\\\"{a}inen";
        String result = BibtexUtils.escapeBiBTeXString(s);
        
        assertEquals(expected, result);
    }

}
