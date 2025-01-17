package com.jacobpmods.neomod.effect;

import com.jacobpmods.neomod.block.fluids.PoisonedWaterBlock;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SwimmersVelocityEffect extends MobEffect {
    public SwimmersVelocityEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // Check if the entity is inside a PoisonedWaterBlock
        if (entity.isInWater() || entity.level().getBlockState(entity.blockPosition()).getBlock() instanceof PoisonedWaterBlock) {
            // Remove Dolphin's Grace if present
            if (entity.hasEffect(net.minecraft.world.effect.MobEffects.DOLPHINS_GRACE)) {
                entity.removeEffect(net.minecraft.world.effect.MobEffects.DOLPHINS_GRACE);
            }

            // Apply Water Velocity effect
            double multiplier = 1.0 + (0.05 * (amplifier + 1)); // Adjusted speed boost
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(multiplier, 1.0, multiplier));
        }

        return true; // Continue applying the effect on the next tick
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }


}
