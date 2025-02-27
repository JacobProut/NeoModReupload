package com.jacobpmods.neomod.datagen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        //Nexon Ore Smelting/Blasting Methods
        List<ItemLike> NEXON_SMELTABLES= List.of(ModItems.nexon.get()); //Can add other nexon items here that can be smelted.
        oreSmelting(recipeOutput, NEXON_SMELTABLES, RecipeCategory.MISC, ModItems.HEATED_NEXON.get(), 0.30f, 200, "nexon");
        oreBlasting(recipeOutput, NEXON_SMELTABLES, RecipeCategory.MISC, ModItems.HEATED_NEXON.get(), 0.30f, 100, "nexon");

        // Otherworldly Crafting Table recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OTHERWORLDLY_CRAFTING_TABLE.get(), 1)
                .define('G', ModBlocks.GHOSTLY_PLANKS.get())  // Define the G for Ghostly Planks
                .pattern("GG")
                .pattern("GG")
                .unlockedBy("has_ghostly_planks", has(ModBlocks.GHOSTLY_PLANKS.get()))  // Unlock when Ghostly Planks are acquired
                .save(recipeOutput, "neomod:otherworldly_crafting_table_ghostly");  // Use a unique recipe name
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OTHERWORLDLY_CRAFTING_TABLE.get(), 1)
                .define('B', ModBlocks.BLOODY_PLANKS.get())  // Define the G for Bloody Planks
                .pattern("BB")
                .pattern("BB")
                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS.get()))  // Unlock when Bloody Planks are acquired
                .save(recipeOutput, "neomod:otherworldly_crafting_table_bloody");  // Use a unique recipe name

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AFTERLIFE_TORCH.get(), 4)
                .define('S', Items.STICK)  // Define the S for Sticks
                .define('C', ModItems.SPIRIT_COAL) // C for Spirit Coal
                .pattern("C")
                .pattern("S")
                .unlockedBy("has_spirit_coal", has(ModItems.SPIRIT_COAL.get())).save(recipeOutput); // Use a unique recipe name

        //Nexon Block Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NEXON_BLOCK.get(), 1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.NEXON_INGOT.get())
                .unlockedBy(getHasName(ModItems.NEXON_INGOT.get()), has(ModItems.NEXON_INGOT.get())).save(recipeOutput);
        // 9 Nexon from Block Recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.nexon.get(), 9)
                .requires(ModBlocks.NEXON_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.NEXON_BLOCK.get()), has(ModBlocks.NEXON_BLOCK.get())).save(recipeOutput);
        //Shattered Fragment Block Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SHATTERED_FRAGMENT_BLOCK.get(), 1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.SHATTERED_FRAGMENT.get())
                .unlockedBy(getHasName(ModItems.SHATTERED_FRAGMENT.get()), has(ModItems.SHATTERED_FRAGMENT.get())).save(recipeOutput);
        //9 shattered fragments from block Recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SHATTERED_FRAGMENT.get(), 9)
                .requires(ModBlocks.SHATTERED_FRAGMENT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SHATTERED_FRAGMENT_BLOCK.get()), has(ModBlocks.SHATTERED_FRAGMENT_BLOCK.get())).save(recipeOutput);
        //Spirit Coal Block Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPIRIT_COAL_BLOCK.get(), 1)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SPIRIT_COAL.get())
                .unlockedBy(getHasName(ModItems.SPIRIT_COAL.get()), has(ModItems.SPIRIT_COAL.get())).save(recipeOutput);
        //9 Spirit Coal From Block Recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SPIRIT_COAL.get(), 9)
                .requires(ModBlocks.SPIRIT_COAL_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SPIRIT_COAL_BLOCK.get()), has(ModBlocks.SPIRIT_COAL_BLOCK.get())).save(recipeOutput);

        //Nexon ingot Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NEXON_INGOT.get(), 1)
                .pattern("HHH")
                .pattern("HHH")
                .pattern("HHH")
                .define('H', ModItems.HEATED_NEXON.get())
                .unlockedBy(getHasName(ModItems.NEXON_INGOT.get()), has(ModItems.NEXON_INGOT.get())).save(recipeOutput);

        //Nexon Reinforced Ingot Craft Recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NEXON_REINFORCED_INGOT.get(), 1)
                .requires(ModItems.NEXON_INGOT.get(),1)
                .requires(Items.NETHERITE_INGOT, 1)
                .unlockedBy(getHasName(ModItems.NEXON_INGOT.get()), has(ModItems.NEXON_INGOT.get())).save(recipeOutput);

        //Nexon Pickaxe Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NEXON_PICKAXE.get(), 1)
                .pattern("AAA")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ModItems.NEXON_REINFORCED_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.NEXON_REINFORCED_INGOT.get()), has(ModItems.NEXON_REINFORCED_INGOT.get())).save(recipeOutput);


        //Nexon Helmet Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NEXON_HELMET.get(), 1)
                .pattern("HRH")
                .pattern("R R")
                .pattern("   ")
                .define('H', ModItems.HEATED_NEXON.get())
                .define('R', ModItems.NEXON_REINFORCED_INGOT.get())
                .unlockedBy(getHasName(ModItems.NEXON_REINFORCED_INGOT.get()), has(ModItems.NEXON_REINFORCED_INGOT.get())).save(recipeOutput);

        //Nexon Chestplate Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NEXON_CHESTPLATE.get(), 1)
                .pattern("R R")
                .pattern("HRH")
                .pattern("RHR")
                .define('H', ModItems.HEATED_NEXON.get())
                .define('R', ModItems.NEXON_REINFORCED_INGOT.get())
                .unlockedBy(getHasName(ModItems.NEXON_REINFORCED_INGOT.get()), has(ModItems.NEXON_REINFORCED_INGOT.get())).save(recipeOutput);

        //Nexon Leggings Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NEXON_LEGGINGS.get(), 1)
                .pattern("RHR")
                .pattern("H H")
                .pattern("R R")
                .define('H', ModItems.HEATED_NEXON.get())
                .define('R', ModItems.NEXON_REINFORCED_INGOT.get())
                .unlockedBy(getHasName(ModItems.NEXON_REINFORCED_INGOT.get()), has(ModItems.NEXON_REINFORCED_INGOT.get())).save(recipeOutput);

        //Nexon Boots Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NEXON_BOOTS.get(), 1)
                .pattern("R R")
                .pattern("H H")
                .pattern("   ")
                .define('H', ModItems.HEATED_NEXON.get())
                .define('R', ModItems.NEXON_REINFORCED_INGOT.get())
                .unlockedBy(getHasName(ModItems.NEXON_REINFORCED_INGOT.get()), has(ModItems.NEXON_REINFORCED_INGOT.get())).save(recipeOutput);

        //Speed apple
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPEED_APPLE.get(), 1)
                .pattern("SSS")
                .pattern("SAS")
                .pattern("SSS")
                .define('A', Items.APPLE)
                .define('S', Items.SUGAR)
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE)).save(recipeOutput);


        //Ghostly Log to planks Craft Recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.GHOSTLY_PLANKS.get(), 4)
                .requires(ModBlocks.LOG_GHOSTLY.get(),1)
                .unlockedBy(getHasName(ModBlocks.LOG_GHOSTLY.get()), has(ModBlocks.LOG_GHOSTLY.get())).save(recipeOutput);

        //Bloody Log to planks Craft Recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.BLOODY_PLANKS.get(), 4)
                .requires(ModBlocks.LOG_BLOODY.get(),1)
                .unlockedBy(getHasName(ModBlocks.LOG_BLOODY.get()), has(ModBlocks.LOG_BLOODY.get())).save(recipeOutput);

        //Bone Sword[Uncharged] Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.BONE_SWORD.get(), 1)
                .pattern("FUF")
                .pattern("FUF")
                .pattern("FNF")
                .define('F', ModItems.SHATTERED_FRAGMENT.get())
                .define('U', ModItems.UNDEAD_BONE.get())
                .define('N', Items.NETHERITE_SWORD)
                .unlockedBy(getHasName(ModItems.SHATTERED_FRAGMENT.get()), has(ModItems.SHATTERED_FRAGMENT.get())).save(recipeOutput);

        // Slabs
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GHOSTLY_STONE_SLAB.get(), ModBlocks.GHOSTLY_STONE.get());
        stairBuilder(ModBlocks.GHOSTLY_STONE_STAIRS.get(), Ingredient.of(ModBlocks.GHOSTLY_STONE.get())).group("ghostly_stone")
                .unlockedBy("has_ghostly_stone", has(ModBlocks.GHOSTLY_STONE.get())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get(), ModBlocks.GHOSTLY_COBBLESTONE.get());
        stairBuilder(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get(), Ingredient.of(ModBlocks.GHOSTLY_COBBLESTONE.get())).group("ghostly_cobblestone")
                .unlockedBy("has_ghostly_cobblestone", has(ModBlocks.GHOSTLY_COBBLESTONE.get())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GHOSTLY_STONEBRICK_SLAB.get(), ModBlocks.GHOSTLY_STONEBRICKS.get());
        stairBuilder(ModBlocks.GHOSTLY_STONEBRICK_STAIRS.get(), Ingredient.of(ModBlocks.GHOSTLY_STONEBRICKS.get())).group("ghostly_stone_brick")
                .unlockedBy("has_ghostly_stone_brick", has(ModBlocks.GHOSTLY_STONEBRICKS.get())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BONE_BRICK_SLAB.get(), ModBlocks.BONE_BRICK.get());
        stairBuilder(ModBlocks.BONE_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.BONE_BRICK.get())).group("bone_brick")
                .unlockedBy("has_bone_brick", has(ModBlocks.BONE_BRICK.get())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GHOSTLY_PLANK_SLAB.get(), ModBlocks.GHOSTLY_PLANKS.get());
        stairBuilder(ModBlocks.GHOSTLY_PLANK_STAIRS.get(), Ingredient.of(ModBlocks.GHOSTLY_PLANKS.get())).group("ghostly_plank")
                .unlockedBy("has_ghostly_plank", has(ModBlocks.GHOSTLY_PLANKS.get())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODY_PLANK_SLAB.get(), ModBlocks.BLOODY_PLANKS.get());
        stairBuilder(ModBlocks.BLOODY_PLANK_STAIRS.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS.get())).group("bloody_plank")
                .unlockedBy("has_ghostly_plank", has(ModBlocks.BLOODY_PLANKS.get())).save(recipeOutput);


        // Walls
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GHOSTLY_STONE_WALL.get(), ModBlocks.GHOSTLY_STONE.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GHOSTLY_COBBLESTONE_WALL.get(), ModBlocks.GHOSTLY_COBBLESTONE.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GHOSTLY_STONEBRICK_WALL.get(), ModBlocks.GHOSTLY_STONEBRICKS.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BONE_BRICK_WALL.get(), ModBlocks.BONE_BRICK.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GHOSTLY_PLANK_WALL.get(), ModBlocks.GHOSTLY_PLANKS.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODY_PLANK_WALL.get(), ModBlocks.BLOODY_PLANKS.get());

        // Fences
        fenceBuilder(ModBlocks.GHOSTLY_STONE_FENCE.get(), Ingredient.of(ModBlocks.GHOSTLY_STONE.get())).group("ghostly_stone")
                .unlockedBy("has_ghostly_stone", has(ModBlocks.GHOSTLY_STONE.get())).save(recipeOutput);
        fenceBuilder(ModBlocks.GHOSTLY_COBBLESTONE_FENCE.get(), Ingredient.of(ModBlocks.GHOSTLY_COBBLESTONE.get())).group("ghostly_cobblestone")
                .unlockedBy("has_ghostly_cobblestone", has(ModBlocks.GHOSTLY_COBBLESTONE.get())).save(recipeOutput);
        fenceBuilder(ModBlocks.GHOSTLY_STONEBRICK_FENCE.get(), Ingredient.of(ModBlocks.GHOSTLY_STONEBRICKS.get())).group("ghostly_stone_bricks")
                .unlockedBy("has_ghostly_stonebricks", has(ModBlocks.GHOSTLY_STONEBRICKS.get())).save(recipeOutput);
        fenceBuilder(ModBlocks.BONE_BRICK_FENCE.get(), Ingredient.of(ModBlocks.BONE_BRICK.get())).group("bone_bricks")
                .unlockedBy("has_bone_brick", has(ModBlocks.BONE_BRICK.get())).save(recipeOutput);
        fenceBuilder(ModBlocks.GHOSTLY_PLANK_FENCE.get(), Ingredient.of(ModBlocks.GHOSTLY_PLANKS.get())).group("ghostly_planks")
                .unlockedBy("has_ghostly_planks", has(ModBlocks.GHOSTLY_PLANKS.get())).save(recipeOutput);
        fenceBuilder(ModBlocks.BLOODY_PLANK_FENCE.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS.get())).group("bloody_planks")
                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS.get())).save(recipeOutput);

        // Fence Gates
        fenceGateBuilder(ModBlocks.GHOSTLY_STONE_FENCE_GATE.get(), Ingredient.of(ModBlocks.GHOSTLY_STONE.get())).group("ghostly_stone")
                .unlockedBy("has_ghostly_stone", has(ModBlocks.GHOSTLY_STONE.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.GHOSTLY_COBBLESTONE_FENCE_GATE.get(), Ingredient.of(ModBlocks.GHOSTLY_COBBLESTONE.get())).group("ghostly_cobblestone")
                .unlockedBy("has_ghostly_cobblestone", has(ModBlocks.GHOSTLY_COBBLESTONE.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.GHOSTLY_STONEBRICK_FENCE_GATE.get(), Ingredient.of(ModBlocks.GHOSTLY_STONEBRICKS.get())).group("ghostly_stone_bricks")
                .unlockedBy("has_ghostly_stonebricks", has(ModBlocks.GHOSTLY_STONEBRICKS.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.BONE_BRICK_FENCE_GATE.get(), Ingredient.of(ModBlocks.BONE_BRICK.get())).group("bone_bricks")
                .unlockedBy("has_bone_brick", has(ModBlocks.BONE_BRICK.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.GHOSTLY_PLANK_FENCE_GATE.get(), Ingredient.of(ModBlocks.GHOSTLY_PLANKS.get())).group("ghostly_planks")
                .unlockedBy("has_ghostly_planks", has(ModBlocks.GHOSTLY_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.BLOODY_PLANK_FENCE_GATE.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS.get())).group("bloody_planks")
                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS.get())).save(recipeOutput);

        // Pressure Plates
        pressurePlate(recipeOutput, ModBlocks.GHOSTLY_STONE_PRESSURE_PLATE.get(), ModBlocks.GHOSTLY_STONE.get());
        pressurePlate(recipeOutput, ModBlocks.GHOSTLY_COBBLESTONE_PRESSURE_PLATE.get(), ModBlocks.GHOSTLY_COBBLESTONE.get());
        pressurePlate(recipeOutput, ModBlocks.GHOSTLY_STONEBRICK_PRESSURE_PLATE.get(), ModBlocks.GHOSTLY_STONEBRICKS.get());
        pressurePlate(recipeOutput, ModBlocks.BONE_BRICK_PRESSURE_PLATE.get(), ModBlocks.BONE_BRICK.get());
        pressurePlate(recipeOutput, ModBlocks.GHOSTLY_PLANK_PRESSURE_PLATE.get(), ModBlocks.GHOSTLY_PLANKS.get());
        pressurePlate(recipeOutput, ModBlocks.BLOODY_PLANK_PRESSURE_PLATE.get(), ModBlocks.BLOODY_PLANKS.get());


        // Buttons
        buttonBuilder(ModBlocks.GHOSTLY_STONE_BUTTON.get(), Ingredient.of(ModBlocks.GHOSTLY_STONE.get())).group("ghostly_stone")
                .unlockedBy("has_ghostly_stone", has(ModBlocks.GHOSTLY_STONE.get())).save(recipeOutput);
        buttonBuilder(ModBlocks.GHOSTLY_COBBLESTONE_BUTTON.get(), Ingredient.of(ModBlocks.GHOSTLY_COBBLESTONE.get())).group("ghostly_cobblestone")
                .unlockedBy("has_ghostly_cobblestone", has(ModBlocks.GHOSTLY_COBBLESTONE.get())).save(recipeOutput);
        buttonBuilder(ModBlocks.GHOSTLY_STONEBRICK_BUTTON.get(), Ingredient.of(ModBlocks.GHOSTLY_STONEBRICKS.get())).group("ghostly_stone_bricks")
                .unlockedBy("has_ghostly_stone_bricks", has(ModBlocks.GHOSTLY_STONEBRICKS.get())).save(recipeOutput);
        buttonBuilder(ModBlocks.BONE_BRICK_BUTTON.get(), Ingredient.of(ModBlocks.BONE_BRICK.get())).group("bone_bricks")
                .unlockedBy("has_bone_bricks", has(ModBlocks.BONE_BRICK.get())).save(recipeOutput);
        buttonBuilder(ModBlocks.GHOSTLY_PLANK_BUTTON.get(), Ingredient.of(ModBlocks.GHOSTLY_PLANKS.get())).group("ghostly_planks")
                .unlockedBy("has_ghostly_planks", has(ModBlocks.GHOSTLY_PLANKS.get())).save(recipeOutput);
        buttonBuilder(ModBlocks.BLOODY_PLANK_BUTTON.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS.get())).group("bloody_planks")
                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS.get())).save(recipeOutput);

        // Door
        doorBuilder(ModBlocks.GHOSTLY_DOOR.get(), Ingredient.of(ModBlocks.GHOSTLY_PLANKS.get())).group("ghostly_planks")
                .unlockedBy("has_ghostly_planks", has(ModBlocks.GHOSTLY_PLANKS.get())).save(recipeOutput);
        doorBuilder(ModBlocks.BLOODY_DOOR.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS.get())).group("bloody_planks")
                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS.get())).save(recipeOutput);

        // Trap Door
        trapdoorBuilder(ModBlocks.GHOSTLY_TRAPDOOR.get(), Ingredient.of(ModBlocks.GHOSTLY_PLANKS.get())).group("ghostly_planks")
                .unlockedBy("has_ghostly_planks", has(ModBlocks.GHOSTLY_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.BLOODY_TRAPDOOR.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS.get())).group("bloody_planks")
                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS.get())).save(recipeOutput);
    }
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, FirstNeoMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}