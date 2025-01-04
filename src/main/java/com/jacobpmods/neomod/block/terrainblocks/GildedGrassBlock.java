package com.jacobpmods.neomod.block.terrainblocks;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.particles.ModParticlesTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GildedGrassBlock extends BloodyGrassBlock {
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double d0 = (double) pos.getX() + 0.5;
        double d1 = (double) pos.getY() + 1.2;
        double d2 = (double) pos.getZ() + 0.5;
        if (random.nextInt(7) == 0) {
            level.addParticle(ModParticlesTypes.SPARK.get(), d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canBeGrass(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            level.setBlockAndUpdate(pos, ModBlocks.GILDED_GRASS_BLOCK.get().defaultBlockState());
        } else {
            if (!level.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
                BlockState defaultState = this.defaultBlockState();
                for (int i = 0; i < 4; ++i) {
                    BlockPos offsetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (level.getBlockState(offsetPos).is(ModBlocks.GILDED_DIRT.get()) && canPropagate(defaultState, level, offsetPos)) {
                        level.setBlockAndUpdate(offsetPos, defaultState);
                    }
                }
            }
        }
    }
}
