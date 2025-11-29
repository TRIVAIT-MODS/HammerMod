package org.trivait.hammer_mod.util;

import net.minecraft.item.ItemStack;

public final class HammerModeUtils {

    public static HammerMode get(ItemStack stack) {
        HammerMode mode = stack.get(ModDataComponentTypes.HAMMER_MODE);
        return mode != null ? mode : HammerMode.DEFAULT;
    }

    public static void set(ItemStack stack, HammerMode mode) {
        stack.set(ModDataComponentTypes.HAMMER_MODE, mode);
    }

    public static void cycle(ItemStack stack, boolean forward) {
        set(stack, (forward ? get(stack).next() : get(stack).prev()));
    }
}
