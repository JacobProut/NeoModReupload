package com.jacobpmods.neomod.effect;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class LavaSpeedEffect extends MobEffect {
    public LavaSpeedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {

        Minecraft minecraft = Minecraft.getInstance(); //Gets instance of level
        Player player = minecraft.player; //Gets instance of Player
        if (player == null) {
            return false; // No Player instance = abort execution
        }

        if (entity.isInLava()) { // Checks for lava
            if (!player.isCreative()) { // Make sure player is not in creative (If player was in creative, it would give them extremely fast speed)
                if (entity.isSprinting() || entity.isSwimming()) { // Checks for if the player is sprinting first before apply walk speed.
                    double multiplier = 1.0 + (0.75 * (amplifier + 1)); // If sprinting apply 1.75% speed
                    entity.setDeltaMovement(entity.getDeltaMovement().multiply(multiplier, 1.0, multiplier));
                } else if (entity.walkDistO > 0) { //Checks if player is walking
                    double multiplier = 1.0 + (0.50 * (amplifier + 1)); // If walking apply 1.50% speed
                    entity.setDeltaMovement(entity.getDeltaMovement().multiply(multiplier, 1.0, multiplier));
                }
            }
        }

        return true; // Continue applying the effect on the next tick
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

}
