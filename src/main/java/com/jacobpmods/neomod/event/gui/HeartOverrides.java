package com.jacobpmods.neomod.event.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class HeartOverrides {

    public static void renderCustomHearts(
            GuiGraphics guiGraphics,
            Gui gui,
            Player player,
            int x,
            int y,
            int health,
            int maxHealth,
            int absorb,
            boolean isHardcore,
            Gui.HeartType heartType
    ) {
        int fullHearts = Mth.ceil(health / 2.0);
        int maxFullHearts = Mth.ceil(maxHealth / 2.0);
        int absorbHearts = Mth.ceil(absorb / 2.0);

        // Render hearts (adjust based on poison resistance)
        for (int i = 0; i < fullHearts + absorbHearts; i++) {
            int heartX = x + (i % 10) * 8;
            int heartY = y - (i / 10) * 10;

            if (i < fullHearts) {
                // Render normal heart (ignore poison effect visuals)
                renderHeart(guiGraphics, Gui.HeartType.NORMAL, heartX, heartY, isHardcore, false, false);
            } else if (i < fullHearts + absorbHearts) {
                // Render absorption heart
                renderHeart(guiGraphics, Gui.HeartType.ABSORBING, heartX, heartY, isHardcore, false, false);
            }
        }
    }

    public static void renderHeart(
            GuiGraphics guiGraphics,
            Gui.HeartType heartType,
            int x,
            int y,
            boolean isHardcore,
            boolean blinking,
            boolean halfHeart
    ) {
        RenderSystem.enableBlend();
        guiGraphics.blitSprite(
                heartType.getSprite(isHardcore, halfHeart, blinking),
                x,
                y,
                9,
                9
        );
        RenderSystem.disableBlend();
    }

    public static Gui.HeartType getHeartTypeForPlayer(Player player) {
        if (player.hasEffect(MobEffects.POISON)) {
            return Gui.HeartType.POISIONED; // Replace with your custom type if needed
        } else if (player.hasEffect(MobEffects.WITHER)) {
            return Gui.HeartType.WITHERED;
        } else if (player.isFullyFrozen()) {
            return Gui.HeartType.FROZEN;
        } else {
            return Gui.HeartType.NORMAL;
        }
    }
}
