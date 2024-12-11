package com.jacobpmods.neomod.item;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.fluid.ModFluids;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
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

                        //Spawn Eggs
                        output.accept(ModItems.SKELETAL_ZOMBIE_SPAWN_EGG);
                        output.accept(ModItems.SKELETAL_ENDERMAN_SPAWN_EGG);
                        output.accept(ModItems.SKELETAL_GUARDIAN_SPAWN_EGG);

                        //Tools
                        output.accept(ModItems.SCYTHE);
                        output.accept(ModItems.BONE_SWORD);
                        output.accept(ModItems.BLOOD_BONE_SWORD);

                        //Skeletal Armor
                        //output.accept(ModItems.SKELETAL_HELMET);

                        //Nexon Items
                        output.accept(ModItems.nexon);
                        output.accept(ModItems.HEATED_NEXON);
                        output.accept(ModItems.NEXON_INGOT);
                        output.accept(ModItems.NEXON_REINFORCED_INGOT);
                        output.accept(ModBlocks.NEXON_BLOCK);
                        output.accept(ModBlocks.NEXON_ORE_BLOCK);
                        output.accept(ModItems.NEXON_PICKAXE);
                        output.accept(ModItems.NEXON_HOE);
                        output.accept(ModItems.NEXON_SHOVEL);
                        output.accept(ModItems.NEXON_SWORD);
                        output.accept(ModItems.NEXON_AXE);
                        output.accept(ModItems.NEXON_HELMET);
                        output.accept(ModItems.NEXON_CHESTPLATE);
                        output.accept(ModItems.NEXON_LEGGINGS);
                        output.accept(ModItems.NEXON_BOOTS);

                        //Nexon Blocks
                        output.accept(ModBlocks.NEXON_BLOCK);
                        output.accept(ModBlocks.NEXON_ORE_BLOCK);


                        output.accept(ModBlocks.SHATTERED_FRAGMENT_ORE_BLOCK);
                        output.accept(ModItems.SHATTERED_FRAGMENT);
                        output.accept(ModItems.SKELETAL_BLOOD_SHARD);

                        //Grass Blocks
                        output.accept(ModBlocks.GHOSTLY_GRASS_BLOCK);
                        output.accept(ModBlocks.BLOODY_GRASS_BLOCK);
                        output.accept(ModBlocks.GHOSTLY_DIRT);
                        //output.accept(ModBlocks.BLOODY_DIRT.get());


                        //Bone Blocks
                        output.accept(ModBlocks.BONE_BRICK);
                        output.accept(ModBlocks.BONE_BRICK_STAIRS);
                        output.accept(ModBlocks.BONE_BRICK_SLAB);
                        output.accept(ModBlocks.BONE_BRICK_FENCE);
                        output.accept(ModBlocks.BONE_BRICK_FENCE_GATE);
                        output.accept(ModBlocks.BONE_BRICK_WALL);
                        output.accept(ModBlocks.BONE_BRICK_PRESSURE_PLATE);
                        output.accept(ModBlocks.BONE_BRICK_BUTTON);


                        //Stone-CobbleStone Blocks
                        output.accept(ModBlocks.GHOSTLY_STONE);
                        output.accept(ModBlocks.GHOSTLY_STONE_STAIRS);
                        output.accept(ModBlocks.GHOSTLY_STONE_SLAB);
                        output.accept(ModBlocks.GHOSTLY_STONE_FENCE);
                        output.accept(ModBlocks.GHOSTLY_STONE_FENCE_GATE);
                        output.accept(ModBlocks.GHOSTLY_STONE_WALL);
                        output.accept(ModBlocks.GHOSTLY_STONE_PRESSURE_PLATE);
                        output.accept(ModBlocks.GHOSTLY_STONE_BUTTON);
                        output.accept(ModBlocks.GHOSTLY_COBBLESTONE);
                        output.accept(ModBlocks.GHOSTLY_COBBLESTONE_STAIRS);
                        output.accept(ModBlocks.GHOSTLY_COBBLESTONE_SLAB);
                        output.accept(ModBlocks.GHOSTLY_COBBLESTONE_FENCE);
                        output.accept(ModBlocks.GHOSTLY_COBBLESTONE_FENCE_GATE);
                        output.accept(ModBlocks.GHOSTLY_COBBLESTONE_WALL);
                        output.accept(ModBlocks.GHOSTLY_COBBLESTONE_PRESSURE_PLATE);
                        output.accept(ModBlocks.GHOSTLY_COBBLESTONE_BUTTON);
                        output.accept(ModBlocks.GHOSTLY_STONE_BRICKS);
                        output.accept(ModBlocks.GHOSTLY_STONEBRICK_STAIRS);
                        output.accept(ModBlocks.GHOSTLY_STONEBRICK_SLAB);
                        output.accept(ModBlocks.GHOSTLY_STONEBRICK_FENCE);
                        output.accept(ModBlocks.GHOSTLY_STONEBRICK_FENCE_GATE);
                        output.accept(ModBlocks.GHOSTLY_STONEBRICK_WALL);
                        output.accept(ModBlocks.GHOSTLY_STONEBRICK_PRESSURE_PLATE);
                        output.accept(ModBlocks.GHOSTLY_STONEBRICK_BUTTON);

                        //Logs/Wood
                        output.accept(ModBlocks.LOG_GHOSTLY);
                        output.accept(ModBlocks.GHOSTLY_PLANKS);
                        output.accept(ModBlocks.GHOSTLY_PLANK_STAIRS);
                        output.accept(ModBlocks.GHOSTLY_PLANK_SLAB);
                        output.accept(ModBlocks.GHOSTLY_PLANK_FENCE);
                        output.accept(ModBlocks.GHOSTLY_PLANK_FENCE_GATE);
                        output.accept(ModBlocks.GHOSTLY_PLANK_WALL);
                        output.accept(ModBlocks.GHOSTLY_PLANK_PRESSURE_PLATE);
                        output.accept(ModBlocks.GHOSTLY_PLANK_BUTTON);
                        output.accept(ModBlocks.LOG_BLOODY);
                        output.accept(ModBlocks.PLANKS_BLOODY);
                        output.accept(ModBlocks.BLOODY_PLANK_STAIRS);
                        output.accept(ModBlocks.BLOODY_PLANK_SLAB);
                        output.accept(ModBlocks.BLOODY_PLANK_FENCE);
                        output.accept(ModBlocks.BLOODY_PLANK_FENCE_GATE);
                        output.accept(ModBlocks.BLOODY_PLANK_WALL);
                        output.accept(ModBlocks.BLOODY_PLANK_PRESSURE_PLATE);
                        output.accept(ModBlocks.BLOODY_PLANK_BUTTON);

                        //Leaves
                        output.accept(ModBlocks.GHOSTLY_LEAVES);
                        output.accept(ModBlocks.BLOODY_LEAVES);

                        //Saplings
                        output.accept(ModBlocks.GHOSTLY_SAPLING);
                        output.accept(ModBlocks.BLOODY_SAPLING);

                        //Flowers
                        output.accept(ModBlocks.OOZING_FLOWER);

                        //Custom Block Models
                        output.accept(ModBlocks.PEDESTAL);
                        output.accept(ModBlocks.ENHANCER);

                        //Fluids
                        output.accept(ModFluids.POISONED_WATER_BUCKET);

                        //Portals
                        output.accept(ModBlocks.GHOSTLY_PORTAL_BLOCK);

                        //Rings
                        output.accept(ModItems.UNDEAD_STRENGTH_RING);

                        //Necklaces
                        output.accept(ModItems.SOUL_SPLIT_NECKLACE);

                        //Berries
                        output.accept(ModItems.BLOOD_BONE_FRUIT_BERRIES);

                        output.accept(ModBlocks.SKULL_N_BONES);
                        output.accept(ModItems.UNDEAD_BONE);
                        output.accept(ModItems.UNDEAD_KEY);
                        output.accept(ModBlocks.GHOSTLY_WEB);

                        //Vines
                        output.accept(ModBlocks.BLOODY_VINE.get());

                    }).build());

   /* public static final Supplier<CreativeModeTab> MODDED_WEAPONS_AND_TOOLS = CREATIVE_MODE_TABS.register("Weapons and Tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SCYTHE.get()))
                    .title(Component.translatable("Jacobs Modded Weapons & Tools")).displayItems((itemDisplayParameters, output) -> {
                        //Tools
                        output.accept(ModItems.SCYTHE);
                        output.accept(ModItems.BONE_SWORD);
                        output.accept(ModItems.BLOOD_BONE_SWORD);

                        output.accept(ModItems.NEXON_PICKAXE);
                        output.accept(ModItems.NEXON_HOE);
                        output.accept(ModItems.NEXON_SHOVEL);
                        output.accept(ModItems.NEXON_SWORD);
                        output.accept(ModItems.NEXON_AXE);

                    }).build());
*/


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
