package com.jacobpmods.neomod.item.custom.enchantment.effects;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.item.ModItems;
import com.jacobpmods.neomod.item.custom.enchantment.ModEnchantmentEffects;
import com.jacobpmods.neomod.item.custom.enchantment.ModEnchantments;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class VeinMinerEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<VeinMinerEnchantmentEffect> CODEC = MapCodec.unit(VeinMinerEnchantmentEffect::new);

    public static final Set<Block> block_list = Set.of(
            Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE,
            Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE,
            Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE,
            Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE,
            Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
            Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE,
            Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE
    );

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse itemInUse, Entity entity, Vec3 origin) {
        if (!(entity instanceof Player player)) return;

        BlockPos blockPos = BlockPos.containing(origin);
        BlockState blockState = level.getBlockState(blockPos);

        if (!block_list.contains(blockState.getBlock())) return; //Ensures it's a block on the list above

        ItemStack tool = itemInUse.itemStack();
        damageTool(player, tool);

        boolean hasMagmaMine = tool.getEnchantmentLevel(level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolderOrThrow(ModEnchantments.MAGMA_MINE)) > 0;

        mineConnectedBlocks(level, blockPos, blockState.getBlock(), player, tool, hasMagmaMine);
    }

    private void damageTool(Player player, ItemStack tool) {
        if (!tool.isEmpty() && tool.isDamageableItem()) {
            tool.hurtAndBreak(1, player, null);
        }
    }

    public static void mineConnectedBlocks(ServerLevel level, BlockPos startPos, Block blockType, Player player, ItemStack tool, boolean hasMagmaMine) {
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();

        queue.add(startPos);
        visited.add(startPos);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            BlockState currentState = level.getBlockState(current);

            if (currentState.is(blockType)) {
                level.setBlockAndUpdate(current, Blocks.AIR.defaultBlockState()); // Remove block

                ItemStack drop = getDropForBlock(blockType, hasMagmaMine);
                Block.popResource(level, current, drop); // Drop the correct item (smelted or raw)

                for (Direction direction : Direction.values()) {
                    BlockPos neighbor = current.relative(direction);
                    if (!visited.contains(neighbor) && level.getBlockState(neighbor).is(blockType)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
    }

    private static ItemStack getDropForBlock(Block block, boolean hasMagmaMine) {
        if (hasMagmaMine) {
            if (block == Blocks.IRON_ORE || block == Blocks.DEEPSLATE_IRON_ORE) return new ItemStack(Items.IRON_INGOT);
            if (block == Blocks.GOLD_ORE || block == Blocks.DEEPSLATE_GOLD_ORE) return new ItemStack(Items.GOLD_INGOT);
            if (block == ModBlocks.NEXON_ORE_BLOCK.get()) return new ItemStack(ModItems.HEATED_NEXON.get());
        }
        return new ItemStack(block.asItem()); // Default drop (raw ore)
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
