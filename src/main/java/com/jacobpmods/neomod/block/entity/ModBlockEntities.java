package com.jacobpmods.neomod.block.entity;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.block.entity.custom.EnhancerBlockEntity;
import com.jacobpmods.neomod.block.entity.custom.MixerBlockEntity;
import com.jacobpmods.neomod.block.entity.custom.PedestalBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FirstNeoMod.MOD_ID);

    public static final Supplier<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
            BLOCK_ENTITIES.register("pedestal_be", () -> BlockEntityType.Builder.of(
                    PedestalBlockEntity::new, ModBlocks.PEDESTAL.get()).build(null));

    public static final Supplier<BlockEntityType<EnhancerBlockEntity>> ENHANCER_BE =
            BLOCK_ENTITIES.register("enhancer_be", () -> BlockEntityType.Builder.of(
                    EnhancerBlockEntity::new, ModBlocks.ENHANCER.get()).build(null));

    public static final Supplier<BlockEntityType<MixerBlockEntity>> MIXER_BE =
            BLOCK_ENTITIES.register("mixer_be", () -> BlockEntityType.Builder.of(
                    MixerBlockEntity::new, ModBlocks.MIXER.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
