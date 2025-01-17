package com.jacobpmods.neomod.entity.client.skeletal.guardian;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.entity.client.ModModelLayers;
import com.jacobpmods.neomod.entity.custom.SkeletalGuardianEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class SkeletalGuardianRender extends LivingEntityRenderer<SkeletalGuardianEntity, SkeletalGuardianModel> {
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

    @Nullable
    @Override
    public RenderType getRenderType(SkeletalGuardianEntity entity, boolean p_115323_, boolean p_115324_, boolean p_115325_) {
        return RenderType.ENTITY_TRANSLUCENT.apply(getTextureLocation(entity), false);
    }
}
