package com.jacobpmods.neomod.entity.client.skeletal.guardian;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.entity.client.ModModelLayers;
import com.jacobpmods.neomod.entity.custom.SkeletalGuardianEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SkeletalGuardianRender extends MobRenderer<SkeletalGuardianEntity, SkeletalGuardianModel> {
    public SkeletalGuardianRender(EntityRendererProvider.Context context) {
        super(context, new SkeletalGuardianModel(context.bakeLayer(ModModelLayers.SKELETAL_GUARDIAN)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletalGuardianEntity skeletalGuardianEntity) {
        return ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "textures/entity/skeletalguardian/skeletal_guardian.png");
    }

    @Override
    public void render(SkeletalGuardianEntity skeletalGuardianEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(skeletalGuardianEntity.isBaby()) {
            pPoseStack.scale(0.45f,0.45f,0.45f);
        }
        super.render(skeletalGuardianEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);


    }
}
