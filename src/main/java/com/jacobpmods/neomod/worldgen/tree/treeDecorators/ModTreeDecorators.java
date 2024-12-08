package com.jacobpmods.neomod.worldgen.tree.treeDecorators;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTreeDecorators {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(BuiltInRegistries.TREE_DECORATOR_TYPE, "neomod");

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<BloodVineDecorator>> BLOOD_VINE_DECORATOR =
            TREE_DECORATORS.register("bloody_vine_decorator", () -> new TreeDecoratorType<>(BloodVineDecorator.CODEC));
}