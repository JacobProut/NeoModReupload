package com.jacobpmods.neomod.entity.client.skeletal.wolf;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.entity.client.ModModelLayers;
import com.jacobpmods.neomod.entity.custom.SkeletalWolfEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SkeletalWolfRender extends MobRenderer<SkeletalWolfEntity, SkeletalWolfModel> {

    public SkeletalWolfRender(EntityRendererProvider.Context context) {
        super(context, new SkeletalWolfModel(context.bakeLayer(ModModelLayers.SKELETAL_WOLF)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletalWolfEntity skeletalWolfEntity) {
        return ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "textures/entity/skeletalwolf/skeletal_wolf.png");
    }

    @Override
    public void render(SkeletalWolfEntity SkeletalWolfEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(SkeletalWolfEntity.isBaby()) {
            pPoseStack.scale(0.45f,0.45f,0.45f);
        }
        super.render(SkeletalWolfEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);


    }
}