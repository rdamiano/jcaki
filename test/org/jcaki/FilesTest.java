package org.jcaki;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.File;

public class FilesTest {
    @Test
    public void MD5CalculationTest() throws IOException {
        assertEquals("873362e429c261e3596ad1d387ad152e",
                Numbers.toHex(Files.calculateMD5(new File("test/file_for_md5.txt"))));
    }

    @Test
    public void fileAppendTest() throws IOException {
        Files.appendFiles(new File("apended.txt"), new File("test/file_for_md5.txt"), new File("test/multi_line_text_file.txt"));
    }

    @Test
    public void testHexDump() throws IOException {
        Files.hexDump(new File("test/multi_line_text_file.txt"), -1);
    }

}
