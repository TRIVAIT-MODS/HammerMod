package org.trivait.hammer_mod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.trivait.hammer_mod.HammerMod;

public class ModItemGroups {

    public static final ItemGroup HAMMER_MOD = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(HammerMod.MOD_ID, "hammer_mod"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.STONE_HAMMER))
                    .displayName(Text.translatable("itemgroup.hammer_mod.hammer_mod"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.IRON_STICK);
                        entries.add(ModItems.WOOD_HAMMER);
                        entries.add(ModItems.STONE_HAMMER);
                        entries.add(ModItems.GOLD_HAMMER);
                        entries.add(ModItems.IRON_HAMMER);
                        entries.add(ModItems.DIAMOND_HAMMER);
                        entries.add(ModItems.NETHERITE_HAMMER);
                    })
                    .build());

    public static void register() {

    }

}
