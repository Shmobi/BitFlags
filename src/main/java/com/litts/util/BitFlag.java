package com.litts.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A single flag in a 64 bit field. Provides several operations to read and write its value.
 *
 * @see BitFlagUtil for multi flag operations
 */
public class BitFlag {

    private static final String EMPTY_64_BITS = "0000000000000000000000000000000000000000000000000000000000000000";
    private static final String BIT_FORMAT_REGEX = "(.{8})";
    private static final String BITFLAG_STRING_FORMAT = "Bitflag [%s]";
    private static final int MAX_INDEX = 63;
    private static final  int BINARY_BASE = 2;
    private final long value;

    private BitFlag(long value) {
        this.value = value;
    }

    /**
     * Creates a new instance of {@link BitFlag} representing the flag at the chosen index.<br>
     * The index may only range between 0 - 63.
     *
     * @param index The desired index of the flag
     * @return the created {@link BitFlag}. Not nullable
     * @throws IllegalArgumentException if the index is outside the valid range
     */
    @NotNull
    public static BitFlag create(int index) {
        if (index < 0 || index > MAX_INDEX) {
            throw new IllegalArgumentException("Index is outside of valid range 0 - 63. Was: " + index);
        }
        long value = index != MAX_INDEX ? (long) Math.pow(BINARY_BASE, index) : Long.MIN_VALUE;
        return new BitFlag(value);
    }

    /**
     * @return the long representation of this flag
     */
    public long getValue() {
        return value;
    }

    /**
     * Ads this flag to the provided flags if it wasn't already set
     *
     * @param flags The flags that this flag should be added to
     * @return the new flags value
     */
    public long setTo(long flags) {
        return flags | value;
    }

    /**
     * Removes this flag from the provided flags if it was set
     *
     * @param flags The flags that this flag should be removed from
     * @return the new flags value
     */
    public long removeFrom(long flags) {
        return flags & ~value;
    }

    /**
     * Negates this flag in the provided flags
     *
     * @param flags The flags in which this flag should be negated in
     * @return the new flags value
     */
    public long negate(long flags) {
        return flags ^ value;
    }

    /**
     * @param flags The flags that should be checked for this flag
     * @return true if this flag was set in the provided flags value
     */
    public boolean isSet(long flags) {
        return (flags & value) == value;
    }

    /**
     * @param obj The object to compare to. Null will always result in false
     * @return true if the provided Object is a {@link BitFlag} with the same {@link #getValue()} as this flag
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof BitFlag) {
            return value == ((BitFlag) obj).value;
        }
        return false;
    }

    /**
     * @return the {@link Long#hashCode(long)} for this flags {@link #getValue()}
     */
    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    /**
     * @return a String representation for this flag
     */
    @Override
    public String toString() {
        String binaryRepresentation = Long.toBinaryString(value); // binary representation without leading empty bits
        binaryRepresentation = EMPTY_64_BITS.substring(0, EMPTY_64_BITS.length() - binaryRepresentation.length()) + binaryRepresentation; // ads leading empty bits
        binaryRepresentation = binaryRepresentation.replaceAll(BIT_FORMAT_REGEX, "$1 ").trim(); // separates every 8 digits with space
        return String.format(BITFLAG_STRING_FORMAT, binaryRepresentation); // final format
    }
}
