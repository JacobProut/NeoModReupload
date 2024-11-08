package com.jacobpmods.neomod.block.terrainblocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;


public class GhostlyDirtBlock extends Block {
    public GhostlyDirtBlock() {
        super(Properties.ofFullCopy(Blocks.DIRT)
                .strength(0.5f)  // Set block hardness
                .sound(SoundType.GRAVEL)  // Set the sound type
                .isSuffocating((state, world, pos) -> false)  // Mimics dirt behavior
                .isViewBlocking((state, world, pos) -> true) // Mimics dirt behavior
        );
    }

}
