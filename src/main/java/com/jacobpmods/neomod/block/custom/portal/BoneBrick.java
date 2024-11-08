package com.jacobpmods.neomod.block.custom.portal;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BoneBrick extends Block {
    public static final Logger LOGGER = LoggerFactory.getLogger(FirstNeoMod.class);
    public BoneBrick(Properties properties) {
        super(properties);
    }


    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        LOGGER.info("Player is using item on Block at position: " + pos.toString());

        // Check if the item in hand is flint and steel
    /*    if (stack.getItem() == Items.FLINT_AND_STEEL) {
            Direction.Axis axis = Direction.Axis.X; // Set the appropriate axis

            Optional<GhostlyPortalShape> portalShape = GhostlyPortalShape.findCollisionFreePosition(level, pos, axis);

            if (portalShape.isPresent()) {
                LOGGER.info("Portal shape found, creating portal blocks.");
                portalShape.ifPresent(GhostlyPortalShape::createPortalBlocks);
                LOGGER.info("Portal blocks created successfully.");
            } else {
                LOGGER.warn("No valid portal shape found at position: " + pos.toString());
            }

            // Optionally damage the item in hand
            // stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));

            LOGGER.info("End of useItemOn method");
            return ItemInteractionResult.SUCCESS; // Indicate that the interaction was successful
        }*/

        LOGGER.info("Item used is not flint and steel.");
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION; // Default behavior
    }

}