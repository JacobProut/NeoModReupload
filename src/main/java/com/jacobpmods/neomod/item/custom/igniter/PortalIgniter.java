package com.jacobpmods.neomod.item.custom.igniter;

import com.jacobpmods.neomod.block.custom.portal.setup.GhostlyPortalShape;
import com.jacobpmods.neomod.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Predicate;

public class PortalIgniter extends Item {

    public PortalIgniter(){
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().dimension() == Level.OVERWORLD || context.getLevel().dimension() == ModDimensions.GHOSTLY_LEVEL_KEY) {
            BlockPos framePos = context.getClickedPos().relative(context.getClickedFace());
            Optional<GhostlyPortalShape> optional = findPortalShape(context.getLevel(), framePos, shape -> shape.isValid() && shape.getPortalBlocks() == 0, Direction.Axis.X);
            if (optional.isPresent()) {
                optional.get().createPortalBlocks();
                return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
            }
        }
        return InteractionResult.FAIL;
    }

    public static Optional<GhostlyPortalShape> findPortalShape(LevelAccessor accessor, BlockPos pos, Predicate<GhostlyPortalShape> shape, Direction.Axis axis) {
        Optional<GhostlyPortalShape> optional = Optional.of(new GhostlyPortalShape(accessor, pos, axis)).filter(shape);
        if (optional.isPresent()) {
            return optional;
        } else {
            Direction.Axis oppositeAxis = axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
            return Optional.of(new GhostlyPortalShape(accessor, pos, oppositeAxis)).filter(shape);
        }
    }
}