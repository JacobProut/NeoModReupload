package com.jacobpmods.neomod.datagen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.jacobpmods.neomod.util.ModTags.Blocks.*;
import static net.minecraft.tags.BlockTags.*;
import static net.neoforged.neoforge.common.Tags.Blocks.NEEDS_NETHERITE_TOOL;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FirstNeoMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.NEXON_BLOCK.get())
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get())
                .add(ModBlocks.GHOSTLY_STONE.get())
                .add(ModBlocks.GHOSTLY_STONE_BRICKS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE.get())
                .add(ModBlocks.GHOSTLY_STONE_SLAB.get())
                .add(ModBlocks.GHOSTLY_STONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get())
                .add(ModBlocks.BONE_BRICK.get())
                .add(ModBlocks.BONE_BRICK_STAIRS.get())
                .add(ModBlocks.BONE_BRICK_SLAB.get());

        tag(MINEABLE_WITH_AXE)
                .add(ModBlocks.LOG_GHOSTLY.get())
                .add(ModBlocks.GHOSTLY_PLANKS.get())
                .add(ModBlocks.GHOSTLY_PLANK_STAIRS.get())
                .add(ModBlocks.GHOSTLY_PLANK_SLAB.get())
                .add(ModBlocks.BLOODY_PLANK_STAIRS.get())
                .add(ModBlocks.BLOODY_PLANK_SLAB.get())
                .add(ModBlocks.LOG_BLOODY.get());

        tag(NEEDS_NEXON_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get())
                .add(ModBlocks.GHOSTLY_STONE.get())
                .add(ModBlocks.GHOSTLY_STONE_BRICKS.get())
                .add(ModBlocks.GHOSTLY_STONE_SLAB.get())
                .add(ModBlocks.GHOSTLY_STONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get())
                .add(ModBlocks.BONE_BRICK.get())
                .add(ModBlocks.BONE_BRICK_STAIRS.get())
                .add(ModBlocks.BONE_BRICK_SLAB.get());
                //.addTag(NEEDS_NEXON_TOOL);


        tag(NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get())
                .add(ModBlocks.GHOSTLY_STONE.get())
                .add(ModBlocks.GHOSTLY_STONE_SLAB.get())
                .add(ModBlocks.GHOSTLY_STONE_STAIRS.get())
                .add(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get())
                .add(ModBlocks.BONE_BRICK.get())
                .add(ModBlocks.BONE_BRICK_STAIRS.get())
                .add(ModBlocks.BONE_BRICK_SLAB.get());
                //.addTag(NEEDS_NETHERITE_TOOL)

        tag(INCORRECT_FOR_DIAMOND_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get())
                .add(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get())
                .add(ModBlocks.GHOSTLY_STONE.get())
                .add(ModBlocks.GHOSTLY_STONE_SLAB.get())
                .add(ModBlocks.GHOSTLY_STONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_STONE_BRICKS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get())
                .add(ModBlocks.BONE_BRICK.get())
                .add(ModBlocks.BONE_BRICK_STAIRS.get())
                .add(ModBlocks.BONE_BRICK_SLAB.get());

        tag(INCORRECT_FOR_IRON_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get())
                .add(ModBlocks.GHOSTLY_STONE.get())
                .add(ModBlocks.GHOSTLY_STONE_SLAB.get())
                .add(ModBlocks.GHOSTLY_STONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_STONE_BRICKS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get())
                .add(ModBlocks.BONE_BRICK.get())
                .add(ModBlocks.BONE_BRICK_STAIRS.get())
                .add(ModBlocks.BONE_BRICK_SLAB.get())
                .add(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get());

        tag(INCORRECT_FOR_GOLD_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get())
                .add(ModBlocks.GHOSTLY_STONE.get())
                .add(ModBlocks.GHOSTLY_STONE_SLAB.get())
                .add(ModBlocks.GHOSTLY_STONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_STONE_BRICKS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get())
                .add(ModBlocks.BONE_BRICK.get())
                .add(ModBlocks.BONE_BRICK_STAIRS.get())
                .add(ModBlocks.BONE_BRICK_SLAB.get())
                .add(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get());

        tag(INCORRECT_FOR_STONE_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get())
                .add(ModBlocks.GHOSTLY_STONE.get())
                .add(ModBlocks.GHOSTLY_STONE_SLAB.get())
                .add(ModBlocks.GHOSTLY_STONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_STONE_BRICKS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get())
                .add(ModBlocks.BONE_BRICK.get())
                .add(ModBlocks.BONE_BRICK_STAIRS.get())
                .add(ModBlocks.BONE_BRICK_SLAB.get())
                .add(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get());

        tag(INCORRECT_FOR_WOODEN_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get())
                .add(ModBlocks.GHOSTLY_STONE.get())
                .add(ModBlocks.GHOSTLY_STONE_SLAB.get())
                .add(ModBlocks.GHOSTLY_STONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_STONE_BRICKS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get())
                .add(ModBlocks.BONE_BRICK.get())
                .add(ModBlocks.BONE_BRICK_STAIRS.get())
                .add(ModBlocks.BONE_BRICK_SLAB.get())
                .add(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get());

        tag(DIRT)
                .add(ModBlocks.GHOSTLY_DIRT.get());
                //.add(ModBlocks.BLOODY_DIRT.get())

        tag(LOGS_THAT_BURN)
                .add(ModBlocks.LOG_GHOSTLY.get())
                .add(ModBlocks.LOG_BLOODY.get());
        tag(LEAVES)
                .add(ModBlocks.GHOSTLY_LEAVES.get())
                .add(ModBlocks.BLOODY_LEAVES.get());
        tag(SAPLINGS)
                .add(ModBlocks.GHOSTLY_SAPLING.get())
                .add(ModBlocks.BLOODY_SAPLING.get());
        tag(FLOWERS)
                .add(ModBlocks.OOZING_FLOWER.get());


        tag(PLANKS)
                .add(ModBlocks.GHOSTLY_PLANKS.get())
                .add(ModBlocks.BLOODY_PLANKS.get());
        tag(SLABS)
                .add(ModBlocks.GHOSTLY_PLANK_SLAB.get())
                .add(ModBlocks.BLOODY_PLANK_SLAB.get());
        tag(STAIRS)
                .add(ModBlocks.GHOSTLY_PLANK_STAIRS.get())
                .add(ModBlocks.BLOODY_PLANK_STAIRS.get());
        tag(FENCES)
                .add(ModBlocks.GHOSTLY_STONE_FENCE.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_FENCE.get())
                .add(ModBlocks.GHOSTLY_STONEBRICK_FENCE.get())
                .add(ModBlocks.BONE_BRICK_FENCE.get())
                .add(ModBlocks.GHOSTLY_PLANK_FENCE.get())
                .add(ModBlocks.BLOODY_PLANK_FENCE.get());
        tag(FENCE_GATES)
                .add(ModBlocks.GHOSTLY_STONE_FENCE_GATE.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_FENCE_GATE.get())
                .add(ModBlocks.GHOSTLY_STONEBRICK_FENCE_GATE.get())
                .add(ModBlocks.BONE_BRICK_FENCE_GATE.get())
                .add(ModBlocks.GHOSTLY_PLANK_FENCE_GATE.get())
                .add(ModBlocks.BLOODY_PLANK_FENCE_GATE.get());
        tag(WALLS)
                .add(ModBlocks.GHOSTLY_STONE_WALL.get())
                .add(ModBlocks.GHOSTLY_COBBLESTONE_WALL.get())
                .add(ModBlocks.GHOSTLY_STONEBRICK_WALL.get())
                .add(ModBlocks.BONE_BRICK_WALL.get())
                .add(ModBlocks.GHOSTLY_PLANK_WALL.get())
                .add(ModBlocks.BLOODY_PLANK_WALL.get());
        tag(DOORS)
                .add(ModBlocks.GHOSTLY_DOOR.get())
                .add(ModBlocks.BLOODY_DOOR.get());
        tag(TRAPDOORS)
                .add(ModBlocks.GHOSTLY_TRAPDOOR.get())
                .add(ModBlocks.BLOODY_TRAPDOOR.get());

        tag(PORTALS)
                .add(ModBlocks.GHOSTLY_PORTAL_BLOCK.get());
        tag(PORTAL_FRAME)
                .add(ModBlocks.BONE_BRICK.get());


        tag(CLIMBABLE)
                .add(ModBlocks.BLOODY_VINE.get());
    }
}
