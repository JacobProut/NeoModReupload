package com.jacobpmods.neomod.datagen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.block.custom.BloodBoneBlossomBushBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.VineBlock;
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

        horizontalBlock(ModBlocks.ENHANCER.get(), models().orientable("neomod:enhancer",
                //using "mcLoc" allows you to use minecraft blocks. like "block/blast_furnace_side"
                modLoc("block/enhancer_side"),
                modLoc("block/enhancer_front"),
                modLoc("block/enhancer_top")));
        blockItem(ModBlocks.ENHANCER);

        blockWithItem(ModBlocks.BONE_BRICK);


        System.out.println("Block states and models:logBlock-axisBlock's");
        logBlock(((RotatedPillarBlock) ModBlocks.LOG_GHOSTLY.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.WOOD_GHOSTLY.get()), blockTexture(ModBlocks.LOG_GHOSTLY.get()), blockTexture(ModBlocks.LOG_GHOSTLY.get()));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_GHOSTLY_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_GHOSTLY_WOOD.get()), blockTexture(ModBlocks.STRIPPED_GHOSTLY_LOG.get()), blockTexture(ModBlocks.STRIPPED_GHOSTLY_LOG.get()));


        blockItem(ModBlocks.LOG_GHOSTLY);
        blockItem(ModBlocks.WOOD_GHOSTLY);
        blockItem(ModBlocks.STRIPPED_GHOSTLY_LOG);
        blockItem(ModBlocks.STRIPPED_GHOSTLY_WOOD);

        blockWithItem(ModBlocks.GHOSTLY_WEB);
        blockWithItem(ModBlocks.GHOSTLY_STONE);


        blockWithItem(ModBlocks.PLANKS_GHOSTLY);
        leavesBlock(ModBlocks.GHOSTLY_LEAVES);
        saplingBlock(ModBlocks.GHOSTLY_SAPLING);

        leavesBlock(ModBlocks.BLOODY_LEAVES);
        logBlock(((RotatedPillarBlock) ModBlocks.LOG_BLOODY.get()));
        blockItem(ModBlocks.LOG_BLOODY);
        saplingBlock(ModBlocks.BLOODY_SAPLING);
        blockWithItem(ModBlocks.PLANKS_BLOODY);


        simpleBlock(ModBlocks.OOZING_FLOWER.get(),
                models().cross(blockTexture(ModBlocks.OOZING_FLOWER.get()).getPath(), blockTexture(ModBlocks.OOZING_FLOWER.get())).renderType("cutout"));

        blockWithItem(ModBlocks.SKULL_N_BONES);

        makeBush(((BloodBoneBlossomBushBlock) ModBlocks.BLOOD_BONE_FRUIT_BUSH.get()), "blood_bone_blossom_bush_stage", "blood_bone_blossom_bush_stage");

        //NEEDS TO BE FIXED
       // vineBlock(ModBlocks.BLOODY_VINE);
   }

    private void vineBlock(DeferredBlock<VineBlock> deferredBlock) {
        getVariantBuilder(deferredBlock.get())
                .forAllStates(state -> {
                    String path = BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath();
                    return ConfiguredModel.builder()
                            .modelFile(models().singleTexture(path, mcLoc("block/vine"), "vine", blockTexture(deferredBlock.get())))
                            .build();
                });
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
