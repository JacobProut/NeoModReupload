package com.jacobpmods.neomod.block.entity.compat;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.recipe.EnhancerRecipe;
import com.jacobpmods.neomod.recipe.ModRecipes;
import com.jacobpmods.neomod.screen.custom.EnhancerScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIMCCoursePlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new EnhancerRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()
        ));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<EnhancerRecipe> enhancerRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.ENHANCER_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(EnhancerRecipeCategory.ENHANCER_RECIPE_RECIPE_TYPE, enhancerRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(EnhancerScreen.class, 70, 30, 25, 20,
                EnhancerRecipeCategory.ENHANCER_RECIPE_RECIPE_TYPE);
    }
}
