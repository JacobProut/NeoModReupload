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
                        output.accept(ModItems.SPEED_APPLE.get());
                        output.accept(ModItems.FIRE_BALL.get());

                        //Nexon Items
                        output.accept(ModItems.nexon.get());
                        output.accept(ModItems.HEATED_NEXON.get());
                        output.accept(ModItems.NEXON_INGOT.get());
                        output.accept(ModItems.NEXON_REINFORCED_INGOT.get());
                        output.accept(ModBlocks.NEXON_BLOCK.get());
                        output.accept(ModBlocks.NEXON_ORE_BLOCK.get());
                        output.accept(ModItems.NEXON_PICKAXE.get());
                        output.accept(ModItems.NEXON_HOE.get());
                        output.accept(ModItems.NEXON_SHOVEL.get());
                        output.accept(ModItems.NEXON_SWORD.get());
                        output.accept(ModItems.NEXON_AXE.get());
                        output.accept(ModItems.NEXON_HELMET);
                        output.accept(ModItems.NEXON_CHESTPLATE);
                        output.accept(ModItems.NEXON_LEGGINGS);
                        output.accept(ModItems.NEXON_BOOTS);

                        //Spawn Eggs
                        output.accept(ModItems.SKELETAL_ZOMBIE_SPAWN_EGG);
                        output.accept(ModItems.SKELETAL_ENDERMAN_SPAWN_EGG);

                        //Nexon Blocks
                        output.accept(ModBlocks.NEXON_BLOCK.get());
                        output.accept(ModBlocks.NEXON_ORE_BLOCK.get());

                        //Grass Blocks
                        output.accept(ModBlocks.GHOSTLY_GRASS_BLOCK.get());
                        output.accept(ModBlocks.GHOSTLY_DIRT.get());
                        output.accept(ModBlocks.BLOODY_GRASS_BLOCK.get());
                        //output.accept(ModBlocks.BLOODY_DIRT.get());


                        output.accept(ModBlocks.LOG_GHOSTLY.get());
                        output.accept(ModBlocks.GHOSTLY_WEB.get());

                        //BONE ITEMS
                        output.accept(ModBlocks.BONE_BRICK.get());

                        //Custom Block Models
                        output.accept(ModBlocks.PEDESTAL.get());

                        //Fluids
                        output.accept(ModFluids.POISONED_WATER_BUCKET);

                        //Portals
                        output.accept(ModBlocks.GHOSTLY_PORTAL_BLOCK.get());

                        //Flowers
                        output.accept(ModBlocks.OOZING_FLOWER.get());

                        //Leaves
                        output.accept(ModBlocks.GHOSTLY_LEAVES.get());
                        output.accept(ModBlocks.BLOODY_LEAVES.get());

                        //Saplings
                        output.accept(ModBlocks.GHOSTLY_SAPLING.get());
                        output.accept(ModBlocks.BLOODY_SAPLING.get());

                        //Logs/Wood
                        output.accept(ModBlocks.LOG_BLOODY.get());

                        //Planks
                        output.accept(ModBlocks.PLANKS_GHOSTLY.get());
                        output.accept(ModBlocks.PLANKS_BLOODY.get());

                        output.accept(ModBlocks.SKULL_N_BONES.get());
                        output.accept(ModItems.UNDEAD_BONE.get());

                        //Rings
                        output.accept(ModItems.UNDEAD_STRENGTH_RING.get());

                        //Necklaces
                        output.accept(ModItems.SOUL_SPLIT_NECKLACE.get());

                        //Berries
                        output.accept(ModItems.BLOOD_BONE_FRUIT_BERRIES.get());


                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
