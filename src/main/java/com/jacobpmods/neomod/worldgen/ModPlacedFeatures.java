package com.jacobpmods.neomod.worldgen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    //Ghostly Biome
    public static final ResourceKey<PlacedFeature> GHOSTLY_TREE_PLACED_KEY = registerKey("ghostly_tree_placed");
    public static final ResourceKey<PlacedFeature> OOZING_FLOWER_PLACED_KEY = registerKey("oozing_flower_placed");

    //Blood biome
    public static final ResourceKey<PlacedFeature> BLOODY_TREE_PLACED_KEY = registerKey("bloody_tree_placed");
    public static final ResourceKey<PlacedFeature> BLOODY_BONE_BUSH_KEY = registerKey("bloody_bone_bush_placed");
    public static final ResourceKey<PlacedFeature> SKULL_N_BONES_BLOCK_KEY = registerKey("skull_n_bones_block_placed");

    //Ore Gen
    public static final ResourceKey<PlacedFeature> GHOSTLY_DIMENSION_SHATTERED_FRAGMENT_ORE_PLACED_KEY = registerKey("shattered_fragment_ore_placed");
    public static final ResourceKey<PlacedFeature> GHOSTLY_DIMENSION_SPIRIT_COAL_ORE_PLACED_KEY = registerKey("spirit_coal_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, GHOSTLY_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GHOSTLY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.GHOSTLY_SAPLING.get()));
        register(context,OOZING_FLOWER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OOZING_FLOWER),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context, BLOODY_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOODY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.BLOODY_SAPLING.get()));

        register(context, BLOODY_BONE_BUSH_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_BONE_BUSH),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

       /* register(context, SKULL_N_BONES_BLOCK_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SKULL_N_BONE_BLOCK),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));*/

        register(context, SKULL_N_BONES_BLOCK_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SKULL_N_BONE_BLOCK),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        PlacementUtils.HEIGHTMAP_TOP_SOLID, //TOP_SOLID NEEDED TO MAKE SURE IT DOESN'T SPAWN ON WATER AS MUCH
                        BiomeFilter.biome()
                ));

        register(context, GHOSTLY_DIMENSION_SHATTERED_FRAGMENT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GHOSTLY_SHATTERED_FRAGMENT_ORE_KEY),
                ModOrePlacement.commonOrePlacement(10, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(45)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious

        register(context, GHOSTLY_DIMENSION_SPIRIT_COAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GHOSTLY_SPIRIT_COAL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(15, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(100)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious

    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        LogUtils.getLogger().debug("REGISTER_KEY: Creating ResourceKey for " + name);
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, name));
    }
    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        LogUtils.getLogger().debug("REGISTER: Registering PlacedFeature with key " + key.location());
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
