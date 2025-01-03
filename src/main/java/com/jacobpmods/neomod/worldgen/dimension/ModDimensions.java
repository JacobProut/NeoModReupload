package com.jacobpmods.neomod.worldgen.dimension;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.worldgen.ModNoiseGeneratorSettings;
import com.jacobpmods.neomod.worldgen.biome.ModBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {

    public static final ResourceKey<LevelStem> AFTERLIFE_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "afterlife"));
    public static final ResourceKey<Level> AFTERLIFE_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "afterlife"));
    public static final ResourceKey<DimensionType> AFTERLIFE_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "afterlife_type"));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(AFTERLIFE_DIM_TYPE, new DimensionType(
                OptionalLong.of(18000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                8.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.END_EFFECTS, // effectsLocation
                0.5f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.GHOSTLY_BIOME)),
                noiseGenSettings.getOrThrow(ModNoiseGeneratorSettings.AFTERLIFE_DIMENSION_GENERATION)
        );

        //1: Temperature(Controls the "heat" of the biome. Lower values represent colder areas (e.g., snowy tundras), while higher values represent warmer areas (e.g., deserts))
        //2: Humidity(Determines the wetness or dryness of the biome. Lower values correspond to dry biomes (e.g., deserts), while higher values correspond to wet biomes (e.g., jungles, swamps))
        //3: Continentalness(Controls the distance from an ocean. Low values correspond to oceanic areas, while high values correspond to inland or central areas.)
        //4: Erosion(Affects the steepness or flatness of terrain) Negative values (e.g., -0.5) tend to create more jagged terrain (mountains), and positive values (e.g., 0.5) tend to create smoother, flatter terrain.)
        //5: Weirdness(Used to add variation to biome placement by introducing "weird" or unexpected biome combinations. It is often used for biomes like mushroom fields or other rare/unique terrain.)
        //6: Offset(Acts as a tiebreaker, determining how likely this biome is to spawn in overlapping areas.)
        //7: Noise Point Weight(Affects how this particular set of parameters influences biome placement when blended with others. Higher values increase the "weight" or significance of this parameter set in the overall biome map.)

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0.5F, 0.8F, 0.6F, 0.0F, 0.5F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.GHOSTLY_BIOME)),
                                Pair.of(Climate.parameters(0.8F, 0.5F, 0.5F, 0.2F, 0.3F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.BLOOD_GARDEN_BIOME)),
                                Pair.of(Climate.parameters(0.2F, 0.0F, 1.0F, 0.5F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.HEAVENLY_PLAINS_BIOME))
                        ))),
                noiseGenSettings.getOrThrow(ModNoiseGeneratorSettings.AFTERLIFE_DIMENSION_GENERATION));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.AFTERLIFE_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(AFTERLIFE_KEY, stem);
    }

}

