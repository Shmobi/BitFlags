package com.litts.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitFlagUtilTest {

    @Test
    public void testAreSet() {
        long flags = 3;
        BitFlag flag1 = BitFlag.create(0);
        BitFlag flag2 = BitFlag.create(1);
        BitFlag flag3 = BitFlag.create(2);
        assertTrue(BitFlagUtil.areSet(flags, flag1, flag2));
        assertFalse(BitFlagUtil.areSet(flags, flag1, flag2, flag3));
    }

    @Test
    public void testAreAnySet() {
        long flags = 1;
        long emptyFlags = 0;
        BitFlag flag1 = BitFlag.create(0);
        BitFlag flag2 = BitFlag.create(1);
        assertTrue(BitFlagUtil.areAnySet(flags, flag1, flag2));
        assertFalse(BitFlagUtil.areAnySet(emptyFlags, flag1, flag2));
    }

    @Test
    public void testSetAll() {
        long flags = 0;
        BitFlag flag1 = BitFlag.create(0);
        BitFlag flag2 = BitFlag.create(1);
        flags = BitFlagUtil.setAll(flags, flag1, flag2);
        assertEquals(3, flags);
    }

    @Test
    public void testRemoveAll() {
        long flags = 3;
        BitFlag flag1 = BitFlag.create(0);
        BitFlag flag2 = BitFlag.create(1);
        flags = BitFlagUtil.removeAll(flags, flag1, flag2);
        assertFalse(BitFlagUtil.areAnySet(flags, flag1, flag2));
    }

    @Test
    public void testNegateAll() {
        long flags = 0;
        BitFlag flag1 = BitFlag.create(0);
        BitFlag flag2 = BitFlag.create(1);
        flags = BitFlagUtil.negateAll(flags, flag1, flag2);
        assertEquals(3, flags);
        flags = BitFlagUtil.negateAll(flags, flag1, flag2);
        assertEquals(0, flags);
    }

}
