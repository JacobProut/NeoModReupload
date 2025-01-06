package com.jacobpmods.neomod.worldgen.biome;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {
    //                                      * Spawn Entities *
    //                                         Hostile Mobs
    public static final ResourceKey<BiomeModifier> SPAWN_SKELETAL_ZOMBIE = registerKey("spawn_skeletal_zombie");
    public static final ResourceKey<BiomeModifier> SPAWN_SKELETAL_ENDERMAN = registerKey("spawn_skeletal_enderman");

    //                                         Friendly Mobs
    public static final ResourceKey<BiomeModifier> SPAWN_SKELETAL_COW = registerKey("spawn_skeletal_cow");




    //                                      * Ghostly Biome *
    public static final ResourceKey<BiomeModifier> ADD_TREE_GHOSTLY = registerKey("add_tree_ghostly");
    public static final ResourceKey<BiomeModifier> ADD_OOZING_FLOWER = registerKey("add_oozing_flower");

    //                                    * Blood Garden Biome *
    public static final ResourceKey<BiomeModifier> ADD_TREE_BLOODY = registerKey("add_tree_bloody");
    public static final ResourceKey<BiomeModifier> ADD_BLOODY_BUSH = registerKey("add_bloody_bush");
    public static final ResourceKey<BiomeModifier> ADD_SKULL_N_BONES = registerKey("add_skull_n_bones");

    //Gilded Forest Biome
    public static final ResourceKey<BiomeModifier> ADD_TREE_GILDED = registerKey("add_tree_gilded");


    //                                          * ORES *
    public static final ResourceKey<BiomeModifier> ADD_AFTERLIFE_SHATTERED_FRAGMENT_ORE = registerKey("add_afterlife_dimension_shattered_fragment_ore");
    public static final ResourceKey<BiomeModifier> ADD_AFTERLIFE_SPIRIT_COAL_ORE = registerKey("add_afterlife_dimension_spirit_coal_ore");
    public static final ResourceKey<BiomeModifier> ADD_AFTERLIFE_IRON_ORE = registerKey("add_afterlife_dimension_iron_ore");
    public static final ResourceKey<BiomeModifier> ADD_AFTERLIFE_GOLD_ORE = registerKey("add_afterlife_dimension_gold_ore");
    public static final ResourceKey<BiomeModifier> ADD_AFTERLIFE_DIAMOND_ORE = registerKey("add_afterlife_dimension_diamond_ore");
    public static final ResourceKey<BiomeModifier> ADD_AFTERLIFE_REDSTONE_ORE = registerKey("add_afterlife_dimension_redstone_ore");

    //                                      * SUB LEVEL 5 ORES *
    public static final ResourceKey<BiomeModifier> ADD_SUBLVL5_AFTERLIFE_SHATTERED_FRAGMENT_ORE = registerKey("add_sublvl5_afterlife_dimension_shattered_fragment_ore");
    public static final ResourceKey<BiomeModifier> ADD_SUBLVL5_AFTERLIFE_SPIRIT_COAL_ORE = registerKey("add_sublvl5_afterlife_dimension_spirit_coal_ore");
    public static final ResourceKey<BiomeModifier> ADD_SUBLVL5_AFTERLIFE_IRON_ORE = registerKey("add_sublvl5_afterlife_dimension_iron_ore");
    public static final ResourceKey<BiomeModifier> ADD_SUBLVL5_AFTERLIFE_GOLD_ORE = registerKey("add_sublvl5_afterlife_dimension_gold_ore");
    public static final ResourceKey<BiomeModifier> ADD_SUBLVL5_AFTERLIFE_DIAMOND_ORE = registerKey("add_sublvl5_afterlife_dimension_diamond_ore");
    public static final ResourceKey<BiomeModifier> ADD_SUBLVL5_AFTERLIFE_REDSTONE_ORE = registerKey("add_sublvl5_afterlife_dimension_redstone_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        //                                    * ENTITIES *
        context.register(SPAWN_SKELETAL_ZOMBIE, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.SKELETAL_COW.get(), 22, 2, 5)))); //Weight, minCount, maxCount
        context.register(SPAWN_SKELETAL_ENDERMAN, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.SKELETAL_COW.get(), 20, 1, 4)))); //Weight, minCount, maxCount
        context.register(SPAWN_SKELETAL_COW, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.SKELETAL_COW.get(), 20, 2, 4)))); //Weight, minCount, maxCount


        //                                  * Ghostly Biome *
        //HolderSet created to send a direct link to the Modded Biome
        HolderSet<Biome> ghostlyBiomes = HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME));
        context.register(ADD_TREE_GHOSTLY, new BiomeModifiers.AddFeaturesBiomeModifier(
                ghostlyBiomes, // I assume change this to a different biome for it to spawn else where.
                // For multiple biomes use: HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.BIRCH_FOREST))
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GHOSTLY_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_OOZING_FLOWER, new BiomeModifiers.AddFeaturesBiomeModifier(
                ghostlyBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.OOZING_FLOWER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));


        //                                * Blood Garden Biome *
        HolderSet<Biome> bloodgardenBiome = HolderSet.direct(biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME));
        context.register(ADD_TREE_BLOODY, new BiomeModifiers.AddFeaturesBiomeModifier(
                bloodgardenBiome,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BLOODY_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_BLOODY_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                bloodgardenBiome,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BLOODY_BONE_BUSH_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_SKULL_N_BONES, new BiomeModifiers.AddFeaturesBiomeModifier(
                bloodgardenBiome,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SKULL_N_BONES_BLOCK_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));


        //                               * Gilded Forest Biome *
        HolderSet<Biome> gildedForestBiome = HolderSet.direct(biomes.getOrThrow(ModBiomes.GILDED_FOREST_BIOME));
        context.register(ADD_TREE_GILDED, new BiomeModifiers.AddFeaturesBiomeModifier(
                gildedForestBiome,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GILDED_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));


        //                                     * ORES *
        context.register(ADD_AFTERLIFE_SHATTERED_FRAGMENT_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_SHATTERED_FRAGMENT_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_AFTERLIFE_SPIRIT_COAL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_SPIRIT_COAL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_AFTERLIFE_IRON_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_IRON_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_AFTERLIFE_GOLD_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_GOLD_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_AFTERLIFE_DIAMOND_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_DIAMOND_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_AFTERLIFE_REDSTONE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_REDSTONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        //                              * SUB LEVEL 5 ORES *
        context.register(ADD_SUBLVL5_AFTERLIFE_SHATTERED_FRAGMENT_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_SUBLVL5_SHATTERED_FRAGMENT_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_SUBLVL5_AFTERLIFE_SPIRIT_COAL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_SUBLVL5_SPIRIT_COAL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_SUBLVL5_AFTERLIFE_IRON_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_SUBLVL5_IRON_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_SUBLVL5_AFTERLIFE_GOLD_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_SUBLVL5_GOLD_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_SUBLVL5_AFTERLIFE_DIAMOND_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_SUBLVL5_DIAMOND_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_SUBLVL5_AFTERLIFE_REDSTONE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME), biomes.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME), biomes.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AFTERLIFE_DIMENSION_SUBLVL5_REDSTONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, name));
    }
}
