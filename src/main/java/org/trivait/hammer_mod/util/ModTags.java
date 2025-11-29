package org.trivait.hammer_mod.util;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.trivait.hammer_mod.HammerMod;

public class ModTags {

    public static class Blocks {
    }

    public static class Items {

        public static final TagKey<Item> HAMMERS = createTag("hammers");

        private static TagKey<Item> createTag(String id){
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(HammerMod.MOD_ID, id));
        }
    }
}
