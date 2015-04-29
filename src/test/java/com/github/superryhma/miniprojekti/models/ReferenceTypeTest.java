package com.github.superryhma.miniprojekti.models;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

public class ReferenceTypeTest {
    private void testResults(String name, Set<String> requiredAttributes, Set<String> optionalAttributes) {
        ReferenceType attribute = new ReferenceType(name, requiredAttributes, optionalAttributes);
        Assert.assertEquals(requiredAttributes, attribute.getRequiredAttributes());
        Assert.assertEquals(optionalAttributes, attribute.getOptionalAttributes());
        Assert.assertEquals(name, attribute.getName());
        attribute.setName("other name");
        Assert.assertEquals(attribute.getName(), "other name");
    }

    @Test
    public void constructorTest() {
        Set<String> requiredAttributes = new HashSet<String>() {
            {
                add("title");
                add("author");
            }
        };
        Set<String> optionalAttributes = new HashSet<String>() {
            {
                add("year");
                add("url");
            }
        };
        testResults("article", requiredAttributes, optionalAttributes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsOnNullName() {
        new ReferenceType(null, new HashSet<>(), new HashSet<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsOnNullRequired() {
        new ReferenceType("name", null, new HashSet<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsOnNullOptional() {
        new ReferenceType("name", new HashSet<>(), null);
    }

    @Test
    public void attributeEqualsSelf() {
        Set<String> requiredAttributes = new HashSet<String>() {
            {
                add("foo");
                add("bar");
            }
        };
        Set<String> optionalAttributes = new HashSet<String>() {
            {
                add("optional");
                add("attributes");
            }
        };
        ReferenceType self = new ReferenceType("name", requiredAttributes, optionalAttributes);
        Assert.assertTrue(self.equals(self));
    }

    @Test
    public void attributeEqualsIdentical() {
        Set<String> requiredAttributes = new HashSet<String>() {
            {
                add("this");
                add("that");
            }
        };
        Set<String> optionalAttributes = new HashSet<String>() {
            {
                add("opt");
                add("attr");
            }
        };
        ReferenceType self = new ReferenceType("book", requiredAttributes, optionalAttributes);
        ReferenceType other = new ReferenceType("book", requiredAttributes, optionalAttributes);
        Assert.assertTrue(self.equals(other));
    }

    @Test
    public void attributeDoesNotEqualNull() {
        Set<String> requiredAttributes = new HashSet<String>() {
            {
                add("this");
                add("that");
            }
        };
        Set<String> optionalAttributes = new HashSet<String>() {
            {
                add("opt");
                add("attr");
            }
        };
        ReferenceType self = new ReferenceType("thing", requiredAttributes, optionalAttributes);
        Assert.assertFalse(self.equals(null));
    }

    @Test
    public void attributeDoesNotEqualOtherType() {
        Set<String> requiredAttributes = new HashSet<String>() {
            {
                add("this");
                add("that");
            }
        };
        Set<String> optionalAttributes = new HashSet<String>() {
            {
                add("opt");
                add("attr");
            }
        };
        ReferenceType self = new ReferenceType("article", requiredAttributes, optionalAttributes);
        ReferenceType other = new ReferenceType("book", requiredAttributes, optionalAttributes);
        Assert.assertFalse(self.equals(other));
    }

    @Test
    public void attributeDoesNotEqualOtherObject() {
        Set<String> requiredAttributes = new HashSet<String>() {
            {
                add("author");
                add("title");
            }
        };
        Set<String> optionalAttributes = new HashSet<String>() {
            {
                add("weight");
            }
        };
        ReferenceType self = new ReferenceType("article", requiredAttributes, optionalAttributes);
        Assert.assertFalse(self.equals(new String("other object")));
    }

    @Test
    public void hashCodeEquals() {
        Set<String> requiredAttributes = new HashSet<String>() {
            {
                add("author");
                add("title");
            }
        };
        Set<String> optionalAttributes = new HashSet<String>() {
            {
                add("weight");
            }
        };
        ReferenceType self = new ReferenceType("article", requiredAttributes, optionalAttributes);
        ReferenceType other = new ReferenceType("article", requiredAttributes, optionalAttributes);
        Assert.assertEquals(self.hashCode(), other.hashCode());
    }
}
