package com.jacobpmods.neomod.block.custom.portal.setup;

import com.google.common.collect.ImmutableSet;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ModPoiTypes  {

    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, FirstNeoMod.MOD_ID);

    public static final DeferredHolder<PoiType, PoiType> GHOSTLY_PORTAL = POI.register("ghostly_portal", () ->
            new PoiType(ImmutableSet.copyOf(ModBlocks.GHOSTLY_PORTAL_BLOCK.get().getStateDefinition().getPossibleStates()), 0, 1));


    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }
}
