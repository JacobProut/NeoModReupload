package com.jacobpmods.neomod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class NoLavaFogEffect extends MobEffect {
    public NoLavaFogEffect(MobEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

}