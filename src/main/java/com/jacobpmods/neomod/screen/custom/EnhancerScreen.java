package com.jacobpmods.neomod.screen.custom;

import com.jacobpmods.neomod.FirstNeoMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class EnhancerScreen extends AbstractContainerScreen<EnhancerMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "textures/gui/enhancer/enhancer_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "textures/gui/enhancer/arrow_progress.png");
    private static final ResourceLocation CRYSTAL_TEXTURE =
            ResourceLocation.parse("textures/block/amethyst_cluster.png");

    public EnhancerScreen(EnhancerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;


        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        renderProgressCrystal(guiGraphics, x, y);
    }

    //THIS IS LOCATION OF PROGRESS ARROW. CHANGE VALUES
    //REFER TO 33:30 in Kaupens Custom Block Entity method to learn how to properly set these
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
                //x + 78 looks good too
            guiGraphics.blit(ARROW_TEXTURE, x + 77, y + 37, 0, 0, menu.getScaledArrowProgress(), 16,24,16);
        }
    }

    //THIS IS LOCATION OF PROGRESS CRYSTAL. CHANGE VALUES
    //REFER TO 33:30 in Kaupens Custom Block Entity method to learn how to properly set these
    private void renderProgressCrystal(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(CRYSTAL_TEXTURE, x + 112, y + 16 + 16 - menu.getScaledCrystalProgress(), 0,
                    16 - menu.getScaledCrystalProgress(), 16, menu.getScaledCrystalProgress(), 16, 16);
        }
    }



    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}