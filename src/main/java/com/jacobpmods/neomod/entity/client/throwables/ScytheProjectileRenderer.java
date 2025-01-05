package com.jacobpmods.neomod.entity.client.throwables;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.entity.custom.throwables.ScytheProjectileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ScytheProjectileRenderer extends EntityRenderer<ScytheProjectileEntity> {
    private ScytheProjectileModel model;
    public ScytheProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ScytheProjectileModel(context.bakeLayer(ScytheProjectileModel.LAYER_LOCATION));
    }

    @Override
    public void render(ScytheProjectileEntity pEntity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        if (!pEntity.isGrounded()) {
            // Interpolate yaw rotation smoothly
            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, pEntity.yRotO, pEntity.getYRot())));
            // Adjust pitch rotation for east-west alignment (horizontal flipping)
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0f)); // Rotate around Z to make the model face east-west
            // Optional: If you still want the spinning effect, include this
            poseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getRenderingRotation() * 5f));
            poseStack.translate(0, -1.0f, 0);
        } else {
            // Grounded orientation
            poseStack.mulPose(Axis.YP.rotationDegrees(pEntity.groundOffset.y));
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0f)); // Align to face east-west
            poseStack.translate(0, -1.0f, 0);
        }

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                buffer, this.model.renderType(this.getTextureLocation(pEntity)),false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(pEntity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ScytheProjectileEntity scytheProjectileEntity) {
        return ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "textures/entity/throwable_scythe/throwable_scythe.png");
    }
}
