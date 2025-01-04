package com.jacobpmods.neomod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ModSaplingBlock extends SaplingBlock {
    private Block block;
    private Block block2;
    private Block block3;
    private Block block4;
    private Block block5;
    private Block block6;
    private Block block7;


    public ModSaplingBlock(TreeGrower treeGrower, Properties properties, Block block, Block block2, Block block3, Block block4, Block block5, Block block6, Block block7) {
        super(treeGrower, properties);
        this.block = block;
        this.block2 = block2;
        this.block3 = block3;
        this.block4 = block4;
        this.block5 = block5;
        this.block6 = block6;
        this.block7 = block7;
    }



    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter plevel, BlockPos blockPos) {
        return blockState.is(block) ||
                (block2 != null && blockState.is(block2)) ||
                (block3 != null && blockState.is(block3)) ||
                (block4 != null && blockState.is(block4)) ||
                (block5 != null && blockState.is(block5)) ||
                (block6 != null && blockState.is(block6)) ||
                (block7 != null && blockState.is(block7));
    }

}
