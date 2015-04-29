package com.github.superryhma.miniprojekti.models;

import org.junit.Test;
import org.junit.Assert;

public class AttributeTest {
    private void constructorTestEqual(String attributeType, String value) {
        Attribute attribute = new Attribute(attributeType, value);
        Assert.assertEquals(attributeType, attribute.getAttributeType());
        Assert.assertEquals(value, attribute.getValue());
    }

    @Test
    public void constructorTest() {
        constructorTestEqual("type", "value");
        constructorTestEqual("anotherType", "anotherValue");
        constructorTestEqual("special characters", "special characters");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsOnNullType() {
        new Attribute(null, "not null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsOnNullName() {
        new Attribute("not null", null);
    }

    @Test
    public void attributeEqualsSelf() {
        Attribute self = new Attribute("self", "equal");
        Assert.assertTrue(self.equals(self));
    }

    @Test
    public void attributeEqualsIdentical() {
        Attribute self = new Attribute("identical", "equal");
        Attribute other = new Attribute("identical", "equal");
        Assert.assertTrue(self.equals(other));
    }

    @Test
    public void attributeDoesNotEqualNull() {
        Attribute self = new Attribute("identical", "equal");
        Assert.assertFalse(self.equals(null));
    }

    @Test
    public void attributeDoesNotEqualOtherType() {
        Attribute self = new Attribute("identical", "equal");
        Attribute other = new Attribute("otherType", "equal");
        Assert.assertFalse(self.equals(other));
    }

    @Test
    public void attributeEqualsOtherName() {
        Attribute self = new Attribute("identical", "other");
        Attribute other = new Attribute("identical", "equal");
        Assert.assertTrue(self.equals(other));
    }

    @Test
    public void attributeDoesNotEqualOtherObject() {
        Attribute self = new Attribute("identical", "other");
        Assert.assertFalse(self.equals(new String("other object")));
    }

    @Test
    public void hashCodeEquals() {
        Attribute self = new Attribute("identical", "same");
        Attribute other = new Attribute("identical", "not same");
        Assert.assertEquals(self.hashCode(), other.hashCode());
    }
}