package com.jacobpmods.neomod.worldgen.tree.treeDecorators;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.block.custom.blocks.BloodyVine;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class BloodVineDecorator extends TreeDecorator {
    public static final MapCodec<BloodVineDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(BloodVineDecorator::new, BloodVineDecorator::getProbability);
    private final float probability;


    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecorators.BLOOD_VINE_DECORATOR.get(); // Make sure this is correctly registered
    }

    public BloodVineDecorator(float probability) {
        this.probability = probability;
    }

    public float getProbability() {
        return probability;
    }

    @Override
    public void place(TreeDecorator.Context p_226039_) {
        RandomSource randomsource = p_226039_.random();
        p_226039_.leaves().forEach(p_226035_ -> {
            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos = p_226035_.west();
                if (p_226039_.isAir(blockpos)) {
                    addHangingVine(blockpos, BloodyVine.EAST, p_226039_);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos1 = p_226035_.east();
                if (p_226039_.isAir(blockpos1)) {
                    addHangingVine(blockpos1, BloodyVine.WEST, p_226039_);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos2 = p_226035_.north();
                if (p_226039_.isAir(blockpos2)) {
                    addHangingVine(blockpos2, BloodyVine.SOUTH, p_226039_);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos3 = p_226035_.south();
                if (p_226039_.isAir(blockpos3)) {
                    addHangingVine(blockpos3, BloodyVine.NORTH, p_226039_);
                }
            }
        });
    }

    //This needs to be changed to work with bloody vines
    private static void addHangingVine(BlockPos p_226041_, BooleanProperty p_226042_, TreeDecorator.Context p_226043_) {
        // Replace placeVine with setting BLOODY_VINE block state
        p_226043_.setBlock(p_226041_, ModBlocks.BLOODY_VINE.get().defaultBlockState().setValue(p_226042_, true));

        int i = 4;

        // Grow the blood vine downward
        for (BlockPos blockpos = p_226041_.below(); p_226043_.isAir(blockpos) && i > 0; i--) {
            p_226043_.setBlock(blockpos, ModBlocks.BLOODY_VINE.get().defaultBlockState().setValue(p_226042_, true));
            blockpos = blockpos.below();
        }
    }


}
