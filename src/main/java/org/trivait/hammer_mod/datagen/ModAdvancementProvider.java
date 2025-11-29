package org.trivait.hammer_mod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import org.trivait.hammer_mod.item.ModItems;
import org.trivait.hammer_mod.util.ModAchievements;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<net.minecraft.advancement.AdvancementEntry> consumer) {
        // HammerMod! — root, фон: камень, иконка: алмазный молот
        var root = ModAchievements.registerRoot(
                consumer,
                "root",
                Text.translatable("advancement.hammer_mod.root.title"),
                Text.translatable("advancement.hammer_mod.root.desc"),
                ModItems.DIAMOND_HAMMER
        );

        // Основа — железные палки
        var ironStick = ModAchievements.registerWithItems(
                consumer,
                "iron_stick",
                Text.translatable("advancement.hammer_mod.iron_stick.title"),
                Text.translatable("advancement.hammer_mod.iron_stick.desc"),
                ModItems.IRON_STICK,
                root,
                ModItems.IRON_STICK
        );

        // Молоты (все от «Основа»)
        var woodHammer = ModAchievements.registerWithItems(
                consumer,
                "wood_hammer",
                Text.translatable("advancement.hammer_mod.wood_hammer.title"),
                Text.translatable("advancement.hammer_mod.wood_hammer.desc"),
                ModItems.WOOD_HAMMER,
                ironStick,
                ModItems.WOOD_HAMMER
        );

        var stoneHammer = ModAchievements.registerWithItems(
                consumer,
                "stone_hammer",
                Text.translatable("advancement.hammer_mod.stone_hammer.title"),
                Text.translatable("advancement.hammer_mod.stone_hammer.desc"),
                ModItems.STONE_HAMMER,
                ironStick,
                ModItems.STONE_HAMMER
        );

        var ironHammer = ModAchievements.registerWithItems(
                consumer,
                "iron_hammer",
                Text.translatable("advancement.hammer_mod.iron_hammer.title"),
                Text.translatable("advancement.hammer_mod.iron_hammer.desc"),
                ModItems.IRON_HAMMER,
                ironStick,
                ModItems.IRON_HAMMER
        );

        var goldHammer = ModAchievements.registerWithItems(
                consumer,
                "gold_hammer",
                Text.translatable("advancement.hammer_mod.gold_hammer.title"),
                Text.translatable("advancement.hammer_mod.gold_hammer.desc"),
                ModItems.GOLD_HAMMER,
                ironStick,
                ModItems.GOLD_HAMMER
        );

        var diamondHammer = ModAchievements.registerWithItems(
                consumer,
                "diamond_hammer",
                Text.translatable("advancement.hammer_mod.diamond_hammer.title"),
                Text.translatable("advancement.hammer_mod.diamond_hammer.desc"),
                ModItems.DIAMOND_HAMMER,
                ironStick,
                ModItems.DIAMOND_HAMMER
        );

        // Вот это молот! — незеритовый молот, наследуется от алмазного (challenge)
        var netheriteHammer = ModAchievements.registerChallengeWithItems(
                consumer,
                "netherite_hammer",
                Text.translatable("advancement.hammer_mod.netherite_hammer.title"),
                Text.translatable("advancement.hammer_mod.netherite_hammer.desc"),
                ModItems.NETHERITE_HAMMER,
                diamondHammer,
                ModItems.NETHERITE_HAMMER
        );

        // Настоящий каменщик — все молоты (challenge, иконка: булыжник)
        ModAchievements.registerAllItemsChallenge(
                consumer,
                "all_hammers",
                Text.translatable("advancement.hammer_mod.all_hammers.title"),
                Text.translatable("advancement.hammer_mod.all_hammers.desc"),
                Items.COBBLESTONE,
                netheriteHammer,
                new String[] {"wood", "stone", "iron", "gold", "diamond", "netherite"},
                new net.minecraft.item.ItemConvertible[] {
                        ModItems.WOOD_HAMMER,
                        ModItems.STONE_HAMMER,
                        ModItems.IRON_HAMMER,
                        ModItems.GOLD_HAMMER,
                        ModItems.DIAMOND_HAMMER,
                        ModItems.NETHERITE_HAMMER
                }
        );
    }
}
