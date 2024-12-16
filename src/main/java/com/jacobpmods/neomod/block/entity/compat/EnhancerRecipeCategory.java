package com.jacobpmods.neomod.block.entity.compat;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.recipe.EnhancerRecipe;
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

public class EnhancerRecipeCategory implements IRecipeCategory<EnhancerRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "enhancer");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "textures/gui/enhancer/enhancer_gui.png");

    public static final RecipeType<EnhancerRecipe> ENHANCER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, EnhancerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public EnhancerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ENHANCER.get()));
    }

    @Override
    public RecipeType<EnhancerRecipe> getRecipeType() {
        return ENHANCER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Enhancer");
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
    public void setRecipe(IRecipeLayoutBuilder builder, EnhancerRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 21, 37).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 37).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 113, 37).addItemStack(recipe.getResultItem(null));
    }
}
