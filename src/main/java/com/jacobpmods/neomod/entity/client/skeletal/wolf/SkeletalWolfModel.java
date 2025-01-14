package com.jacobpmods.neomod.entity.client.skeletal.wolf;

import com.jacobpmods.neomod.entity.custom.SkeletalWolfEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SkeletalWolfModel extends HierarchicalModel<SkeletalWolfEntity> {
        // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
        private final ModelPart Wolf;
        private final ModelPart leg1;
        private final ModelPart head;
        private final ModelPart leg0;
        private final ModelPart leg2;
        private final ModelPart leg3;
        private final ModelPart tail;
        private final ModelPart body;

        public SkeletalWolfModel(ModelPart root) {
            this.Wolf = root.getChild("Wolf");
            this.leg1 = Wolf.getChild("leg1");
            this.head = Wolf.getChild("head");
            this.leg0 = Wolf.getChild("leg0");
            this.leg2 = Wolf.getChild("leg2");
            this.leg3 = Wolf.getChild("leg3");
            this.tail = Wolf.getChild("tail");
            this.body = Wolf.getChild("body");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition Wolf = partdefinition.addOrReplaceChild("Wolf", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

            PartDefinition leg1 = Wolf.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -8.0F, 7.0F));

            PartDefinition head = Wolf.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 20).addBox(-3.01F, -3.0F, -1.0F, 6.02F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(8, 30).addBox(-3.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(14, 30).addBox(1.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(20, 20).addBox(-1.5F, -0.0156F, -5.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -10.5F, -7.0F));

            PartDefinition leg0 = Wolf.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(24, 10).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -8.0F, 7.0F));

            PartDefinition leg2 = Wolf.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(21, 28).addBox(-1.0F, 0.0F, -0.99F, 2.0F, 8.0F, 1.98F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -8.0F, -4.0F));

            PartDefinition leg3 = Wolf.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(28, 27).addBox(-1.0F, 0.0F, -0.99F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -8.0F, -4.0F));

            PartDefinition tail = Wolf.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -12.0F, 8.0F, 0.9599F, 0.0F, 0.0F));

            PartDefinition body = Wolf.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -3.0F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

            return LayerDefinition.create(meshdefinition, 64, 64);
        }
    @Override
    public void setupAnim(SkeletalWolfEntity skeletalWolfEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(SkeletalWolfAnimations.SKELETAL_WOLF_WALK, limbSwing, limbSwingAmount, 1f, 1f); //changes animation speed
       // this.animate(skeletalWolfEntity.sitAnimation, SkeletalWolfAnimations.SKELETAL_WOLF_SIT, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, - 30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch * ((float)Math.PI / 180f);

    }


    @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
            Wolf.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        }

    @Override
    public ModelPart root() {
        return Wolf;
    }
}
