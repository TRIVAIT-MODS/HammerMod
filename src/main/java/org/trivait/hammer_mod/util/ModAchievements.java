package org.trivait.hammer_mod.util;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.function.Consumer;

public class ModAchievements {

    // Root: триггер по тикам, фон — камень, иконка — как задано
    public static AdvancementEntry registerRoot(Consumer<AdvancementEntry> consumer,
                                                String id,
                                                Text title,
                                                Text desc,
                                                ItemConvertible icon) {
        Advancement.Builder builder = Advancement.Builder.create()
                .display(
                        icon,
                        title,
                        desc,
                        Identifier.of("minecraft", "textures/block/stone.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("tick", TickCriterion.Conditions.createTick());

        return builder.build(consumer, "hammer_mod:" + id);
    }

    // Обычная ачивка: отдельная иконка и отдельные предметы для условия (любая из items)
    public static AdvancementEntry registerWithItems(Consumer<AdvancementEntry> consumer,
                                                     String id,
                                                     Text title,
                                                     Text desc,
                                                     ItemConvertible icon,
                                                     AdvancementEntry parent,
                                                     ItemConvertible... itemsForCondition) {

        Advancement.Builder builder = Advancement.Builder.create()
                .display(
                        icon,
                        title,
                        desc,
                        Identifier.of("minecraft", "textures/block/stone.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(itemsForCondition));

        if (parent != null) builder.parent(parent);

        return builder.build(consumer, "hammer_mod:" + id);
    }

    // Испытание (challenge): отдельная иконка и предметы-условия
    public static AdvancementEntry registerChallengeWithItems(Consumer<AdvancementEntry> consumer,
                                                              String id,
                                                              Text title,
                                                              Text desc,
                                                              ItemConvertible icon,
                                                              AdvancementEntry parent,
                                                              ItemConvertible... itemsForCondition) {

        Advancement.Builder builder = Advancement.Builder.create()
                .display(
                        icon,
                        title,
                        desc,
                        Identifier.of("minecraft", "textures/block/stone.png"),
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(itemsForCondition));

        if (parent != null) builder.parent(parent);

        return builder.build(consumer, "hammer_mod:" + id);
    }

    // Испытание «все молоты»: множественные критерии + требования "все из них"
    public static AdvancementEntry registerAllItemsChallenge(Consumer<AdvancementEntry> consumer,
                                                             String id,
                                                             Text title,
                                                             Text desc,
                                                             ItemConvertible icon,
                                                             AdvancementEntry parent,
                                                             String[] criterionNames,
                                                             ItemConvertible[] criterionItems) {

        if (criterionNames.length != criterionItems.length) {
            throw new IllegalArgumentException("criterionNames.length must equal criterionItems.length");
        }

        Advancement.Builder builder = Advancement.Builder.create()
                .display(
                        icon,
                        title,
                        desc,
                        Identifier.of("minecraft", "textures/block/stone.png"),
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                );

        for (int i = 0; i < criterionNames.length; i++) {
            builder.criterion(criterionNames[i], InventoryChangedCriterion.Conditions.items(criterionItems[i]));
        }

        // Требовать ВСЕ критерии (коллекция имен)
        builder.requirements(AdvancementRequirements.allOf(Arrays.asList(criterionNames)));

        if (parent != null) builder.parent(parent);

        return builder.build(consumer, "hammer_mod:" + id);
    }
}
