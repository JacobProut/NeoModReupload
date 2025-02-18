package com.jacobpmods.neomod.item.custom.enchantment.effects.axes;

import com.jacobpmods.neomod.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.Set;

public record TimberFellerEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<TimberFellerEnchantmentEffect> CODEC = MapCodec.unit(TimberFellerEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (enchantmentLevel == 1) {

            BlockPos blockPos = BlockPos.containing(origin);
            BlockState blockState = level.getBlockState(blockPos);

            // Check if the block being broken is a log
            if (isLog(blockState)) {
                // Trigger the tree breaking logic
                breakTree(level, blockPos, new HashSet<>());
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }

    private boolean isLog(BlockState state) {
        // Check if the block is a log
        return state.is(Blocks.OAK_LOG) || state.is(Blocks.SPRUCE_LOG) || state.is(Blocks.BIRCH_LOG) ||
                state.is(Blocks.JUNGLE_LOG) || state.is(Blocks.ACACIA_LOG) || state.is(Blocks.DARK_OAK_LOG) ||
                state.is(ModBlocks.LOG_GHOSTLY) || state.is(ModBlocks.LOG_BLOODY);
    }

    private boolean isLeaf(BlockState state) {
        // Check if the block is a leaf
        return state.is(Blocks.OAK_LEAVES) || state.is(Blocks.SPRUCE_LEAVES) || state.is(Blocks.BIRCH_LEAVES) ||
                state.is(Blocks.JUNGLE_LEAVES) || state.is(Blocks.ACACIA_LEAVES) || state.is(Blocks.DARK_OAK_LEAVES) ||
                state.is(ModBlocks.GHOSTLY_LEAVES) || state.is(ModBlocks.BLOODY_LEAVES);
    }

    private void breakTree(ServerLevel level, BlockPos startPos, Set<BlockPos> visited) {
        // Avoid revisiting blocks
        if (visited.contains(startPos)) {
            return;
        }

        visited.add(startPos);

        BlockState state = level.getBlockState(startPos);
        if (isLog(state)) {
            // Break the log and drop its items
            Block.dropResources(state, level, startPos);
            level.setBlock(startPos, Blocks.AIR.defaultBlockState(), 3);

            // Check all neighboring positions
            for (BlockPos offset : BlockPos.betweenClosed(
                    startPos.offset(-1, -1, -1), startPos.offset(1, 1, 1))) {
                if (!visited.contains(offset) && (isLog(level.getBlockState(offset)) || isLeaf(level.getBlockState(offset)))) {
                    breakTree(level, offset.immutable(), visited);
                }
            }
        }
    }
}
