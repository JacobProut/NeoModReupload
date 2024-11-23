package com.jacobpmods.neomod.worldgen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.block.custom.BloodBoneBlossomBushBlock;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    // Configured Features -> Placed Features -> Placed inside of world Via BiomeModifiers
    public static final ResourceKey<ConfiguredFeature<?, ?>> GHOSTLY_KEY = registerKey("ghostly");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OOZING_FLOWER = registerKey("oozing_flower");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_BONE_BUSH = registerKey("blood_bone_bush");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODY_KEY = registerKey("bloody");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        LogUtils.getLogger().debug("BOOTSTRAP: Registering Configured Features for GHOSTLY_KEY");
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
                .dirt(BlockStateProvider.simple(ModBlocks.GHOSTLY_DIRT.get())) // Custom soil block
                .build() //|Controls the number of layers at the top of the tree where branches can spawn| The size of the truck that can be uninterrupted at the base, the additional radius added to first layer, additional height for second layer
        );

        register(context, BLOOD_BONE_BUSH, Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLOOD_BONE_FRUIT_BUSH.get().defaultBlockState().setValue(BloodBoneBlossomBushBlock.AGE, 3))))));

    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
