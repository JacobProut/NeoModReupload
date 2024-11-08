package com.jacobpmods.neomod.worldgen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TREE_GHOSTLY = registerKey("add_tree_ghostly");



    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        //HolderSet created to send a direct link to the Modded Biome
        HolderSet<Biome> ghostlyBiomes = HolderSet.direct(biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME));

        //This is for combined biomes
       /* HolderSet<Biome> combinedBiomes = HolderSet.direct(
                biomes.getOrThrow(ModBiomes.GHOSTLY_BIOME_KEY),
                biomes.getOrThrow(Biomes.BIRCH_FOREST)
        );*/

        context.register(ADD_TREE_GHOSTLY, new BiomeModifiers.AddFeaturesBiomeModifier(
            ghostlyBiomes, // I assume change this to a different biome for it to spawn else where.
                // For multiple biomes use: HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.BIRCH_FOREST))
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GHOSTLY_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, name));
    }
}
