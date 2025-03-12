package com.jacobpmods.neomod.item.custom.enchantment.effects.hoe;

import com.jacobpmods.neomod.item.custom.enchantment.ModEnchantments;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public record FastHarvestEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<FastHarvestEnchantmentEffect> CODEC = MapCodec.unit(FastHarvestEnchantmentEffect::new);

    public static final Set<Block> crop_list = Set.of(
            Blocks.POTATOES, Blocks.CARROTS, Blocks.BEETROOTS, Blocks.WHEAT
    );

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse itemInUse, Entity entity, Vec3 origin) {
        if (!(entity instanceof Player player)) return;

        BlockPos blockPos = BlockPos.containing(origin);
        BlockState blockstate = level.getBlockState(blockPos);

        if (!(crop_list.contains(blockstate.getBlock()))) return;

        ItemStack tool = itemInUse.itemStack();


        boolean hasFastHarvest = tool.getEnchantmentLevel(level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolderOrThrow(ModEnchantments.FAST_HARVEST)) > 0;

        if (enchantmentLevel == 1) {
            damageTool(player, tool, 1);
            mineConnectedCrops(level, blockPos, blockstate.getBlock(), hasFastHarvest, 1);
        }
        if (enchantmentLevel == 2) {
            damageTool(player, tool, 2);
            mineConnectedCrops(level, blockPos, blockstate.getBlock(), hasFastHarvest, 2);
        }
    }

    private void damageTool(Player player, ItemStack tool, int damageAmount) {
        if (!tool.isEmpty() && tool.isDamageableItem()) {
            if (damageAmount == 1) {
                tool.hurtAndBreak(1, player, null);
            }
            if (damageAmount == 2) {
                tool.hurtAndBreak(2, player, null);
            }

        }
    }

    public static void mineConnectedCrops(ServerLevel level, BlockPos startPos, Block blockType, boolean hasFastHarvest, int enchantmentLevel) {
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();

        // Store positions with their distance from the starting point
        queue.add(startPos);
        visited.add(startPos);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            BlockState currentState = level.getBlockState(current);

            if (currentState.is(blockType)) {
                level.setBlockAndUpdate(current, Blocks.AIR.defaultBlockState()); //Removal of Block

                // Get all drops (can contain multiple items)
                List<ItemStack> drops = getCropDrops(blockType, currentState, hasFastHarvest, level);
                // Drop all items
                for (ItemStack drop : drops) {
                    Block.popResource(level, current, drop);
                }


                // Explore only horizontal neighbors (ignore up and down)
                for (Direction direction : Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)) {
                    BlockPos neighbor = current.relative(direction);

                    // Ensure the Y-Level is the same
                    if(neighbor.getY() != startPos.getY()) continue;

                    // Calculate the Manhatten Distance (Horizontal only)
                    int distance = Math.abs(neighbor.getX() - startPos.getX())
                            + Math.abs(neighbor.getZ() - startPos.getZ());

                    if (enchantmentLevel == 1) {
                        // Check if within distance and unvisited
                        if (distance <= 5 && !visited.contains(neighbor) && level.getBlockState(neighbor).is(blockType)) {
                            queue.add(neighbor);
                            visited.add(neighbor);
                        }
                    }
                    if (enchantmentLevel == 2) {
                        // Check if within distance and unvisited
                        if (distance <= 10 && !visited.contains(neighbor) && level.getBlockState(neighbor).is(blockType)) {
                            queue.add(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
        }
    }

    private static List<ItemStack> getCropDrops(Block block, BlockState state, boolean hasFastHarvest, ServerLevel level) {
        List<ItemStack> drops = new ArrayList<>();

        if (hasFastHarvest) {
            // Handle crops with AGE_7 (Wheat, Carrots, Potatoes)
            if (block == Blocks.POTATOES || block == Blocks.CARROTS || block == Blocks.WHEAT) {
                int age = state.getValue(BlockStateProperties.AGE_7);

                int baseDropCarrotAndPotatoes = switch (age) {
                    case 0, 1 -> 1; // Very early stage: 1 items
                    case 2, 3, 4 -> 2; // Mid-growth: 2 items
                    case 5, 6 -> 3; // Nearly grown: 3 item
                    case 7 -> 3 + level.random.nextInt(2); // Fully grown (3 to 4 items)
                    default -> 1;
                };
                if (block == Blocks.POTATOES) {
                    drops.add(new ItemStack(Items.POTATO, baseDropCarrotAndPotatoes));
                }
                if (block == Blocks.CARROTS) {
                    drops.add(new ItemStack(Items.CARROT, baseDropCarrotAndPotatoes));
                }


                if (block == Blocks.WHEAT) {
                    if (age == 7) {
                        // Fully grown: 1 wheat + 1-3 seeds
                        drops.add(new ItemStack(Items.WHEAT, 1));
                        drops.add(new ItemStack(Items.WHEAT_SEEDS, 1 + level.random.nextInt(3)));
                    } else {
                        drops.add(new ItemStack(Items.WHEAT_SEEDS, 1)); // Not fully grown: Always 1 seed
                    }
                }
            }

            // Handle Beetroots (AGE_3)
            if (block == Blocks.BEETROOTS) {
                int age = state.getValue(BlockStateProperties.AGE_3);
                if (age == 3) {
                    drops.add(new ItemStack(Items.BEETROOT, 1)); // Only fully grown drops beetroot
                    drops.add(new ItemStack(Items.BEETROOT_SEEDS, 1 + level.random.nextInt(3)));
                } else {
                    drops.add(new ItemStack(Items.BEETROOT_SEEDS, 1));
                }
            }
            return drops; //Return early if Fast Harvest logic is handled
        }
        drops.add(new ItemStack(block.asItem())); // Default drop if no Fast Harvest
        return drops;
    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
