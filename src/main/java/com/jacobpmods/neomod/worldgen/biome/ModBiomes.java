package com.jacobpmods.neomod.worldgen.biome;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;

public class ModBiomes {
    public static final ResourceKey<Biome> GHOSTLY_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "ghostly_biome"));
    public static final ResourceKey<Biome> BLOOD_GARDEN_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "blood_garden_biome"));
    public static final ResourceKey<Biome> HEAVENLY_PLAINS_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "heavenly_plains_biome"));
    public static final ResourceKey<Biome> GILDED_FOREST_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "gilded_forest_biome"));


    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(GHOSTLY_BIOME, ghostlyBiome(context));
        context.register(BLOOD_GARDEN_BIOME, bloodGarden(context));
        context.register(HEAVENLY_PLAINS_BIOME, heavenlyPlains(context));
        context.register(GILDED_FOREST_BIOME, gildedForest(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);    // Amethyst spawn
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);          // Mob Spawners
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
       // BiomeDefaultFeatures.addSurfaceFreezing(builder);           // Snow spawning on top
    }

    public static Biome ghostlyBiome(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.RHINO.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        /*BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);*/

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        //BiomeDefaultFeatures.addFerns(biomeBuilder);
        //BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);
        //BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        //BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PINE_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(1.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xFF32CD32)
                        .waterFogColor(0xFF6666)
                        .skyColor(0xD3D3D3)
                        .grassColorOverride(0xADD8E6) // Grass color
                        //.foliageColorOverride(0xd203fc) //Leaves/Ferms n stuff like that
                        .fogColor(0xD3D3D3)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        //.backgroundMusic(Musics.createGameMusic(ModSounds.BAR_BRAWL.getHolder().get())).build())
                        .build()).build();
    }

    public static Biome bloodGarden(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();


        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));


        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        //Create a bloody tree
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.GHOSTLY_TREE_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(1.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xFF0000)
                        .waterFogColor(0xFF6666)
                        .skyColor(0xD3D3D3)
                        .grassColorOverride(0xADD8E6) // Grass color
                        //.foliageColorOverride(0xd203fc) //Leaves/Ferms n stuff like that
                        .fogColor(0xD3D3D3)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        //.backgroundMusic(Musics.createGameMusic(ModSounds.BAR_BRAWL.getHolder().get())).build())
                        .build()).build();
    }

    public static Biome heavenlyPlains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.4f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xFFE0FFFF)
                        .waterFogColor(0xFF00FFFF)
                        .skyColor(0xFFF5F5F5)
                        .grassColorOverride(0xFFFFFFFF) // Grass color
                        //.foliageColorOverride(0xd203fc) //Leaves/Ferms n stuff like that
                        .fogColor(0xFFFFFFE0)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        //.backgroundMusic(Musics.createGameMusic(ModSounds.BAR_BRAWL.getHolder().get())).build())
                        .build()).build();
    }

    public static Biome gildedForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.6f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xFFFFD700)
                        .waterFogColor(0xFFFFE8A0)
                        .skyColor(0xFFFFFFE0)
                        .grassColorOverride(0xFFFAE1A1) // Grass color
                        .fogColor(0xA0A0A0)
                        //.foliageColorOverride(0xd203fc) //Leaves/Ferms n stuff like that
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build()).build();
    }

}
