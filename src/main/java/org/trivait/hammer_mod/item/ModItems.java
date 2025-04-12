package org.trivait.hammer_mod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;
import org.trivait.hammer_mod.HammerMod;
import org.trivait.hammer_mod.item.custom.HammerItem;

public class ModItems {

    public static final Item IRON_STICK = registerItem("iron_stick", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HammerMod.MOD_ID, "iron_stick")))));
    public static final Item WOOD_HAMMER = registerItem("wood_hammer",
            new HammerItem(ToolMaterial.WOOD,1, -1f, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HammerMod.MOD_ID, "wood_hammer")))));
    public static final Item STONE_HAMMER = registerItem("stone_hammer",
            new HammerItem(ToolMaterial.STONE, 1, -1f, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HammerMod.MOD_ID, "stone_hammer")))));
    public static final Item IRON_HAMMER = registerItem("iron_hammer",
            new HammerItem(ToolMaterial.IRON,1, -1f,  new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HammerMod.MOD_ID, "iron_hammer")))));
    public static final Item GOLD_HAMMER = registerItem("gold_hammer",
            new HammerItem(ToolMaterial.GOLD, 1, -1f, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HammerMod.MOD_ID, "gold_hammer")))));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer",
            new HammerItem(ToolMaterial.DIAMOND, 1, -1f, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HammerMod.MOD_ID, "diamond_hammer")))));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer",
            new HammerItem(ToolMaterial.NETHERITE,1, -1f,  new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HammerMod.MOD_ID, "netherite_hammer"))).fireproof()));


    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(HammerMod.MOD_ID, id), item);
    }

    public static void register() {
    }
}
