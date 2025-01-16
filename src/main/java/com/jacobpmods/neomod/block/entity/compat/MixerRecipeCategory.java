package com.jacobpmods.neomod.block.entity.compat;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.recipe.EnhancerRecipe;
import com.jacobpmods.neomod.recipe.MixerRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MixerRecipeCategory implements IRecipeCategory<MixerRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "mixer");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "textures/gui/mixer/mixer_gui.png");


    public static final RecipeType<MixerRecipe> MIXER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, MixerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public MixerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.MIXER.get()));
    }

    @Override
    public RecipeType<MixerRecipe> getRecipeType() {
        return MIXER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Mixer");
    }


    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MixerRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 9).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 62, 37).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 98, 37).addIngredients(recipe.getIngredients().get(2));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 127, 61).addItemStack(recipe.getResultItem(null));
    }
}
