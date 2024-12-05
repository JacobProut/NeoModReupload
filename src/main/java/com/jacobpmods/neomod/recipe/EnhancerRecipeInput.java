package com.jacobpmods.neomod.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record EnhancerRecipeInput(ItemStack input1, ItemStack input2) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        return switch(index) {
            case 0 -> input1;
            case 1 -> input2;
            default -> throw new IndexOutOfBoundsException("Invalid index" + index);

        };
    }

    @Override
    public int size() {
        return 2;
    }
}
