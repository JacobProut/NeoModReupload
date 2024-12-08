
package com.jacobpmods.neomod.datagen;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider  extends BlockLootSubProvider {
    protected  ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }


    @Override
    protected void generate() {
        dropSelf(ModBlocks.NEXON_BLOCK.get());
        dropSelf(ModBlocks.NEXON_ORE_BLOCK.get());
        this.add(ModBlocks.NEXON_ORE_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.NEXON_ORE_BLOCK.get(), ModItems.nexon.get(), 1, 2));

        //Grass & Dirt Blocks
        this.add(ModBlocks.GHOSTLY_GRASS_BLOCK.get(), block -> this.createSilkTouchDispatchTable(
                ModBlocks.GHOSTLY_GRASS_BLOCK.get(),
                this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(ModBlocks.GHOSTLY_DIRT.get())
                )
        ));
        this.add(ModBlocks.BLOODY_GRASS_BLOCK.get(), block -> this.createSilkTouchDispatchTable(
                ModBlocks.BLOODY_GRASS_BLOCK.get(),
                this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(ModBlocks.GHOSTLY_DIRT.get())
                )
        ));
        dropSelf(ModBlocks.GHOSTLY_DIRT.get());


        //Stone Blocks
        this.add(ModBlocks.GHOSTLY_STONE.get(), block -> this.createSilkTouchDispatchTable(
                ModBlocks.GHOSTLY_STONE.get(),
                this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(ModBlocks.GHOSTLY_COBBLESTONE.get())
                )
        ));
        dropSelf(ModBlocks.GHOSTLY_STONE_STAIRS.get());
        this.add(ModBlocks.GHOSTLY_STONE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.GHOSTLY_STONE_SLAB.get()));
        dropSelf(ModBlocks.GHOSTLY_STONE_FENCE.get());
        dropSelf(ModBlocks.GHOSTLY_STONE_FENCE_GATE.get());
        dropSelf(ModBlocks.GHOSTLY_STONE_WALL.get());
        dropSelf(ModBlocks.GHOSTLY_COBBLESTONE.get());
        dropSelf(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get());
        this.add(ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get()));
        dropSelf(ModBlocks.GHOSTLY_COBBLESTONE_FENCE.get());
        dropSelf(ModBlocks.GHOSTLY_COBBLESTONE_FENCE_GATE.get());
        dropSelf(ModBlocks.GHOSTLY_COBBLESTONE_WALL.get());
        dropSelf(ModBlocks.GHOSTLY_STONE_BRICKS.get());
        dropSelf(ModBlocks.GHOSTLY_STONEBRICK_STAIRS.get());
        this.add(ModBlocks.GHOSTLY_STONEBRICK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.GHOSTLY_STONEBRICK_SLAB.get()));
        dropSelf(ModBlocks.GHOSTLY_STONEBRICK_FENCE.get());
        dropSelf(ModBlocks.GHOSTLY_STONEBRICK_FENCE_GATE.get());
        dropSelf(ModBlocks.GHOSTLY_STONEBRICK_WALL.get());

        //Bone Brick Blocks
        dropSelf(ModBlocks.BONE_BRICK.get());
        dropSelf(ModBlocks.BONE_BRICK_STAIRS.get());
        this.add(ModBlocks.BONE_BRICK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BONE_BRICK_SLAB.get()));
        dropSelf(ModBlocks.BONE_BRICK_FENCE.get());
        dropSelf(ModBlocks.BONE_BRICK_FENCE_GATE.get());
        dropSelf(ModBlocks.BONE_BRICK_WALL.get());


        //Wood Blocks
        this.dropSelf(ModBlocks.LOG_GHOSTLY.get());
        this.dropSelf(ModBlocks.STRIPPED_GHOSTLY_LOG.get());
        this.dropSelf(ModBlocks.WOOD_GHOSTLY.get());
        this.dropSelf(ModBlocks.STRIPPED_GHOSTLY_WOOD.get());
        this.dropSelf(ModBlocks.GHOSTLY_PLANKS.get());
        dropSelf(ModBlocks.GHOSTLY_PLANK_STAIRS.get());
        this.add(ModBlocks.GHOSTLY_PLANK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.GHOSTLY_PLANK_SLAB.get()));
        dropSelf(ModBlocks.GHOSTLY_PLANK_FENCE.get());
        dropSelf(ModBlocks.GHOSTLY_PLANK_FENCE_GATE.get());
        dropSelf(ModBlocks.GHOSTLY_PLANK_WALL.get());
        this.add(ModBlocks.GHOSTLY_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.GHOSTLY_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.GHOSTLY_SAPLING.get());

        this.dropSelf(ModBlocks.LOG_BLOODY.get());
        this.dropSelf(ModBlocks.PLANKS_BLOODY.get());
        dropSelf(ModBlocks.BLOODY_PLANK_STAIRS.get());
        this.add(ModBlocks.BLOODY_PLANK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BLOODY_PLANK_SLAB.get()));
        dropSelf(ModBlocks.BLOODY_PLANK_FENCE.get());
        dropSelf(ModBlocks.BLOODY_PLANK_FENCE_GATE.get());
        dropSelf(ModBlocks.BLOODY_PLANK_WALL.get());
        this.add(ModBlocks.BLOODY_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BLOODY_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.BLOODY_SAPLING.get());


        //Flowers
        this.dropSelf(ModBlocks.OOZING_FLOWER.get());

        //Block Entities
        dropSelf(ModBlocks.PEDESTAL.get());
        dropSelf(ModBlocks.ENHANCER.get());

        dropSelf(ModBlocks.GHOSTLY_WEB.get());

        dropNothing(ModBlocks.SKULL_N_BONES.get());
        /*this.add(ModBlocks.SKULL_N_BONES.get(),
                block -> createMultipleOreDrops(ModBlocks.SKULL_N_BONES.get(), ModItems.SUPERIOR_BONES.get(), 1, 2));*/


        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        this.add(ModBlocks.BLOOD_BONE_FRUIT_BUSH.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLOOD_BONE_FRUIT_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.BLOOD_BONE_FRUIT_BERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLOOD_BONE_FRUIT_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 1))
                                ).add(LootItem.lootTableItem(ModItems.BLOOD_BONE_FRUIT_BERRIES.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                )));


        this.add(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get(), ModItems.SHATTERED_FRAGMENT.get(), 1, 2));


        dropSelf(ModBlocks.BLOODY_VINE.get());


    }

    protected void dropNothing(Block block) {
        this.add(block, b -> LootTable.lootTable()
                .withPool(LootPool.lootPool())  // Empty pool, no drops
        );
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected  Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

}

