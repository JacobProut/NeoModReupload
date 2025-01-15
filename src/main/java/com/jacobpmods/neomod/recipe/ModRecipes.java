package com.jacobpmods.neomod.recipe;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, FirstNeoMod.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, FirstNeoMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<EnhancerRecipe>> ENHANCER_SERIALIZER =
            SERIALIZERS.register("enhancing", EnhancerRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<EnhancerRecipe>> ENHANCER_TYPE =
            TYPES.register("enhancing", () -> new RecipeType<EnhancerRecipe>() {
                @Override
                public String toString() {
                    return "enhancing";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MixerRecipe>> MIXER_SERIALIZER =
            SERIALIZERS.register("mixing", MixerRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<MixerRecipe>> MIXER_TYPE =
            TYPES.register("mixing", () -> new RecipeType<MixerRecipe>() {
                @Override
                public String toString() {
                    return "mixing";
                }
            });

    public static void registers(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
