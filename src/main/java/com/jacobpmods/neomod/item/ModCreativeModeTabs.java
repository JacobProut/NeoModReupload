package com.jacobpmods.neomod.item;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.fluid.ModFluids;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstNeoMod.MOD_ID);

    public static final Supplier<CreativeModeTab> JACOBS_MODDED_ITEMS_TAB = CREATIVE_MODE_TABS.register("jacobs_modded_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.nexon.get()))
                    .title(Component.translatable("Jacobs Modded items")).displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.speedapple.get());
                        output.accept(ModItems.fireball.get());

                        //Nexon Items
                        output.accept(ModItems.nexon.get());
                        output.accept(ModItems.heatednexon.get());
                        output.accept(ModItems.nexoningot.get());
                        output.accept(ModItems.nexonreinforcedingot.get());
                        output.accept(ModBlocks.NEXON_BLOCK.get());
                        output.accept(ModBlocks.NEXON_ORE_BLOCK.get());
                        output.accept(ModItems.nexonpickaxe.get());
                        output.accept(ModItems.nexonhoe.get());
                        output.accept(ModItems.nexonshovel.get());
                        output.accept(ModItems.nexonsword.get());
                        output.accept(ModItems.nexonaxe.get());
                        output.accept(ModItems.nexonhelmet);
                        output.accept(ModItems.nexonchestplate);
                        output.accept(ModItems.nexonleggings);
                        output.accept(ModItems.nexonboots);

                        //Spawn Eggs
                        output.accept(ModItems.SKELETAL_ZOMBIE_SPAWN_EGG);
                        output.accept(ModItems.SKELETAL_ENDERMAN_SPAWN_EGG);

                        //Nexon Blocks
                        output.accept(ModBlocks.NEXON_BLOCK.get());
                        output.accept(ModBlocks.NEXON_ORE_BLOCK.get());

                        //Ghostly Blocks
                        output.accept(ModBlocks.GHOSTLY_GRASS_BLOCK.get());
                        output.accept(ModBlocks.GHOSTLY_DIRT.get());
                        output.accept(ModBlocks.LOG_GHOSTLY.get());
                        output.accept(ModBlocks.PLANKS_GHOSTLY.get());
                        output.accept(ModBlocks.GHOSTLY_SAPLING.get());
                        output.accept(ModBlocks.GHOSTLY_LEAVES.get());
                        output.accept(ModBlocks.GHOSTLY_WEB.get());

                        //BONE ITEMS
                        output.accept(ModBlocks.BONE_BRICK.get());

                        //Custom Block Models
                        output.accept(ModBlocks.PEDESTAL.get());

                        //Fluids
                        output.accept(ModFluids.POISONED_WATER_BUCKET);

                        //Portals
                        output.accept(ModBlocks.GHOSTLY_PORTAL_BLOCK.get());

                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
