package org.trivait.hammer_mod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.trivait.hammer_mod.HammerMod;
import org.trivait.hammer_mod.item.custom.HammerItem;
import org.trivait.hammer_mod.util.HammerMode;
import org.trivait.hammer_mod.util.ModDataComponentTypes;

public class ModItems {

    public static final Item IRON_STICK = registerItem("iron_stick", new Item(new Item.Settings()));

    public static final Item WOOD_HAMMER = registerItem("wood_hammer",
            new HammerItem(ToolMaterials.WOOD, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.WOOD, 1, -1f))
                    .component(ModDataComponentTypes.HAMMER_MODE, HammerMode.DEFAULT)));

    public static final Item STONE_HAMMER = registerItem("stone_hammer",
            new HammerItem(ToolMaterials.STONE, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.STONE, 1, -1f))
                    .component(ModDataComponentTypes.HAMMER_MODE, HammerMode.DEFAULT)));

    public static final Item IRON_HAMMER = registerItem("iron_hammer",
            new HammerItem(ToolMaterials.IRON, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.IRON, 1, -1f))
                    .component(ModDataComponentTypes.HAMMER_MODE, HammerMode.DEFAULT)));

    public static final Item GOLD_HAMMER = registerItem("gold_hammer",
            new HammerItem(ToolMaterials.GOLD, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.GOLD, 1, -1f))
                    .component(ModDataComponentTypes.HAMMER_MODE, HammerMode.DEFAULT)));

    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer",
            new HammerItem(ToolMaterials.DIAMOND, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.DIAMOND, 1, -1f))
                    .component(ModDataComponentTypes.HAMMER_MODE, HammerMode.DEFAULT)));

    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer",
            new HammerItem(ToolMaterials.NETHERITE, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.NETHERITE, 1, -1f))
                    .component(ModDataComponentTypes.HAMMER_MODE, HammerMode.DEFAULT)
                    .fireproof()));

    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(HammerMod.MOD_ID, id), item);
    }

    public static void register() { }
}
