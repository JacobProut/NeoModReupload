package com.jacobpmods.neomod.item;

import com.jacobpmods.neomod.util.ModTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

import static net.minecraft.world.item.Items.STICK;

public class ModToolTiers {
    public static final Tier NEXON = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_NEXON_TOOL,3000, 11, 5f, 15,
            () -> Ingredient.of(ModItems.HEATED_NEXON));

    public static final Tier SCYTHE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_SCYTHE,3000, 11, 5f, 15,
            () -> Ingredient.of(STICK));

    public static final Tier BONE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_BONE_TOOL,1200, 11, 5f, 15,
            () -> Ingredient.of(ModItems.UNDEAD_BONE));




    }



