package org.trivait.hammer_mod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;
import org.trivait.hammer_mod.HammerMod;
import org.trivait.hammer_mod.item.custom.HammerItem;

public class ModItems {

    public static final Item IRON_STICK = registerItem("iron_stick", new Item(new Item.Settings()));
    public static final Item WOOD_HAMMER = registerItem("wood_hammer",
            new HammerItem(ToolMaterials.WOOD, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.WOOD, 1, -1f))));
    public static final Item STONE_HAMMER = registerItem("stone_hammer",
            new HammerItem(ToolMaterials.STONE, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.STONE, 1, -1f))));
    public static final Item IRON_HAMMER = registerItem("iron_hammer",
            new HammerItem(ToolMaterials.IRON, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.IRON, 1, -1f))));
    public static final Item GOLD_HAMMER = registerItem("gold_hammer",
            new HammerItem(ToolMaterials.GOLD, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.GOLD, 1, -1f))));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer",
            new HammerItem(ToolMaterials.DIAMOND, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.DIAMOND, 1, -1f))));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer",
            new HammerItem(ToolMaterials.NETHERITE, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.NETHERITE, 1, -1f))));


    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(HammerMod.MOD_ID, id), item);
    }

    public static void register() {
    }
}
