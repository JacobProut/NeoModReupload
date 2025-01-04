package com.jacobpmods.neomod.worldgen.biome;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.worldgen.ModOrePlacement;
import com.jacobpmods.neomod.worldgen.biome.ModConfiguredFeatures;
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

    public static final ResourceKey<PlacedFeature> GILDED_TREE_PLACED_KEY = registerKey("gilded_tree_placed");

    //Ore Gen
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_SHATTERED_FRAGMENT_ORE_PLACED_KEY = registerKey("shattered_fragment_ore_placed");
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_SPIRIT_COAL_ORE_PLACED_KEY = registerKey("spirit_coal_ore_placed");
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_IRON_ORE_PLACED_KEY = registerKey("iron_ore_placed");
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_GOLD_ORE_PLACED_KEY = registerKey("gold_ore_placed");
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_DIAMOND_ORE_PLACED_KEY = registerKey("diamond_ore_placed");
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_REDSTONE_ORE_PLACED_KEY = registerKey("redstone_ore_placed");

    //Sub Y level 5 Ores
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_SUBLVL5_SHATTERED_FRAGMENT_ORE_PLACED_KEY = registerKey("sublvl5_shattered_fragment_ore_placed");
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_SUBLVL5_SPIRIT_COAL_ORE_PLACED_KEY = registerKey("sublvl5_spirit_coal_ore_placed");
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_SUBLVL5_IRON_ORE_PLACED_KEY = registerKey("sublvl5_iron_ore_placed");
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_SUBLVL5_GOLD_ORE_PLACED_KEY = registerKey("sublvl5_gold_ore_placed");
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_SUBLVL5_DIAMOND_ORE_PLACED_KEY = registerKey("sublvl5_diamond_ore_placed");
    public static final ResourceKey<PlacedFeature> AFTERLIFE_DIMENSION_SUBLVL5_REDSTONE_ORE_PLACED_KEY = registerKey("sublvl5_redstone_ore_placed");

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


        register(context, GILDED_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GILDED_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.GILDED_SAPLING.get()));


       /* register(context, SKULL_N_BONES_BLOCK_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SKULL_N_BONE_BLOCK),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));*/

        register(context, SKULL_N_BONES_BLOCK_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SKULL_N_BONE_BLOCK),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        PlacementUtils.HEIGHTMAP_TOP_SOLID, //TOP_SOLID NEEDED TO MAKE SURE IT DOESN'T SPAWN ON WATER AS MUCH
                        BiomeFilter.biome()
                ));

        //ORES
        register(context, AFTERLIFE_DIMENSION_SHATTERED_FRAGMENT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_SHATTERED_FRAGMENT_ORE_KEY),
                ModOrePlacement.commonOrePlacement(10, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(45)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        register(context, AFTERLIFE_DIMENSION_SPIRIT_COAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_SPIRIT_COAL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(15, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(100)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        register(context, AFTERLIFE_DIMENSION_IRON_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_IRON_ORE_KEY),
                ModOrePlacement.commonOrePlacement(25, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(160)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        register(context, AFTERLIFE_DIMENSION_GOLD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_GOLD_ORE_KEY),
                ModOrePlacement.commonOrePlacement(35, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(8), VerticalAnchor.absolute(256)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        register(context, AFTERLIFE_DIMENSION_DIAMOND_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_DIAMOND_ORE_KEY),
                ModOrePlacement.commonOrePlacement(7, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(80)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        register(context, AFTERLIFE_DIMENSION_REDSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_REDSTONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(32)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        //SUB Y LEVEL 5 ORES
        register(context, AFTERLIFE_DIMENSION_SUBLVL5_SHATTERED_FRAGMENT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_SUBLVL5_SHATTERED_FRAGMENT_ORE_KEY),
                ModOrePlacement.commonOrePlacement(4, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(4)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        register(context, AFTERLIFE_DIMENSION_SUBLVL5_SPIRIT_COAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_SUBLVL5_SPIRIT_COAL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(4)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        register(context, AFTERLIFE_DIMENSION_SUBLVL5_IRON_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_SUBLVL5_IRON_ORE_KEY),
                ModOrePlacement.commonOrePlacement(10, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(4)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        register(context, AFTERLIFE_DIMENSION_SUBLVL5_GOLD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_SUBLVL5_GOLD_ORE_KEY),
                ModOrePlacement.commonOrePlacement(10, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(4)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        register(context, AFTERLIFE_DIMENSION_SUBLVL5_DIAMOND_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_SUBLVL5_DIAMOND_ORE_KEY),
                ModOrePlacement.rareOrePlacement(6, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(4)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
        register(context, AFTERLIFE_DIMENSION_SUBLVL5_REDSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AFTERLIFE_SUBLVL5_REDSTONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8, //Value for amount wanted spawning in chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(4)))); //HeightRangePlacement has another option called .triangle instead of .uniform . Look into it if curious
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
