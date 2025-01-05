package com.jacobpmods.neomod.entity.client.throwables;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.entity.custom.throwables.ScytheProjectileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ScytheProjectileModel extends EntityModel<ScytheProjectileEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "throwable_scythe"), "main");
    private final ModelPart bone;

    public ScytheProjectileModel(ModelPart root) {
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -24.0F, 7.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(11, 2).addBox(-8.75F, -23.0F, 9.0F, 1.5F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(15, 22).addBox(-8.75F, -23.0F, 0.0F, 1.5F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-8.75F, -13.5F, 4.0F, 1.5F, 1.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

        PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(11, 2).addBox(-1.75F, -1.0F, -2.8F, 1.5F, 1.0F, 2.65F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -17.677F, -3.8294F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 0).addBox(-1.75F, -1.8F, 0.0F, 1.5F, 2.8F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -21.337F, 10.3112F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(9, 10).mirror().addBox(-1.75F, -2.8F, 0.0F, 1.5F, 2.8F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, -17.6925F, -4.0537F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r4 = bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(7, 24).addBox(-1.75F, -3.0F, 0.0F, 1.5F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -18.3149F, -3.4713F, 0.3927F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(ScytheProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
