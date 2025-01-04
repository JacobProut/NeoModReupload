package com.jacobpmods.neomod.worldgen.biome;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.block.custom.blocks.BloodBoneBlossomBushBlock;
import com.jacobpmods.neomod.worldgen.tree.treeDecorators.BloodVineDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class ModConfiguredFeatures {
    // Configured Features -> Placed Features -> Placed inside of world Via BiomeModifiers
    public static final ResourceKey<ConfiguredFeature<?, ?>> GHOSTLY_KEY = registerKey("ghostly");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODY_KEY = registerKey("bloody");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HEAVENLY_KEY = registerKey("heavenly");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GILDED_KEY = registerKey("gilded");


    //Bushes and flowers
    public static final ResourceKey<ConfiguredFeature<?, ?>> OOZING_FLOWER = registerKey("oozing_flower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_BONE_BUSH = registerKey("blood_bone_bush");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SKULL_N_BONE_BLOCK = registerKey("skull_n_bone_block");

    //Ores
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_SHATTERED_FRAGMENT_ORE_KEY = registerKey("afterlife_shattered_fragment");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_SPIRIT_COAL_ORE_KEY = registerKey("afterlife_spirit_coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_IRON_ORE_KEY = registerKey("afterlife_iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_GOLD_ORE_KEY = registerKey("afterlife_gold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_DIAMOND_ORE_KEY = registerKey("afterlife_diamond_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_REDSTONE_ORE_KEY = registerKey("afterlife_redstone_ore");

    //SUB LVL 5 ORES
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_SUBLVL5_SHATTERED_FRAGMENT_ORE_KEY = registerKey("afterlife_sublvl5_shattered_fragment");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_SUBLVL5_SPIRIT_COAL_ORE_KEY = registerKey("afterlife_sublvl5_spirit_coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_SUBLVL5_IRON_ORE_KEY = registerKey("afterlife_sublvl5_iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_SUBLVL5_GOLD_ORE_KEY = registerKey("afterlife_sublvl5_gold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_SUBLVL5_DIAMOND_ORE_KEY = registerKey("afterlife_sublvl5_diamond_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AFTERLIFE_SUBLVL5_REDSTONE_ORE_KEY = registerKey("afterlife_sublvl5_redstone_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, GHOSTLY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_GHOSTLY.get()),
                new StraightTrunkPlacer(3, 3, 3), //|Defines trunk of the tree| Base height of the trunk, Random additional height that can be added, Height of the trunk where branches might appear or additional foliage can grow.
                BlockStateProvider.simple(ModBlocks.GHOSTLY_LEAVES.get()), //Specifies what blocks for leaves
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3), //|Determines how leaves are placed around the trunk| Radius of foliage blob at top most part of tree, Controls the foliage radius as you go downwards from the top[For each level, the radius decrease 'Said' blocks, The number of layers of leaves that will be placed starting from the top of the trunk downwards.
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(ModBlocks.GHOSTLY_DIRT.get())) // Custom soil block
                .build() //|Controls the number of layers at the top of the tree where branches can spawn| The size of the truck that can be uninterrupted at the base, the additional radius added to first layer, additional height for second layer
        );

        register(context, OOZING_FLOWER, Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.OOZING_FLOWER.get())))));

        register(context, BLOODY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_BLOODY.get()),
                new StraightTrunkPlacer(3, 3, 3), //|Defines trunk of the tree| Base height of the trunk, Random additional height that can be added, Height of the trunk where branches might appear or additional foliage can grow.
                BlockStateProvider.simple(ModBlocks.BLOODY_LEAVES.get()), //Specifies what blocks for leaves
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), //|Determines how leaves are placed around the trunk| Radius of foliage blob at top most part of tree, Controls the foliage radius as you go downwards from the top[For each level, the radius decrease 'Said' blocks, The number of layers of leaves that will be placed starting from the top of the trunk downwards.
                new TwoLayersFeatureSize(1, 0, 1))
                .dirt(BlockStateProvider.simple(ModBlocks.GHOSTLY_DIRT.get())) //Custom Soil Block
                .decorators(List.of(new BloodVineDecorator(0.25F)))
                .build() //|Controls the number of layers at the top of the tree where branches can spawn| The size of the truck that can be uninterrupted at the base, the additional radius added to first layer, additional height for second layer
        );

        register(context, GILDED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_GILDED.get()),
                new StraightTrunkPlacer(3, 3, 3), //|Defines trunk of the tree| Base height of the trunk, Random additional height that can be added, Height of the trunk where branches might appear or additional foliage can grow.
                BlockStateProvider.simple(ModBlocks.GILDED_LEAVES.get()), //Specifies what blocks for leaves
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3), //|Determines how leaves are placed around the trunk| Radius of foliage blob at top most part of tree, Controls the foliage radius as you go downwards from the top[For each level, the radius decrease 'Said' blocks, The number of layers of leaves that will be placed starting from the top of the trunk downwards.
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(ModBlocks.GILDED_DIRT.get())) // Custom soil block
                .build() //|Controls the number of layers at the top of the tree where branches can spawn| The size of the truck that can be uninterrupted at the base, the additional radius added to first layer, additional height for second layer
        );

        register(context, BLOOD_BONE_BUSH, Feature.FLOWER, new RandomPatchConfiguration(12, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLOOD_BONE_FRUIT_BUSH.get().defaultBlockState().setValue(BloodBoneBlossomBushBlock.AGE, 3))))));

        register(context, SKULL_N_BONE_BLOCK, Feature.RANDOM_PATCH, new RandomPatchConfiguration(2, 7, 0,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SKULL_N_BONES.get().defaultBlockState())))));

    RuleTest ghostlyStoneReplacable = new BlockMatchTest(ModBlocks.GHOSTLY_STONE.get());
    //used for overworld
   /* List<OreConfiguration.TargetBlockState> ghostlyShatteredFragmentOre = List.of(
            OreConfiguration.target(ghostlyStoneReplacable, ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get().defaultBlockState()));*/
                                                                                                                                                                                        //Number of blocks that can spawn in a vein
    register(context, AFTERLIFE_SHATTERED_FRAGMENT_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get().defaultBlockState(), 4));
    register(context, AFTERLIFE_SPIRIT_COAL_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.SPIRIT_COAL_ORE_BLOCK.get().defaultBlockState(), 12));
    register(context, AFTERLIFE_IRON_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.AFTERLIFE_IRON_ORE_BLOCK.get().defaultBlockState(), 10));
    register(context, AFTERLIFE_GOLD_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.AFTERLIFE_GOLD_ORE_BLOCK.get().defaultBlockState(), 10));
    register(context, AFTERLIFE_DIAMOND_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.AFTERLIFE_DIAMOND_ORE_BLOCK.get().defaultBlockState(), 8));
    register(context, AFTERLIFE_REDSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.AFTERLIFE_REDSTONE_ORE_BLOCK.get().defaultBlockState(), 12));
    //SUBLVL5 ORES
    register(context, AFTERLIFE_SUBLVL5_SHATTERED_FRAGMENT_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK.get().defaultBlockState(), 4));
    register(context, AFTERLIFE_SUBLVL5_SPIRIT_COAL_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.SPIRIT_COAL_ORE_BLOCK.get().defaultBlockState(), 10));
    register(context, AFTERLIFE_SUBLVL5_IRON_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.AFTERLIFE_IRON_ORE_BLOCK.get().defaultBlockState(), 12));
    register(context, AFTERLIFE_SUBLVL5_GOLD_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.AFTERLIFE_GOLD_ORE_BLOCK.get().defaultBlockState(), 12));
    register(context, AFTERLIFE_SUBLVL5_DIAMOND_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.AFTERLIFE_DIAMOND_ORE_BLOCK.get().defaultBlockState(), 12));
    register(context, AFTERLIFE_SUBLVL5_REDSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(ghostlyStoneReplacable, ModBlocks.AFTERLIFE_REDSTONE_ORE_BLOCK.get().defaultBlockState(), 10));

    }
    //Random Patch info
    //The first number (Tries) is the maximum number of patches that can be generated in a chunk. It defines how many patches of the feature can appear.
    //The second number (xzSpread) defines the size of each patch. It controls the maximum number of blocks in each patch.
    //The third number (ySpread) is the spread radius in each direction. This means that the blocks will spread out randomly in a 2x2 block area around the placement point, giving a sense of randomness.


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
