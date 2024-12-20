package com.jacobpmods.neomod.block.custom.blocks;

import com.jacobpmods.neomod.particles.ModParticlesTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AfterlifeTorchBlock extends TorchBlock {
    public AfterlifeTorchBlock(Properties properties) {
        super(ParticleTypes.SMOKE, properties);
    }


    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double d0 = (double) pos.getX() + 0.5;
        double d1 = (double) pos.getY() + 0.75;
        double d2 = (double) pos.getZ() + 0.5;
        if (random.nextInt(7) == 0) {
            level.addParticle(ModParticlesTypes.AFTERLIFE_TORCH.get(), d0, d1, d2, 0.0, 0.0, 0.0);
            level.addParticle(ModParticlesTypes.AFTERLIFE_FIRE_FLAME.get(), d0, d1, d2, 0.0, 0.0, 0.0);
            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }
}
