package org.trivait.hammer_mod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.trivait.hammer_mod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.IRON_STICK, 4)
                .pattern("I")
                .pattern("I")
                .input('I', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.WOOD_HAMMER)
                .pattern("MMM")
                .pattern("MMM")
                .pattern(" S ")
                .input('S', ModItems.IRON_STICK)
                .input('M', ItemTags.PLANKS)
                .criterion(hasItem(ModItems.IRON_STICK), conditionsFromItem(ModItems.IRON_STICK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.STONE_HAMMER)
                .pattern("MMM")
                .pattern("MMM")
                .pattern(" S ")
                .input('S', ModItems.IRON_STICK)
                .input('M', ItemTags.STONE_CRAFTING_MATERIALS)
                .criterion(hasItem(ModItems.IRON_STICK), conditionsFromItem(ModItems.IRON_STICK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.IRON_HAMMER)
                .pattern("MMM")
                .pattern("MMM")
                .pattern(" S ")
                .input('S', ModItems.IRON_STICK)
                .input('M', Items.IRON_INGOT)
                .criterion(hasItem(ModItems.IRON_STICK), conditionsFromItem(ModItems.IRON_STICK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.GOLD_HAMMER)
                .pattern("MMM")
                .pattern("MMM")
                .pattern(" S ")
                .input('S', ModItems.IRON_STICK)
                .input('M', Items.GOLD_INGOT)
                .criterion(hasItem(ModItems.IRON_STICK), conditionsFromItem(ModItems.IRON_STICK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DIAMOND_HAMMER)
                .pattern("MMM")
                .pattern("MMM")
                .pattern(" S ")
                .input('S', ModItems.IRON_STICK)
                .input('M', Items.DIAMOND)
                .criterion(hasItem(ModItems.IRON_STICK), conditionsFromItem(ModItems.IRON_STICK))
                .offerTo(exporter);
    }
}
