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

public record MixerRecipe(Ingredient inputItem1, Ingredient inputItem2, Ingredient inputItem3, ItemStack output) implements Recipe<MixerRecipeInput> {
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem1);
        list.add(inputItem2);
        list.add(inputItem3);
        return list;
    }

    @Override
    public boolean matches(MixerRecipeInput input, Level level) {
        if(level.isClientSide()) {
            return false;
        }

        return inputItem1.test(input.getItem(0)) && inputItem2.test(input.getItem(1)) && inputItem3.test(input.getItem(2));
    }

    @Override
    public ItemStack assemble(MixerRecipeInput input, HolderLookup.Provider provider) {
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
        return ModRecipes.MIXER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.MIXER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<MixerRecipe> {

        public static final MapCodec<MixerRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient1").forGetter(MixerRecipe::inputItem1),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient2").forGetter(MixerRecipe::inputItem2),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient3").forGetter(MixerRecipe::inputItem3),
                ItemStack.CODEC.fieldOf("result").forGetter(MixerRecipe::output)
        ).apply(inst, MixerRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MixerRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, MixerRecipe::inputItem1,
                        Ingredient.CONTENTS_STREAM_CODEC, MixerRecipe::inputItem2,
                        Ingredient.CONTENTS_STREAM_CODEC, MixerRecipe::inputItem3,
                        ItemStack.STREAM_CODEC, MixerRecipe::output,
                        MixerRecipe::new);
        @Override
        public MapCodec<MixerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MixerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
