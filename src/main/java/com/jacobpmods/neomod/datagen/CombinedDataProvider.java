package com.jacobpmods.neomod.datagen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.custom.enchantment.ModEnchantments;
import com.jacobpmods.neomod.worldgen.ModBiomeModifiers;
import com.jacobpmods.neomod.worldgen.ModConfiguredFeatures;
import com.jacobpmods.neomod.worldgen.ModNoiseGeneratorSettings;
import com.jacobpmods.neomod.worldgen.ModPlacedFeatures;
import com.jacobpmods.neomod.worldgen.biome.ModBiomes;
import com.jacobpmods.neomod.worldgen.dimension.ModDimensions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CombinedDataProvider extends DatapackBuiltinEntriesProvider {
    public CombinedDataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, buildRegistrySet(), Set.of(FirstNeoMod.MOD_ID));
    }

    private static RegistrySetBuilder buildRegistrySet() {
        return new RegistrySetBuilder()
                .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
                .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
                .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
                .add(Registries.BIOME, ModBiomes::bootstrap)
                .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)

                .add(Registries.NOISE_SETTINGS, ModNoiseGeneratorSettings::bootstrap);
    }

}