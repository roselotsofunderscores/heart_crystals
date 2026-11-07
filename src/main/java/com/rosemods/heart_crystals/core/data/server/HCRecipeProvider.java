package com.rosemods.heart_crystals.core.data.server;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.rosemods.heart_crystals.core.registry.HCBlocks.*;
import static com.rosemods.heart_crystals.core.registry.HCItems.CUPIDS_ARROW;
import static com.rosemods.heart_crystals.core.registry.HCItems.HEART_BANNER_PATTERN;

public class HCRecipeProvider extends BlueprintRecipeProvider {
    public HCRecipeProvider(GatherDataEvent event, HCDatapackProvider dataPack) {
        super(HeartCrystals.MOD_ID, event.getGenerator().getPackOutput(), dataPack.getRegistryProvider());
    }

    @Override
    public void buildRecipes(RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HEART_CRYSTAL).define('#', HEART_CRYSTAL_SHARD).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(HEART_CRYSTAL_SHARD), has(HEART_CRYSTAL_SHARD)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HEART_CRYSTAL_SHARD, 9).requires(HEART_CRYSTAL).unlockedBy(getHasName(HEART_CRYSTAL), has(HEART_CRYSTAL)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HEART_BANNER_PATTERN).requires(Items.PAPER).requires(HEART_CRYSTAL_SHARD).unlockedBy(getHasName(HEART_CRYSTAL_SHARD), has(HEART_CRYSTAL_SHARD)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, CUPIDS_ARROW, 6).define('#', HEART_CRYSTAL_SHARD).define('S', Items.STICK).define('F', Items.FEATHER).pattern("#").pattern("S").pattern("F").unlockedBy(getHasName(HEART_CRYSTAL_SHARD), has(HEART_CRYSTAL_SHARD)).save(output);
        conditionalRecipe(output, new TagEmptyCondition(ResourceLocation.fromNamespaceAndPath("c", "ingots/silver")), RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HEART_LANTERN).define('#', HEART_CRYSTAL).define('I', Items.IRON_INGOT).pattern("I").pattern("#").pattern("I").unlockedBy(getHasName(HEART_CRYSTAL), has(HEART_CRYSTAL)));
        conditionalRecipe(output, new NotCondition(new TagEmptyCondition(ResourceLocation.fromNamespaceAndPath("c", "ingots/silver"))), RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HEART_LANTERN).define('#', HEART_CRYSTAL).define('I', TagUtil.itemTag("c", "ingots/silver")).pattern("I").pattern("#").pattern("I").unlockedBy(getHasName(HEART_CRYSTAL), has(HEART_CRYSTAL)), HeartCrystals.location("heart_lantern_from_silver"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HEART_CRYSTAL_LAMP).define('#', HEART_CRYSTAL_SHARD).define('G', Items.GLOWSTONE).pattern(" # ").pattern("#G#").pattern(" # ").unlockedBy(getHasName(HEART_CRYSTAL_SHARD), has(HEART_CRYSTAL_SHARD)).save(output);
    }

}
