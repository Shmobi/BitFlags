package com.litts.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitFlagTest {

    @Test
    public void testCreation() {
        for (int i = 0; i < 63; i++) {
            BitFlag bitFlag = BitFlag.create(i);
            assertEquals((long) Math.pow(2, i), bitFlag.getValue());
        }
        assertEquals(Long.MIN_VALUE, BitFlag.create(63).getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreation_outOfBounds() {
        BitFlag.create(64);
        fail("Suitable range for bit flags should be 0 - 63");
    }

    @Test
    public void testSetTo() {
        long flags = 0;
        for (int i = 0; i < 64; i++) {
            BitFlag flag = BitFlag.create(i);
            assertNotEquals((flags & flag.getValue()), flag.getValue());
            flags = flag.setTo(flags);
            assertEquals((flags & flag.getValue()), flag.getValue());
        }
    }

    @Test
    public void testRemoveFrom() {
        long allFlags = 0b1111111111111111111111111111111111111111111111111111111111111111L;
        for (int i = 0; i < 64; i++) {
            BitFlag flag = BitFlag.create(i);
            assertEquals((allFlags & flag.getValue()) , flag.getValue());
            allFlags = flag.removeFrom(allFlags);
            assertNotEquals((allFlags & flag.getValue()) ,flag.getValue());
        }
    }

    @Test
    public void testNegate() {
        long flags = 0;
        for (int i = 0; i < 64; i++) {
            BitFlag flag = BitFlag.create(i);
            assertNotEquals((flags & flag.getValue()) , flag.getValue());
            flags = flag.negate(flags);
            assertEquals((flags & flag.getValue()) , flag.getValue());
            flags = flag.negate(flags);
            assertNotEquals((flags & flag.getValue()) , flag.getValue());
        }
    }

    @Test
    public void testIsSet() {
        long noFlags = 0;
        long allFlags = 0b1111111111111111111111111111111111111111111111111111111111111111L;
        for (int i = 0; i < 64; i++) {
            BitFlag flag = BitFlag.create(i);
            assertFalse(flag.isSet(noFlags));
            assertTrue(flag.isSet(allFlags));
        }
    }

    @Test
    public void testEquals() {
        BitFlag flag1 = BitFlag.create(0);
        BitFlag flag2 = BitFlag.create(0);
        BitFlag flag3 = BitFlag.create(1);
        assertEquals(flag1, flag2);
        assertNotEquals(flag1, flag3);
        assertNotEquals(flag2, flag3);
    }

    @Test
    public void testHashcode() {
        for (int i = 0; i < 64; i++) {
            BitFlag flag = BitFlag.create(i);
            assertEquals(Long.hashCode(flag.getValue()), flag.hashCode());
        }
    }

}
