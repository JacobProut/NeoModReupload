package com.jacobpmods.neomod.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record MixerRecipeInput(ItemStack input1, ItemStack input2, ItemStack input3) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case 0 -> input1;
            case 1 -> input2;
            case 2 -> input3;
            default -> throw new IndexOutOfBoundsException("Invalid index" + index);

        };
    }

    @Override
    public int size() {
        return 3;
    }

}
