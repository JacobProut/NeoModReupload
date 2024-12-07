package com.jacobpmods.neomod.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GhostlyWeb extends Block {
    public GhostlyWeb(Properties properties) {
        super(properties);
    }




    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        // Check if the entity is a LivingEntity (includes players and mobs)
        if (entity instanceof LivingEntity) {
            // Reduce motion to simulate getting stuck like in a cobweb
            double slowdownFactor = 0.25; // Adjust this for the stickiness
            entity.setDeltaMovement(
                    entity.getDeltaMovement().multiply(slowdownFactor, 1.0, slowdownFactor)
            );

            // If desired, simulate slowness without GUI effects by manually modifying the entity's motion
            entity.setDeltaMovement(
                    entity.getDeltaMovement().multiply(0.4, 1.0, 0.4) // Adjust to your liking
            );

            // If the entity is falling, reduce vertical velocity as well
            if (!entity.onGround()) {
                entity.setDeltaMovement(
                        entity.getDeltaMovement().multiply(0.4, slowdownFactor, 0.4)
                );
            }
        }
        super.entityInside(state, level, pos, entity);
    }
}
