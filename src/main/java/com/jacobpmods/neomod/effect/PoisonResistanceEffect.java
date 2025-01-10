package com.jacobpmods.neomod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import static net.minecraft.world.effect.MobEffects.POISON;

public class PoisonResistanceEffect extends MobEffect {
    public PoisonResistanceEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect(POISON)) {
                livingEntity.removeEffect(POISON);
            }
        }
        return true;
    }


    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }


}
