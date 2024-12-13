package com.jacobpmods.neomod.datagen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.block.custom.blocks.BloodBoneBlossomBushBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FirstNeoMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        System.out.println("Registering block states and models");
        //ORES
        blockWithItem(ModBlocks.NEXON_BLOCK);
        blockWithItem(ModBlocks.NEXON_ORE_BLOCK);
        blockWithItem(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK);

        //Grass Blocks
        blockWithItem(ModBlocks.GHOSTLY_GRASS_BLOCK);
        blockWithItem(ModBlocks.GHOSTLY_DIRT);
        blockWithItem(ModBlocks.BLOODY_GRASS_BLOCK);
       // blockWithItem(ModBlocks.BLOODY_DIRT);

        /*horizontalBlock(ModBlocks.ENHANCER.get(), models().orientable("neomod:enhancer",
                //using "mcLoc" allows you to use minecraft blocks. like "block/blast_furnace_side"
                modLoc("block/enhancer_side"),
                modLoc("block/enhancer_front"),
                modLoc("block/enhancer_top")));*/
        blockItem(ModBlocks.ENHANCER);

        blockWithItem(ModBlocks.GHOSTLY_STONE);
        stairsBlock(((StairBlock) ModBlocks.GHOSTLY_STONE_STAIRS.get()), blockTexture(ModBlocks.GHOSTLY_STONE.get()));
        blockItem(ModBlocks.GHOSTLY_STONE_STAIRS);
        slabBlock(((SlabBlock) ModBlocks.GHOSTLY_STONE_SLAB.get()), blockTexture(ModBlocks.GHOSTLY_STONE.get()), blockTexture(ModBlocks.GHOSTLY_STONE.get()));
        blockItem(ModBlocks.GHOSTLY_STONE_SLAB);
        fenceBlock(((FenceBlock) ModBlocks.GHOSTLY_STONE_FENCE.get()), blockTexture(ModBlocks.GHOSTLY_STONE.get()));
        blockItem(ModBlocks.GHOSTLY_STONE_FENCE_GATE);
        fenceGateBlock(((FenceGateBlock) ModBlocks.GHOSTLY_STONE_FENCE_GATE.get()), blockTexture(ModBlocks.GHOSTLY_STONE.get()));
        wallBlock(((WallBlock) ModBlocks.GHOSTLY_STONE_WALL.get()), blockTexture(ModBlocks.GHOSTLY_STONE.get()));
        blockItem(ModBlocks.GHOSTLY_STONE_PRESSURE_PLATE);
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.GHOSTLY_STONE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.GHOSTLY_STONE.get()));
        buttonBlock(((ButtonBlock) ModBlocks.GHOSTLY_STONE_BUTTON.get()), blockTexture(ModBlocks.GHOSTLY_STONE.get()));



        blockWithItem(ModBlocks.GHOSTLY_COBBLESTONE);
        stairsBlock(((StairBlock) ModBlocks.GHOSTLY_COBBLESTONE_STAIRS.get()), blockTexture(ModBlocks.GHOSTLY_COBBLESTONE.get()));
        blockItem(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS);
        slabBlock(((SlabBlock) ModBlocks.GHOSTLY_COBBLESTONE_SLAB.get()), blockTexture(ModBlocks.GHOSTLY_COBBLESTONE.get()), blockTexture(ModBlocks.GHOSTLY_COBBLESTONE.get()));
        blockItem(ModBlocks.GHOSTLY_COBBLESTONE_SLAB);
        fenceBlock(((FenceBlock) ModBlocks.GHOSTLY_COBBLESTONE_FENCE.get()), blockTexture(ModBlocks.GHOSTLY_COBBLESTONE.get()));
        blockItem(ModBlocks.GHOSTLY_COBBLESTONE_FENCE_GATE);
        fenceGateBlock(((FenceGateBlock) ModBlocks.GHOSTLY_COBBLESTONE_FENCE_GATE.get()), blockTexture(ModBlocks.GHOSTLY_COBBLESTONE.get()));
        wallBlock(((WallBlock) ModBlocks.GHOSTLY_COBBLESTONE_WALL.get()), blockTexture(ModBlocks.GHOSTLY_COBBLESTONE.get()));
        blockItem(ModBlocks.GHOSTLY_COBBLESTONE_PRESSURE_PLATE);
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.GHOSTLY_COBBLESTONE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.GHOSTLY_COBBLESTONE.get()));
        buttonBlock(((ButtonBlock) ModBlocks.GHOSTLY_COBBLESTONE_BUTTON.get()), blockTexture(ModBlocks.GHOSTLY_COBBLESTONE.get()));


        blockWithItem(ModBlocks.GHOSTLY_STONE_BRICKS);
        stairsBlock(((StairBlock) ModBlocks.GHOSTLY_STONEBRICK_STAIRS.get()), blockTexture(ModBlocks.GHOSTLY_STONE_BRICKS.get()));
        blockItem(ModBlocks.GHOSTLY_STONEBRICK_STAIRS);
        slabBlock(((SlabBlock) ModBlocks.GHOSTLY_STONEBRICK_SLAB.get()), blockTexture(ModBlocks.GHOSTLY_STONE_BRICKS.get()), blockTexture(ModBlocks.GHOSTLY_STONE_BRICKS.get()));
        blockItem(ModBlocks.GHOSTLY_STONEBRICK_SLAB);
        fenceBlock(((FenceBlock) ModBlocks.GHOSTLY_STONEBRICK_FENCE.get()), blockTexture(ModBlocks.GHOSTLY_STONE_BRICKS.get()));
        blockItem(ModBlocks.GHOSTLY_STONEBRICK_FENCE_GATE);
        fenceGateBlock(((FenceGateBlock) ModBlocks.GHOSTLY_STONEBRICK_FENCE_GATE.get()), blockTexture(ModBlocks.GHOSTLY_STONE_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.GHOSTLY_STONEBRICK_WALL.get()), blockTexture(ModBlocks.GHOSTLY_STONE_BRICKS.get()));
        blockItem(ModBlocks.GHOSTLY_STONEBRICK_PRESSURE_PLATE);
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.GHOSTLY_STONEBRICK_PRESSURE_PLATE.get()), blockTexture(ModBlocks.GHOSTLY_STONE_BRICKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.GHOSTLY_STONEBRICK_BUTTON.get()), blockTexture(ModBlocks.GHOSTLY_STONE_BRICKS.get()));

        blockWithItem(ModBlocks.BONE_BRICK);
        stairsBlock(((StairBlock) ModBlocks.BONE_BRICK_STAIRS.get()), blockTexture(ModBlocks.BONE_BRICK.get()));
        blockItem(ModBlocks.BONE_BRICK_STAIRS);
        slabBlock(((SlabBlock) ModBlocks.BONE_BRICK_SLAB.get()), blockTexture(ModBlocks.BONE_BRICK.get()), blockTexture(ModBlocks.BONE_BRICK.get()));
        blockItem(ModBlocks.BONE_BRICK_SLAB);
        fenceBlock(((FenceBlock) ModBlocks.BONE_BRICK_FENCE.get()), blockTexture(ModBlocks.BONE_BRICK.get()));
        blockItem(ModBlocks.BONE_BRICK_FENCE_GATE);
        fenceGateBlock(((FenceGateBlock) ModBlocks.BONE_BRICK_FENCE_GATE.get()), blockTexture(ModBlocks.BONE_BRICK.get()));
        wallBlock(((WallBlock) ModBlocks.BONE_BRICK_WALL.get()), blockTexture(ModBlocks.BONE_BRICK.get()));
        blockItem(ModBlocks.BONE_BRICK_PRESSURE_PLATE);
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.BONE_BRICK_PRESSURE_PLATE.get()), blockTexture(ModBlocks.BONE_BRICK.get()));
        buttonBlock(((ButtonBlock) ModBlocks.BONE_BRICK_BUTTON.get()), blockTexture(ModBlocks.BONE_BRICK.get()));


        System.out.println("Block states and models:logBlock-axisBlock's");


        blockItem(ModBlocks.LOG_GHOSTLY);
        logBlock(((RotatedPillarBlock) ModBlocks.LOG_GHOSTLY.get()));
        blockItem(ModBlocks.WOOD_GHOSTLY);
        axisBlock(((RotatedPillarBlock) ModBlocks.WOOD_GHOSTLY.get()), blockTexture(ModBlocks.LOG_GHOSTLY.get()), blockTexture(ModBlocks.LOG_GHOSTLY.get()));
        blockItem(ModBlocks.STRIPPED_GHOSTLY_LOG);
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_GHOSTLY_LOG.get()));
        blockItem(ModBlocks.STRIPPED_GHOSTLY_WOOD);
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_GHOSTLY_WOOD.get()), blockTexture(ModBlocks.STRIPPED_GHOSTLY_LOG.get()), blockTexture(ModBlocks.STRIPPED_GHOSTLY_LOG.get()));

        blockWithItem(ModBlocks.GHOSTLY_PLANKS);
        stairsBlock(((StairBlock) ModBlocks.GHOSTLY_PLANK_STAIRS.get()), blockTexture(ModBlocks.GHOSTLY_PLANKS.get()));
        blockItem(ModBlocks.GHOSTLY_PLANK_STAIRS);
        slabBlock(((SlabBlock) ModBlocks.GHOSTLY_PLANK_SLAB.get()), blockTexture(ModBlocks.GHOSTLY_PLANKS.get()), blockTexture(ModBlocks.GHOSTLY_PLANKS.get()));
        blockItem(ModBlocks.GHOSTLY_PLANK_SLAB);
        fenceBlock(((FenceBlock) ModBlocks.GHOSTLY_PLANK_FENCE.get()), blockTexture(ModBlocks.GHOSTLY_PLANKS.get()));
        blockItem(ModBlocks.GHOSTLY_PLANK_FENCE_GATE);
        fenceGateBlock(((FenceGateBlock) ModBlocks.GHOSTLY_PLANK_FENCE_GATE.get()), blockTexture(ModBlocks.GHOSTLY_PLANKS.get()));
        wallBlock(((WallBlock) ModBlocks.GHOSTLY_PLANK_WALL.get()), blockTexture(ModBlocks.GHOSTLY_PLANKS.get()));
        blockItem(ModBlocks.GHOSTLY_PLANK_PRESSURE_PLATE);
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.GHOSTLY_PLANK_PRESSURE_PLATE.get()), blockTexture(ModBlocks.GHOSTLY_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.GHOSTLY_PLANK_BUTTON.get()), blockTexture(ModBlocks.GHOSTLY_PLANKS.get()));
        doorBlockWithRenderType(((DoorBlock) ModBlocks.GHOSTLY_DOOR.get()), modLoc("block/ghostly_door_bottom"), modLoc("block/ghostly_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.GHOSTLY_TRAPDOOR.get()), modLoc("block/ghostly_trapdoor"), true, "cutout");
        blockItem(ModBlocks.GHOSTLY_TRAPDOOR, "_bottom");
        leavesBlock(ModBlocks.GHOSTLY_LEAVES);
        saplingBlock(ModBlocks.GHOSTLY_SAPLING);

        //ADD ROTATION PLACEMENT FOR BLOODY LOGS
        blockItem(ModBlocks.LOG_BLOODY);
        logBlock(((RotatedPillarBlock) ModBlocks.LOG_BLOODY.get()));

        blockWithItem(ModBlocks.BLOODY_PLANKS);
        stairsBlock(((StairBlock) ModBlocks.BLOODY_PLANK_STAIRS.get()), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
        blockItem(ModBlocks.BLOODY_PLANK_STAIRS);
        slabBlock(((SlabBlock) ModBlocks.BLOODY_PLANK_SLAB.get()), blockTexture(ModBlocks.BLOODY_PLANKS.get()), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
        blockItem(ModBlocks.BLOODY_PLANK_SLAB);
        fenceBlock(((FenceBlock) ModBlocks.BLOODY_PLANK_FENCE.get()), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
        blockItem(ModBlocks.BLOODY_PLANK_FENCE_GATE);
        fenceGateBlock(((FenceGateBlock) ModBlocks.BLOODY_PLANK_FENCE_GATE.get()), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
        wallBlock(((WallBlock) ModBlocks.BLOODY_PLANK_WALL.get()), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
        blockItem(ModBlocks.BLOODY_PLANK_PRESSURE_PLATE);
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.BLOODY_PLANK_PRESSURE_PLATE.get()), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.BLOODY_PLANK_BUTTON.get()), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
        doorBlockWithRenderType(((DoorBlock) ModBlocks.BLOODY_DOOR.get()), modLoc("block/bloody_door_bottom"), modLoc("block/bloody_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.BLOODY_TRAPDOOR.get()), modLoc("block/bloody_trapdoor"), true, "cutout");
        blockItem(ModBlocks.BLOODY_TRAPDOOR, "_bottom");
        leavesBlock(ModBlocks.BLOODY_LEAVES);
        saplingBlock(ModBlocks.BLOODY_SAPLING);


        simpleBlock(ModBlocks.OOZING_FLOWER.get(),
                models().cross(blockTexture(ModBlocks.OOZING_FLOWER.get()).getPath(), blockTexture(ModBlocks.OOZING_FLOWER.get())).renderType("cutout"));

        makeBush(((BloodBoneBlossomBushBlock) ModBlocks.BLOOD_BONE_FRUIT_BUSH.get()), "blood_bone_blossom_bush_stage", "blood_bone_blossom_bush_stage");

        blockItem(ModBlocks.GHOSTLY_WEB);
        simpleBlock(ModBlocks.GHOSTLY_WEB.get(),
                models().cross("ghostly_web", modLoc("block/ghostly_web")).renderType("cutout"));
        blockWithItem(ModBlocks.SKULL_N_BONES);

        simpleBlock(ModBlocks.GHOSTLY_PORTAL_BLOCK.get());
   }

    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(BloodBoneBlossomBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "block/" + textureName + state.getValue(BloodBoneBlossomBushBlock.AGE))).renderType("cutout"));

        return models;
    }


    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void leavesBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void saplingBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().cross(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("neomod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("neomod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
