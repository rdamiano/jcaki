package org.jcaki;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.jcaki.Numbers.*;


public class NumbersTest {

    //    ~~~~~~~~~~~ toHex ~~~~~~~~~~~~~~

    @Test
    public void toHextTest() {
        assertEquals(toHex((byte) 0), "0");
        assertEquals(toHex((byte) 1), "1");
        assertEquals(toHex((byte) 15), "f");
        assertEquals(toHex((byte) 127), "7f");
        assertEquals(toHex((byte) 0xcc), "cc");
        // arrays
        assertEquals(toHex(new byte[]{(byte) 0x01}), "1");
        assertEquals(toHex(new byte[]{(byte) 0xcc}), "cc");
        assertEquals(toHex(new byte[]{0x00, 0x00}), "0");
        assertEquals(toHex(new byte[]{0x01, 0x1f, (byte) 0xcc}), "11fcc");
        assertEquals(toHex(new byte[]{0x01, 0x1f, 0x00}), "11f00");
        assertEquals(toHex(new byte[]{0x00, 0x01, 0x1f, 0x01, 0x00, 0x00}), "11f010000");
    }

    @Test(expected = NullPointerException.class)
    public void toHexExceptionTest() {
        toHex(null);
    }

    //    ~~~~~~~~~~~ toHexWithZerosWithZeros ~~~~~~~~~~~~~~
    
    @Test
    public void toHexWithZerostWithZerosTest() {
        assertEquals(toHexWithZeros((byte) 0), "00");
        assertEquals(toHexWithZeros((byte) 1), "01");
        assertEquals(toHexWithZeros((byte) 15), "0f");
        assertEquals(toHexWithZeros((byte) 127), "7f");
        assertEquals(toHexWithZeros((byte) 0xcc), "cc");
        // arrays
        assertEquals(toHexWithZeros(new byte[]{(byte) 0x01}), "01");
        assertEquals(toHexWithZeros(new byte[]{(byte) 0xcc}), "cc");
        assertEquals(toHexWithZeros(new byte[]{0x00, 0x00}), "0000");
        assertEquals(toHexWithZeros(new byte[]{0x01, 0x1f, (byte) 0xcc}), "011fcc");
        assertEquals(toHexWithZeros(new byte[]{0x01, 0x1f, 0x00}), "011f00");
        assertEquals(toHexWithZeros(new byte[]{0x00, 0x01, 0x1f, 0x01, 0x00, 0x00}), "00011f010000");
    }

    @Test(expected = NullPointerException.class)
    public void toHexWithZerosExceptionTest() {
        toHexWithZeros(null);
    }

    @Test
    public void DigitCountTest() {
        assertEquals(3,Numbers.digitCount(100));
        assertEquals(3,Numbers.digitCount(123));
        assertEquals(1,Numbers.digitCount(1));
        assertEquals(1,Numbers.digitCount(-1));
        assertEquals(1,Numbers.digitCount(0));
        assertEquals(10,Numbers.digitCount(1234567890));
    }
}
