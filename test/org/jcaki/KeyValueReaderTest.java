package org.jcaki;

import org.junit.Test;
import org.junit.Assert;

import java.util.Map;
import java.io.File;
import java.io.IOException;

public class KeyValueReaderTest {

    @Test
    public void testReader() throws IOException {
        Map<String, String> map = new KeyValueReader(":")
                .loadFromFile(new File("test/key-value-colon-separator.txt"));
        Assert.assertEquals(map.size(), 4);
        Assert.assertTrue(TestUtil.containsAllKeys(map, "1", "2", "3", "4"));
        Assert.assertTrue(TestUtil.containsAllValues(map, "bir", "iki", "uc", "dort"));
    }
}
