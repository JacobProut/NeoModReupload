package com.jacobpmods.neomod.worldgen.dimension;

import com.google.common.collect.ImmutableSet;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPoiTypes  {

    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, FirstNeoMod.MOD_ID);

    public static final DeferredHolder<PoiType, PoiType> GHOSTLY_PORTAL = POI.register("ghostly_portal", () ->
            new PoiType(ImmutableSet.copyOf(ModBlocks.GHOSTLY_PORTAL_BLOCK.get().getStateDefinition().getPossibleStates()), 0, 1));


    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }
}
