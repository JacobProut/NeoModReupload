package com.jacobpmods.neomod.item.custom.enchantment.effects;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import java.util.*;

public class MagmaWalkerEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<MagmaWalkerEnchantmentEffect> CODEC = MapCodec.unit(MagmaWalkerEnchantmentEffect::new);

    private static final Map<ServerLevel, Map<BlockPos, Long>> trackedBlocks = new HashMap<>();
    private static final Map<Player, Boolean> playerOnLavaMap = new HashMap<>(); // Tracks if player was on lava last tick
    private static final int DURATION_TICKS = 100; // 5 seconds (20 ticks per second)
    private static final int LAVA_DEPTH = 1; // Lava is 0.5 blocks below the player

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (!(entity instanceof Player player)) return;

        // Get player position and movement direction
        Vec3 playerPosition = player.position();
        Vec3 movementVector = player.getDeltaMovement().scale(2); // Player's movement direction

        // Calculate the block position in front of the player
        BlockPos lavaPos = new BlockPos(
                (int) Math.floor(playerPosition.x + movementVector.x), // Add movement direction to X
                (int) Math.floor(playerPosition.y - LAVA_DEPTH), // Lava is 0.5 blocks below
                (int) Math.floor(playerPosition.z + movementVector.z) // Add movement direction to Z
        );

        // Check if the block in front of the player is lava
        boolean isOnLava = level.getBlockState(lavaPos).is(Blocks.LAVA);

        // Get previous state
        boolean wasOnLava = playerOnLavaMap.getOrDefault(player, false);

        // If the block in front of the player is lava, replace it with Magma Blokc
        if (isOnLava && !wasOnLava) {
            playMagmaEffects(level, lavaPos);
            replaceLavaWithMagmaBlock(level, lavaPos, DURATION_TICKS);
        }

        // Update player state
        playerOnLavaMap.put(player, isOnLava);

        // Revert expired blocks
        revertExpiredBlocks(level);
    }

    private void replaceLavaWithMagmaBlock(ServerLevel level, BlockPos center, int durationTicks) {
        int radius = 3; // Fixed radius for single level block spawning
        long expirationTime = level.getGameTime() + durationTicks;

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z > radius * radius) continue;

                BlockPos pos = center.offset(x, 0, z);
                if (level.getBlockState(pos).is(Blocks.LAVA) && level.getBlockState(pos.above()).isAir()) {
                    level.setBlock(pos, Blocks.MAGMA_BLOCK.defaultBlockState(), 3);
                    trackBlock(level, pos, expirationTime);
                }
            }
        }
    }

    private void trackBlock(ServerLevel level, BlockPos pos, long expirationTime) {
        trackedBlocks.computeIfAbsent(level, k -> new HashMap<>())
                .put(pos.immutable(), expirationTime);
    }

    private void revertExpiredBlocks(ServerLevel level) {
        Map<BlockPos, Long> levelBlocks = trackedBlocks.getOrDefault(level, new HashMap<>());
        long currentTime = level.getGameTime();

        levelBlocks.entrySet().removeIf(entry -> {
            if (currentTime >= entry.getValue()) {
                BlockPos pos = entry.getKey();
                if (level.getBlockState(pos).is(Blocks.MAGMA_BLOCK)) {
                    level.setBlock(pos, Blocks.LAVA.defaultBlockState(), 3);
                    playRevertEffects(level, pos);
                }
                return true;
            }
            return false;
        });
    }

    private void playMagmaEffects(ServerLevel level, BlockPos pos) {
        // Only play effects if the block is still lava (just stepped on)
        if (level.getBlockState(pos).is(Blocks.LAVA)) {
            // Sound effects
            level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.PLAYERS, 0.7f, 0.8f);

            // Particles
            level.sendParticles(ParticleTypes.LAVA,
                    pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                    20, 0.5, 0.5, 0.5, 0.2);
        }
    }

    private void playRevertEffects(ServerLevel level, BlockPos pos) {
        // Only play revert effects if the block is Magma Block (about to revert)
        if (level.getBlockState(pos).is(Blocks.MAGMA_BLOCK)) {
            level.playSound(null, pos, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.5f, 0.9f);
            level.sendParticles(ParticleTypes.SMOKE,
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    5, 0.2, 0.2, 0.2, 0.01);
        }
    }


    public static boolean isTemporaryMagmaBlock(Level level, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            Map<BlockPos, Long> levelBlocks = trackedBlocks.getOrDefault(serverLevel, new HashMap<>());
            return levelBlocks.containsKey(pos.immutable());
        }
        return false;
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}