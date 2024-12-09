package com.jacobpmods.neomod.entity.client.skeletal.zombie;

import com.jacobpmods.neomod.entity.custom.SkeletalZombieEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SkeletalZombieModel extends HierarchicalModel<SkeletalZombieEntity> {
    private final ModelPart skeletalZombie;
    private final ModelPart abovebody;
    private final ModelPart headgroup;
    private final ModelPart body;
    private final ModelPart chest;
    private final ModelPart leftside;
    private final ModelPart rightside;
    private final ModelPart legs;
    private final ModelPart left;
    private final ModelPart right;

    public SkeletalZombieModel(ModelPart root) {
        this.skeletalZombie = root.getChild("skeletalZombie");
        this.abovebody = skeletalZombie.getChild("abovebody");
        this.headgroup = abovebody.getChild("headgroup");
        this.body = skeletalZombie.getChild("body");
        this.chest = body.getChild("chest");
        this.leftside = body.getChild("leftside");
        this.rightside = body.getChild("rightside");
        this.legs = skeletalZombie.getChild("legs");
        this.left = legs.getChild("left");
        this.right = legs.getChild("right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition skeletalZombie = partdefinition.addOrReplaceChild("skeletalZombie", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition abovebody = skeletalZombie.addOrReplaceChild("abovebody", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 0.0F));

        PartDefinition headgroup = abovebody.addOrReplaceChild("headgroup", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = skeletalZombie.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 0.0F));

        PartDefinition chest = body.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -1.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -23.0F, 0.0F));

        PartDefinition leftside = body.addOrReplaceChild("leftside", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -22.0F, 0.0F));

        PartDefinition rightside = body.addOrReplaceChild("rightside", CubeListBuilder.create().texOffs(40, 16).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.0F, 0.0F));

        PartDefinition legs = skeletalZombie.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, 0.0F));

        PartDefinition left = legs.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-0.1F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right = legs.addOrReplaceChild("right", CubeListBuilder.create().texOffs(0, 16).addBox(-3.9F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(SkeletalZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(SkeletalZombieAnimations.SKELETAL_ZOMBIE_WALK, limbSwing, limbSwingAmount, 1f, 1f); //changes animation speed

        this.animate(entity.idleAnimationState, SkeletalZombieAnimations.SKELETAL_ZOMBIE_IDLE, ageInTicks, 1f);
        this.animate(entity.attackAnimationState, SkeletalZombieAnimations.SKELETAL_ZOMBIE_ATTACK, ageInTicks, 1f);

    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, - 30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.headgroup.yRot = headYaw * ((float)Math.PI / 180f);
        this.headgroup.xRot = headPitch * ((float)Math.PI / 180f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        skeletalZombie.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return skeletalZombie;
    }
}