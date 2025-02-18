package com.jacobpmods.neomod.event.enchantmentHandlers.magmaWalker;


import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.custom.enchantment.effects.boots.MagmaWalkerEnchantmentEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

@EventBusSubscriber(modid = FirstNeoMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class MagmaWalkerEventHandlers {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        // Check if the block being broken is temporary obsidian
        if (MagmaWalkerEnchantmentEffect.isTemporaryMagmaBlock((Level) event.getLevel(), event.getPos())) {
            event.setCanceled(true); // Cancel the event to prevent breaking
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        // Check if the entity is a player
        if (event.getEntity() instanceof Player player) {
            // Check if the damage source is from magma blocks
            if (event.getSource().is(DamageTypes.HOT_FLOOR)) {
                event.setCanceled(true); // Cancel the damage from ALL magma blocks
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        if (player.level() instanceof ServerLevel serverLevel) {
            MagmaWalkerEnchantmentEffect.removeAllTemporaryBlocks(serverLevel);
        }
    }

    @SubscribeEvent
    public static void onWorldUnload(LevelEvent.Unload event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            MagmaWalkerEnchantmentEffect.removeAllTemporaryBlocks(serverLevel);
        }
    }



    //This is so ONLY temporary Magma blocks don't cause damage. | This is for reference
   /* @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        // Check if the entity is a player
        if (event.getEntity() instanceof Player player) {
            // Check if the damage source is from magma blocks
            if (event.getSource().is(DamageTypes.HOT_FLOOR)) {
                // Get the block position below the player
                BlockPos posBelowPlayer = player.blockPosition().below();

                // Check if the block below the player is a temporary magma block
                if (MagmaWalkerEnchantmentEffect.isTemporaryMagmaBlock(player.level(), posBelowPlayer)) {
                    event.setCanceled(true); // Cancel the damage
                }
            }
        }
    }*/

}
