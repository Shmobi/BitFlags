package com.litts.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Utility providing multi-flag operations for {@link BitFlag}
 */
public abstract class BitFlagUtil {

    private BitFlagUtil(){
        // static utility, no instances required
    }

    /**
     * @param flags    The flags that should be checked for the provided {@link BitFlag}s
     * @param bitFlags The {@link BitFlag}s that should be checked for. May not be null
     * @return true if all provided {@link BitFlag}s are set in the provided flags value
     */
    public static boolean areSet(long flags, @NotNull BitFlag... bitFlags) {
        return Arrays.stream(bitFlags).allMatch(flag -> flag.isSet(flags));
    }

    /**
     * @param flags    The flags that should be checked for the provided {@link BitFlag}s
     * @param bitFlags The {@link BitFlag}s that should be checked for. May not be null
     * @return true if one or more of the provided {@link BitFlag}s are set in the provided flags value
     */
    public static boolean areAnySet(long flags, @NotNull BitFlag... bitFlags) {
        return Arrays.stream(bitFlags).anyMatch(flag -> flag.isSet(flags));
    }

    /**
     * Ads all provided {@link BitFlag}s in the provided flags value if they haven't been set already
     *
     * @param flags    The flags that the {@link BitFlag}s should be added to
     * @param bitFlags The {@link BitFlag}s that should be added. May not be null
     * @return the new flags value
     */
    public static long setAll(long flags, @NotNull BitFlag... bitFlags) {
        for (BitFlag bitFlag : bitFlags) {
            flags = bitFlag.setTo(flags);
        }
        return flags;
    }

    /**
     * Removes all provided {@link BitFlag}s from the provided flags value if they have been set
     *
     * @param flags    The flags that the {@link BitFlag}s should be removed from
     * @param bitFlags The {@link BitFlag}s that should be removed. May not be null
     * @return the new flags value
     */
    public static long removeAll(long flags, @NotNull BitFlag... bitFlags) {
        for (BitFlag bitFlag : bitFlags) {
            flags = bitFlag.removeFrom(flags);
        }
        return flags;
    }

    /**
     * Negates all provided {@link BitFlag}s in the provided flags value
     *
     * @param flags    The flags in which the {@link BitFlag}s should be negated in
     * @param bitFlags The {@link BitFlag}s that should be negated. May not be null
     * @return the new flags value
     */
    public static long negateAll(long flags, @NotNull BitFlag... bitFlags) {
        for (BitFlag bitFlag : bitFlags) {
            flags = bitFlag.negate(flags);
        }
        return flags;
    }


}
