package org.trivait.hammer_mod.util;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.trivait.hammer_mod.HammerMod;

import java.util.function.UnaryOperator;

public final class ModDataComponentTypes {

    //это пример из дргого проекта!

    public static final ComponentType<HammerMode> HAMMER_MODE = register("hammer_mode",
            b -> b.codec(Codec.STRING.xmap(HammerMode::valueOf, HammerMode::name)));

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> op) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE,
                Identifier.of(HammerMod.MOD_ID, id),
                op.apply(ComponentType.builder()).build());
    }

    public static void registerData() {
        HammerMod.LOGGER.info("ModDataComponentTypes registered");
    }
}
