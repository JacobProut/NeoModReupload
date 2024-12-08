package com.jacobpmods.neomod.block.custom.blockentities;

import com.jacobpmods.neomod.block.entity.custom.PedestalBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PedestalBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(2, 0, 2,14, 13,14);
    public static final MapCodec<PedestalBlock> CODEC = simpleCodec(PedestalBlock::new);

    public PedestalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PedestalBlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()) {
            if(level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity) {
                pedestalBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity) {
            if (player.isCrouching() && !level.isClientSide()) {
                ((ServerPlayer) player).openMenu(new SimpleMenuProvider(pedestalBlockEntity, Component.literal("pedestal")), pos);
                return ItemInteractionResult.SUCCESS;
            }

            // Placing an item on the pedestal
            if (pedestalBlockEntity.inventory.getStackInSlot(0).isEmpty() && !stack.isEmpty()) {
                // Check if the item is a golden apple
                if (stack.is(Items.GOLDEN_APPLE)) {
                    // Logic to spawn ghostly portal
                    if (openGhostlyPortal(level, pos)) {
                        stack.shrink(1); // Consume the golden apple
                        level.playSound(null, pos, SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 1f, 1f);
                        return ItemInteractionResult.SUCCESS;
                    } else {
                        player.displayClientMessage(Component.literal("No valid portal frame nearby!"), true);
                    }
                } else {
                    pedestalBlockEntity.inventory.insertItem(0, stack.copy(), false);
                    stack.shrink(1);
                    level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
                }
            } else if (stack.isEmpty()) {
                // Removing the item from the pedestal
                ItemStack stackOnPedestal = pedestalBlockEntity.inventory.extractItem(0, 1, false);
                player.setItemInHand(InteractionHand.MAIN_HAND, stackOnPedestal);
                pedestalBlockEntity.clearContents();
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            }
        }
        return ItemInteractionResult.SUCCESS;
    }

    private boolean openGhostlyPortal(Level level, BlockPos pedestalPos) {
        int searchRadius = 15; // Define the search radius around the pedestal
        boolean portalOpened = false; // Track if any portal blocks were created

        // Iterate through all blocks in the specified area
        for (BlockPos mutablePos : BlockPos.betweenClosed(
                pedestalPos.offset(-searchRadius, -searchRadius, -searchRadius),
                pedestalPos.offset(searchRadius, searchRadius, searchRadius))) {

            BlockState blockState = level.getBlockState(mutablePos);

            // Check if the block is Iron Bars
            if (blockState.is(Blocks.IRON_BARS) || blockState.is(Blocks.BARRIER)) {
                // Replace Iron Bars with Air
                level.setBlock(mutablePos, Blocks.AIR.defaultBlockState(), 3);


                // Add particles for effect
                for (int i = 0; i < 5; i++) {
                    double x = mutablePos.getX() + level.random.nextDouble();
                    double y = mutablePos.getY() + level.random.nextDouble();
                    double z = mutablePos.getZ() + level.random.nextDouble();
                    level.addParticle(ParticleTypes.PORTAL, x, y, z, 0, 0, 0);
                    level.addParticle(ParticleTypes.EXPLOSION, x,y,z,0,0,0);
                }

                portalOpened = true; // Mark portal block creation
            }
        }

        // Play sound effect only if any portal blocks were created
        if (portalOpened) {
            level.playSound(null, pedestalPos, SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 1.0f, 1.0f);
        }

        return portalOpened;
    }

    //Original
    /*@Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if(level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity) {
            if(player.isCrouching() && !level.isClientSide()) {
                ((ServerPlayer) player).openMenu(new SimpleMenuProvider(pedestalBlockEntity, Component.literal("pedestal")), pos);
                return ItemInteractionResult.SUCCESS;
            }

            if(pedestalBlockEntity.inventory.getStackInSlot(0).isEmpty() && !stack.isEmpty()) {
                pedestalBlockEntity.inventory.insertItem(0, stack.copy(), false);
                stack.shrink(1);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            } else if (stack.isEmpty()) {
                ItemStack stackOnPedestal = pedestalBlockEntity.inventory.extractItem(0, 1, false);
                player.setItemInHand(InteractionHand.MAIN_HAND, stackOnPedestal);
                pedestalBlockEntity.clearContents();
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            }
        }
        return ItemInteractionResult.SUCCESS;
    }
    */

}
