package org.jcaki;

import org.junit.Assert;
import org.junit.Test;

public class TestBytes {

    byte[] ba = {0x7E, (byte) 0xAC, (byte) 0x8A, (byte) 0x93};
    int bigEndianInt = 0x7EAC8A93;
    int littleEndianInt = 0x938AAC7E;


    @Test
    public void testToInt() {
        Assert.assertEquals(Bytes.toInt(ba, true), bigEndianInt);
        Assert.assertEquals(Bytes.toInt(ba, false), littleEndianInt);
        Assert.assertEquals(Bytes.toInt((byte) 0x7E, (byte) 0xAC, (byte) 0x8A, (byte) 0x93, true), bigEndianInt);
        Assert.assertEquals(Bytes.toInt((byte) 0x7E, (byte) 0xAC, (byte) 0x8A, (byte) 0x93, false), littleEndianInt);
    }

    @Test
    public void testToByte() {
        byte[] baReverse = {(byte) 0x93, (byte) 0x8A, (byte) 0xAC, 0x7E};
        Assert.assertArrayEquals(Bytes.toByteArray(bigEndianInt, true), ba);
        Assert.assertArrayEquals(Bytes.toByteArray(bigEndianInt, false), baReverse);
        Assert.assertArrayEquals(Bytes.toByteArray(littleEndianInt, false), ba);
        Assert.assertArrayEquals(Bytes.toByteArray(littleEndianInt, true), baReverse);
    }

    @Test
    public void testToByteShort() {
        byte[] baShort = {0x43,(byte) 0xAC};
        byte[] baShortReverse = {(byte) 0xAC, 0x43};
        short bigEndianShort = 0x43AC;
        short littleEndianShort = (short)0xAC43;
        Assert.assertArrayEquals(Bytes.toByteArray(bigEndianShort, true), baShort);
        Assert.assertArrayEquals(Bytes.toByteArray(bigEndianShort, false), baShortReverse);
        Assert.assertArrayEquals(Bytes.toByteArray(littleEndianShort, false), baShort);
        Assert.assertArrayEquals(Bytes.toByteArray(littleEndianShort, true), baShortReverse);
    }

    @Test
    public void testToIntArray() {
        int[] intArr = {0x7EAC8A93, 0x66AABBCC};
        int[] intArrReverse = {0x938AAC7E, 0xCCBBAA66, };
        byte[] barr = {0x7E, (byte) 0xAC, (byte) 0x8A, (byte) 0x93, 0x66, (byte) 0xAA, (byte) 0xBB, (byte) 0xCC};
        Assert.assertArrayEquals(Bytes.toIntArray(barr, barr.length,true), intArr);
        Assert.assertArrayEquals(Bytes.toIntArray(barr, barr.length, false), intArrReverse);
    }

    @Test
    public void testToByteArray() {
        byte[] baBe = {0x7E, (byte) 0xAC, (byte) 0x8A, (byte) 0x93};
        byte[] baLe = {(byte) 0xAC, 0x7E, (byte) 0x93, (byte) 0x8A};
        short[] sarr = {0x7EAC, (short) 0x8A93};

        Assert.assertArrayEquals(Bytes.toByteArray(sarr, sarr.length, true), baBe);
        Assert.assertArrayEquals(Bytes.toByteArray(sarr, sarr.length, false), baLe);
    }

    @Test
    public void testToShort() {
        byte[] barr = {0x7E, (byte) 0xAC, (byte) 0x8A, (byte) 0x93};
        short[] sarrBe = {0x7EAC, (short) 0x8A93};
        short[] sarrLe = {(short) 0xAC7E, (short) 0x938A};
        Assert.assertArrayEquals(Bytes.toShortArray(barr, barr.length, true), sarrBe);
        Assert.assertArrayEquals(Bytes.toShortArray(barr, barr.length, false), sarrLe);
    }

    @Test
    public void testHexDump() {
        byte[] barr = {0x7E, (byte) 0xAC, (byte) 0x8A, (byte) 0x93, 0x66, (byte) 0xAA, (byte) 0xBB, (byte) 0xCC};
        Bytes.hexDump(System.out, barr, 3);
    }
}
