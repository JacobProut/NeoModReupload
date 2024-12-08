package com.jacobpmods.neomod.block.terrainblocks;

import com.jacobpmods.neomod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public class GhostlyBlock extends GrassBlock {
    public GhostlyBlock() {
        super(Properties.of()
                .strength(0.6f)  // You can adjust this value
                .sound(SoundType.GRASS)
                .randomTicks()   // Required for grass-like spreading behavior
        );
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canBeGrass(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            level.setBlockAndUpdate(pos, ModBlocks.GHOSTLY_GRASS_BLOCK.get().defaultBlockState());
        } else {
            if (!level.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
                BlockState defaultState = this.defaultBlockState();
                for (int i = 0; i < 4; ++i) {
                    BlockPos offsetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (level.getBlockState(offsetPos).is(ModBlocks.GHOSTLY_DIRT.get()) && canPropagate(defaultState, level, offsetPos)) {
                        level.setBlockAndUpdate(offsetPos, defaultState);
                    }
                }
            }
        }
    }

    private static boolean canBeGrass(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState blockState = level.getBlockState(abovePos);
        if (blockState.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(level, state, pos, blockState, abovePos, Direction.UP, blockState.getLightBlock(level, abovePos));
            return i < level.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        return canBeGrass(state, level, pos) && !level.getFluidState(abovePos).is(FluidTags.WATER);
    }

    /*@Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!world.isAreaLoaded(pos, 3)) return; // Only perform actions if area is loaded

        // Check light level for spreading
        if (world.getMaxLocalRawBrightness(pos.above()) >= 9) {
            BlockState blockAbove = world.getBlockState(pos.above());
            BlockPos belowPos = pos.below();

            // If there's air or a snow block above, spread to adjacent blocks
            if (blockAbove.isAir() || blockAbove.is(Blocks.SNOW)) {
                for (int i = 0; i < 4; i++) {
                    BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    BlockState targetBlockState = world.getBlockState(targetPos);

                    // Spread only if it's Ghostly Dirt (not regular dirt)
                    if (targetBlockState.is(ModBlocks.GHOSTLY_DIRT.get())) {
                        world.setBlockAndUpdate(targetPos, ModBlocks.GHOSTLY_GRASS_BLOCK.get().defaultBlockState());
                    }
                }
            }
        }
    }*/
}
