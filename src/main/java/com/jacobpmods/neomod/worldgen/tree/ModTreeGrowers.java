package com.jacobpmods.neomod.worldgen.tree;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.worldgen.biome.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower GHOSTLY = new TreeGrower(FirstNeoMod.MOD_ID + ":ghostly",
            Optional.empty(), Optional.of(ModConfiguredFeatures.GHOSTLY_KEY), Optional.empty());

    public static final TreeGrower BLOODY = new TreeGrower(FirstNeoMod.MOD_ID + ":bloody",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLOODY_KEY), Optional.empty());

    public static final TreeGrower GILDED = new TreeGrower(FirstNeoMod.MOD_ID + ":gilded",
            Optional.empty(), Optional.of(ModConfiguredFeatures.GILDED_KEY), Optional.empty());
}
