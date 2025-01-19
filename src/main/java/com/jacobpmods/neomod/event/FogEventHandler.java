package com.jacobpmods.neomod.event;

import com.jacobpmods.neomod.effect.ModMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.common.NeoForge;

public class FogEventHandler {
    public FogEventHandler() {
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderFog(ViewportEvent.RenderFog event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player == null) {
            return; // Prevents null pointer if player is not available
        }

        // Check if the player has the NoLavaFog effect
        MobEffectInstance effectInstance = player.getEffect(ModMobEffects.NO_LAVA_FOG);
        if (effectInstance != null) {

            // Cancel the fog rendering (adjusting fog properties)
            event.setNearPlaneDistance(0.0F);  // Disable near fog
            event.setFarPlaneDistance(1000.0F);  // Extend far fog distance
            event.setCanceled(true); //This event must be cancelled for any changes to the planes distances to take effect.!!!!!!!!!!!
        }
    }

    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockScreenEffectEvent event) {
        Player player = Minecraft.getInstance().player;


        if (player != null && player.getEffect(ModMobEffects.NO_LAVA_FOG) != null) {   // Check if the player has the No Lava Fog effect
            if (event.getOverlayType() == RenderBlockScreenEffectEvent.OverlayType.FIRE) { // Check if overLay is fire
                event.setCanceled(true); // Cancel the fire overlay rendering
            }
        }
    }
}