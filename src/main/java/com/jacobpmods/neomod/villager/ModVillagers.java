package com.jacobpmods.neomod.villager;

import com.google.common.collect.ImmutableSet;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPE = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, FirstNeoMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, FirstNeoMod.MOD_ID);


    public static final Holder<PoiType> TAXIDERMY_POI = POI_TYPE.register("taxidermy_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.TAXIDERMY_TABLE.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final Holder<VillagerProfession> TAXIDERMIST = VILLAGER_PROFESSIONS.register("taxidermist",
            () -> new VillagerProfession("taxidermist", holder -> holder.value() == TAXIDERMY_POI.value(),
                    holder -> holder.value() == TAXIDERMY_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_LIBRARIAN));



    public static void register(IEventBus bus) {
        POI_TYPE.register(bus);
        VILLAGER_PROFESSIONS.register(bus);
    }
}
