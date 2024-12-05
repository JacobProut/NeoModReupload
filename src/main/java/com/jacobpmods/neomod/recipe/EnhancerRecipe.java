package com.jacobpmods.neomod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record EnhancerRecipe(Ingredient inputItem, ItemStack output) implements Recipe<EnhancerRecipeInput> {
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(EnhancerRecipeInput input, Level level) {
        if(level.isClientSide()) {
            return false;
        }
        
        return inputItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(EnhancerRecipeInput input, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider p_336125_) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ENHANCER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ENHANCER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<EnhancerRecipe> {

        public static final MapCodec<EnhancerRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(EnhancerRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(EnhancerRecipe::output)
        ).apply(inst, EnhancerRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, EnhancerRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, EnhancerRecipe::inputItem,
                        ItemStack.STREAM_CODEC, EnhancerRecipe::output,
                        EnhancerRecipe::new);

        @Override
        public MapCodec<EnhancerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, EnhancerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
