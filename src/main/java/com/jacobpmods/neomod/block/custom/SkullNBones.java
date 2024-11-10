package com.jacobpmods.neomod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import static net.neoforged.neoforge.common.ItemAbilities.SHOVEL_DIG;

public class SkullNBones extends Block {
    public static final VoxelShape SHAPE = box(0, 0, 0, 16, 8, 16);

    public SkullNBones(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            ItemStack heldItem = player.getMainHandItem();
            boolean correctTool = heldItem.canPerformAction(SHOVEL_DIG) && heldItem.is(Items.IRON_SHOVEL);

            if (correctTool) {
                // Drop bones
                Block.popResource((ServerLevel) level, pos, new ItemStack(Items.BONE, 2)); // Adjust quantity as needed
            } else {
                // Spawn a skeleton with a 25% chance of dropping its held item
                Skeleton skeleton = EntityType.SKELETON.create(level);
                if (skeleton != null) {
                    skeleton.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

                    // Determine if the skeleton should spawn with a stick (25% chance)
                    if (RandomSource.create().nextFloat() < 0.25f) {
                        ItemStack stick = new ItemStack(Items.STICK);
                        skeleton.setItemSlot(EquipmentSlot.MAINHAND, stick);
                        // Set a 50% drop chance for the stick if it has one
                        skeleton.setDropChance(EquipmentSlot.MAINHAND, 0.60f);
                    }
                    // Spawn the skeleton into the world
                    level.addFreshEntity(skeleton);

                    // Placeholder for adding particles or animations for spawn effect
                    // spawnSpawnParticles(level, pos);
                }
            }
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos); // Trigger block destroy event
        }
        super.playerWillDestroy(level, pos, state, player); // Call the superclass to handle any additional effects
        return state;
    }

    // Optional: Placeholder method for adding particles or animations during spawn
    private void spawnSpawnParticles(Level level, BlockPos pos) {
        // Example: Add particle effects here
    }
}
