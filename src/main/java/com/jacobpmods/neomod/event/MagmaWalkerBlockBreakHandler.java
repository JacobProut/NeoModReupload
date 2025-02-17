package com.jacobpmods.neomod.event;


import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.custom.enchantment.effects.MagmaWalkerEnchantmentEffect;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = FirstNeoMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class MagmaWalkerBlockBreakHandler {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        // Check if the block being broken is temporary obsidian
        if (MagmaWalkerEnchantmentEffect.isTemporaryMagmaBlock((Level) event.getLevel(), event.getPos())) {
            event.setCanceled(true); // Cancel the event to prevent breaking
        }
    }
}
