package com.jacobpmods.neomod.worldgen.biome.surface;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
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
        SurfaceRules.ConditionSource customYCheck = SurfaceRules.abovePreliminarySurface();

        // Grass Block Placement (Above Y 54)
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterlevel,
                SurfaceRules.sequence(
                                SurfaceRules.ifTrue(customYCheck, MOD_GRASS_BLOCK) // Apply grass block if above Y 54
                )
        ));

        // Dirt Below Grass (3 blocks below grass level, but only above Y 45)
        SurfaceRules.RuleSource dirtSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.GHOSTLY_BIOME),
                SurfaceRules.ifTrue(
                        SurfaceRules.stoneDepthCheck(0, true, 3, CaveSurface.FLOOR), // Check if we are 3 blocks below the grass block
                        SurfaceRules.ifTrue(
                                SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(45), 20), // Only above Y 45
                                MOD_DIRT
                        )));

        SurfaceRules.RuleSource flowerSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.GHOSTLY_BIOME),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, OOZING_FLOWER)
        );


        SurfaceRules.RuleSource modGhostlyBiomeRules = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.GHOSTLY_BIOME),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface),
                                dirtSurface,
                                MOD_STONE_BLOCK, // Stone for the cave
                                flowerSurface // Flower needed to be here or it spawned in cave
                        )));



        //Bloody Biome
        SurfaceRules.RuleSource bushRule = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.BLOOD_GARDEN_BIOME), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BLOOD_BONE_BUSH)
        );

        //WHY IS IT SPAWNING ON WATER/POISONED WATER & AIR
        SurfaceRules.RuleSource skullNBonesRule = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.BLOOD_GARDEN_BIOME),
                SurfaceRules.ifTrue(isAtOrAboveWaterlevel, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SKULL_N_BONES_BLOCK))
        );

        SurfaceRules.RuleSource bloodyGrassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterlevel,
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(customYCheck,MOD_BLOODY_GRASS_BLOCK)
                )
        ));
        SurfaceRules.RuleSource bloodyDirtSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.BLOOD_GARDEN_BIOME),
                SurfaceRules.ifTrue(
                        SurfaceRules.stoneDepthCheck(0, true, 3, CaveSurface.FLOOR), // Check if we are 3 blocks below the grass block
                        SurfaceRules.ifTrue(
                                SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(45), 20), // Only above Y 45
                                MOD_DIRT
                        )));
        SurfaceRules.RuleSource modBloodyBiomeRules = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BLOOD_GARDEN_BIOME),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, bloodyGrassSurface),
                                bloodyDirtSurface,
                                MOD_STONE_BLOCK,
                                bushRule,
                                skullNBonesRule
                        )
                )
        );

        //Heavenly biome
        SurfaceRules.RuleSource heavenlyGrassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterlevel,
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(customYCheck,MOD_HEAVENLY_GRASS_BLOCK)
                )
        ));
        SurfaceRules.RuleSource heavenlyDirtSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.HEAVENLY_PLAINS_BIOME),
                SurfaceRules.ifTrue(
                        SurfaceRules.stoneDepthCheck(0, true, 3, CaveSurface.FLOOR), // Check if we are 3 blocks below the grass block
                        SurfaceRules.ifTrue(
                                SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(45), 20), // Only above Y 45
                                MOD_HEAVENLY_DIRT
                        )));
        SurfaceRules.RuleSource modHeavenlyBiomeRules = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.HEAVENLY_PLAINS_BIOME),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, heavenlyGrassSurface),
                                heavenlyDirtSurface,
                                MOD_STONE_BLOCK
                        )
                )
        );


        //Gilded Forest biome
        SurfaceRules.RuleSource gildedForestGrassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterlevel,
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(customYCheck,MOD_GILDED_GRASS_BLOCK)
                )
        ));
        SurfaceRules.RuleSource gildedDirtSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.GILDED_FOREST_BIOME),
                SurfaceRules.ifTrue(
                        SurfaceRules.stoneDepthCheck(0, true, 3, CaveSurface.FLOOR), // Check if we are 3 blocks below the grass block
                        SurfaceRules.ifTrue(
                                SurfaceRules.yBlockCheck(VerticalAnchor.aboveBottom(45), 20), // Only above Y 45
                                MOD_GILDED_DIRT
                        )));
        SurfaceRules.RuleSource modGildedForestBiomeRules = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.GILDED_FOREST_BIOME),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, gildedForestGrassSurface),
                                gildedDirtSurface,
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