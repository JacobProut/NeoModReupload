package com.jacobpmods.neomod.worldgen.biome.surface;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import org.spongepowered.include.com.google.common.collect.ImmutableList;


public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource MOD_DIRT = makeStateRule(ModBlocks.GHOSTLY_DIRT.get());
    private static final SurfaceRules.RuleSource MOD_GRASS_BLOCK = makeStateRule(ModBlocks.GHOSTLY_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource MOD_STONE_BLOCK = makeStateRule(ModBlocks.GHOSTLY_STONE.get());
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource OOZING_FLOWER = makeStateRule(ModBlocks.OOZING_FLOWER.get());

    private static final SurfaceRules.RuleSource MOD_BLOODY_GRASS_BLOCK = makeStateRule(ModBlocks.BLOODY_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource BLOOD_BONE_BUSH = makeStateRule(ModBlocks.BLOOD_BONE_FRUIT_BUSH.get());
    private static final SurfaceRules.RuleSource SKULL_N_BONES_BLOCK = makeStateRule(ModBlocks.SKULL_N_BONES.get());

    private static final SurfaceRules.RuleSource MOD_HEAVENLY_GRASS_BLOCK = makeStateRule(ModBlocks.AFTERLIFE_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource MOD_HEAVENLY_DIRT = makeStateRule(ModBlocks.AFTERLIFE_DIRT.get());

    private static final SurfaceRules.RuleSource MOD_GILDED_GRASS_BLOCK = makeStateRule(ModBlocks.GILDED_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource MOD_GILDED_DIRT = makeStateRule(ModBlocks.GILDED_DIRT.get());


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterlevel = SurfaceRules.waterBlockCheck(-1, 0);

        //Ghostly Biome
        SurfaceRules.RuleSource myRules = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.GHOSTLY_BIOME), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MOD_GRASS_BLOCK)

        );
        SurfaceRules.RuleSource flowerRule = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.GHOSTLY_BIOME),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, OOZING_FLOWER)
        );
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterlevel, SurfaceRules.sequence(
                myRules,
                MOD_GRASS_BLOCK,
                flowerRule
        )),MOD_DIRT);

        SurfaceRules.RuleSource modGhostlyBiomeRules = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.GHOSTLY_BIOME),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, MOD_DIRT),
                                MOD_STONE_BLOCK
                        )
                )
        );



        //Bloody Biome
        SurfaceRules.RuleSource bloodyRules = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.BLOOD_GARDEN_BIOME), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MOD_BLOODY_GRASS_BLOCK)

        );

        SurfaceRules.RuleSource bushRule = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.BLOOD_GARDEN_BIOME), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BLOOD_BONE_BUSH)
        );

        //WHY IS IT SPAWNING ON WATER/POISONED WATER & AIR
        SurfaceRules.RuleSource skullNBonesRule = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.BLOOD_GARDEN_BIOME),
                SurfaceRules.ifTrue(isAtOrAboveWaterlevel, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SKULL_N_BONES_BLOCK))
        );

        SurfaceRules.RuleSource bloodyGrassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterlevel, SurfaceRules.sequence(
                bloodyRules,
                MOD_BLOODY_GRASS_BLOCK,
                bushRule,
                skullNBonesRule
        )),MOD_DIRT);

        SurfaceRules.RuleSource modBloodyBiomeRules = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BLOOD_GARDEN_BIOME),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, bloodyGrassSurface),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, MOD_DIRT),
                                MOD_STONE_BLOCK
                        )
                )
        );

        //Heavenly biome
        SurfaceRules.RuleSource heavenlyRules = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.HEAVENLY_PLAINS_BIOME), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MOD_HEAVENLY_GRASS_BLOCK)

        );
        SurfaceRules.RuleSource heavenlyGrassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterlevel, SurfaceRules.sequence(
                heavenlyRules,
                MOD_HEAVENLY_GRASS_BLOCK
        )),MOD_HEAVENLY_DIRT);

        SurfaceRules.RuleSource modHeavenlyBiomeRules = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.HEAVENLY_PLAINS_BIOME),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, heavenlyGrassSurface),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, MOD_HEAVENLY_DIRT),
                                MOD_STONE_BLOCK
                        )
                )
        );

        //Gilded Forest biome
        SurfaceRules.RuleSource gildedForestRules = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.GILDED_FOREST_BIOME), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MOD_GILDED_GRASS_BLOCK)

        );
        SurfaceRules.RuleSource gildedForestGrassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterlevel, SurfaceRules.sequence(
                gildedForestRules,
                MOD_GILDED_GRASS_BLOCK
        )),MOD_GILDED_DIRT);

        SurfaceRules.RuleSource modGildedForestBiomeRules = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.GILDED_FOREST_BIOME),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, gildedForestGrassSurface),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, MOD_GILDED_DIRT),
                                MOD_STONE_BLOCK
                        )
                )
        );




        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(-64)), BEDROCK));
        builder.add(modGhostlyBiomeRules);
        builder.add(modBloodyBiomeRules);
        builder.add(modHeavenlyBiomeRules);
        builder.add(modGildedForestBiomeRules);

        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

}