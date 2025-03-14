package com.jacobpmods.neomod.block.custom.blocks;

import com.jacobpmods.neomod.particles.ModParticlesTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GildedLeavesBlock extends LeavesBlock {
    public GildedLeavesBlock(Properties properties) {
        super(properties);
    }

    //MAKE A BLOOD PARTICLE
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

        if (random.nextInt(2) == 0) { // Frequency of particle spawning | orig 10 [Speed of drips]
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);

            // Only spawn particles if the block below is not a full block
            if (!isFaceFull(belowState.getCollisionShape(level, belowPos), Direction.UP)) {
                // Generate random offsets
                double x = belowPos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.6;
                double y = belowPos.getY() + 1.0; // Slightly above the block below
                double z = belowPos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.6;

                // Add your custom particle
               // ParticleUtils.spawnParticleBelow(level, pos, random, ModParticlesTypes.DRIPPING_BLOOD.get());
                level.addParticle(ModParticlesTypes.GILDED_OOZE.get(), x, y, z, 0, 1.0, 0); // Adjust velocity for a slow fall
            }
        }
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
    }
}
