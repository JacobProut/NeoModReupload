package com.jacobpmods.neomod.entity.client.skeletal.cow;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.entity.client.ModModelLayers;
import com.jacobpmods.neomod.entity.custom.SkeletalCowEntity;
import com.jacobpmods.neomod.entity.custom.SkeletalZombieEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SkeletalCowRender extends MobRenderer<SkeletalCowEntity, SkeletalCowModel> {
    public SkeletalCowRender(EntityRendererProvider.Context context) {
        super(context, new SkeletalCowModel(context.bakeLayer(ModModelLayers.SKELETAL_COW)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletalCowEntity skeletalCowEntity) {
        return ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "textures/entity/skeletalcow/skeletal_cow.png");
    }

    @Override
    public void render(SkeletalCowEntity skeletalCowEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(skeletalCowEntity.isBaby()) {
            pPoseStack.scale(0.45f,0.45f,0.45f);
        }
        super.render(skeletalCowEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);


    }


}
