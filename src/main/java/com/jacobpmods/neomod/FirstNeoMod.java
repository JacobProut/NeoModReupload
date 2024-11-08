package com.jacobpmods.neomod;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.block.entity.custom.ModBlockEntities;
import com.jacobpmods.neomod.block.entity.renderer.PedestalBlockEntityRenderer;
import com.jacobpmods.neomod.entity.ModEntities;
import com.jacobpmods.neomod.entity.client.skeletal.enderman.SkeletalEndermanRender;
import com.jacobpmods.neomod.entity.client.skeletal.zombie.SkeletalZombieRender;
import com.jacobpmods.neomod.fluid.BaseFluidType;
import com.jacobpmods.neomod.fluid.ModFluidTypes;
import com.jacobpmods.neomod.fluid.ModFluids;
import com.jacobpmods.neomod.item.ModArmorMaterials;
import com.jacobpmods.neomod.item.ModCreativeModeTabs;
import com.jacobpmods.neomod.item.ModItems;
import com.jacobpmods.neomod.item.custom.enchantment.ModEnchantmentEffects;
import com.jacobpmods.neomod.screen.ModMenuTypes;
import com.jacobpmods.neomod.screen.custom.PedestalScreen;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FirstNeoMod.MOD_ID)
public class FirstNeoMod {
    public static final String MOD_ID = "neomod";
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public FirstNeoMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);

        ModArmorMaterials.register(modEventBus);

        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        ModFluidTypes.register(modEventBus);
        ModFluids.register(modEventBus);

        ModMenuTypes.register(modEventBus);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.nexon);
            event.accept(ModItems.heatednexon);
            event.accept(ModItems.nexoningot);
            event.accept(ModItems.nexonreinforcedingot);
            event.accept(ModItems.nexonaxe);
            event.accept(ModItems.nexonpickaxe);
            event.accept(ModItems.nexonhoe);
            event.accept(ModItems.nexonshovel);
            event.accept(ModItems.nexonsword);

        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.NEXON_BLOCK);
            event.accept(ModBlocks.NEXON_ORE_BLOCK);
            event.accept(ModBlocks.GHOSTLY_GRASS_BLOCK);
            event.accept(ModBlocks.GHOSTLY_DIRT);
            event.accept(ModBlocks.LOG_GHOSTLY);
            event.accept(ModBlocks.PLANKS_GHOSTLY);
            event.accept(ModBlocks.GHOSTLY_LEAVES);
            event.accept(ModBlocks.GHOSTLY_SAPLING);
            event.accept(ModBlocks.GHOSTLY_STONE);


            //Teleporter?
            event.accept(ModBlocks.GHOSTLY_WEB);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            //Entities
            EntityRenderers.register(ModEntities.SKELETAL_ZOMBIE.get(), SkeletalZombieRender::new);
            EntityRenderers.register(ModEntities.SKELETAL_ENDERMAN.get(), SkeletalEndermanRender::new);

            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_POISONED_WATER.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_POISONED_WATER.get(), RenderType.translucent());
            });
        }

        @SubscribeEvent
        public static void onClientExtensions(RegisterClientExtensionsEvent event) {
            event.registerFluidType(((BaseFluidType)ModFluidTypes.POISONED_WATER_FLUID_TYPE.get()).getClientFluidTypeExtensions(),
                    ModFluidTypes.POISONED_WATER_FLUID_TYPE.get());
        }


        //Register Block Entity Rendering
        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.PEDESTAL_MENU.get(), PedestalScreen::new);
        }

    }
}
