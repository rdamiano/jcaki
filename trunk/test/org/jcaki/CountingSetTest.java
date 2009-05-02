package org.jcaki;

import org.junit.Test;

import junit.framework.Assert;

public class CountingSetTest {

    @Test
    public void testGenerate() {
        CountingSet<String> histogram = new CountingSet<String>();
        histogram.add("Apple", "Pear", "Plum", "Apple", "Apple", "Grape", "Pear");
        Assert.assertEquals(3, histogram.getCount("Apple"));
        Assert.assertEquals(2, histogram.getCount("Pear"));
        Assert.assertEquals(1, histogram.getCount("Plum"));
    }
}
