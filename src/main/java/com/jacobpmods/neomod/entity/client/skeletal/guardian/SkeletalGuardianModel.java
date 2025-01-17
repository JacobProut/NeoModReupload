package com.jacobpmods.neomod.entity.client.skeletal.guardian;

import com.jacobpmods.neomod.entity.custom.SkeletalGuardianEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SkeletalGuardianModel extends HierarchicalModel<SkeletalGuardianEntity> {
    private final ModelPart skeletalGuardian;
    private final ModelPart head;
    private final ModelPart torso;
    private final ModelPart arms;
    private final ModelPart leftarm;
    private final ModelPart lefthand;
    private final ModelPart rightarm;
    private final ModelPart spine;
    private final ModelPart ribs;
    private final ModelPart hips;
    private final ModelPart shoulders;
    private final ModelPart Legs;
    private final ModelPart rightleg;
    private final ModelPart rshin;
    private final ModelPart rknee;
    private final ModelPart rthigh;
    private final ModelPart leftleg;
    private final ModelPart lshin;
    private final ModelPart lthigh;
    private final ModelPart lknee;
    private final ModelPart feet;
    private final ModelPart left;
    private final ModelPart right;
    private final ModelPart Scythe;

    public SkeletalGuardianModel(ModelPart root) {
        this.skeletalGuardian = root.getChild("skeletalGuardian");
        this.head = this.skeletalGuardian.getChild("head");
        this.torso = this.skeletalGuardian.getChild("torso");
        this.arms = this.torso.getChild("arms");
        this.leftarm = this.arms.getChild("leftarm");
        this.lefthand = this.leftarm.getChild("lefthand");
        this.rightarm = this.arms.getChild("rightarm");
        this.spine = this.torso.getChild("spine");
        this.ribs = this.torso.getChild("ribs");
        this.hips = this.torso.getChild("hips");
        this.shoulders = this.torso.getChild("shoulders");
        this.Legs = this.skeletalGuardian.getChild("Legs");
        this.rightleg = this.Legs.getChild("rightleg");
        this.rshin = this.rightleg.getChild("rshin");
        this.rknee = this.rightleg.getChild("rknee");
        this.rthigh = this.rightleg.getChild("rthigh");
        this.leftleg = this.Legs.getChild("leftleg");
        this.lshin = this.leftleg.getChild("lshin");
        this.lthigh = this.leftleg.getChild("lthigh");
        this.lknee = this.leftleg.getChild("lknee");
        this.feet = this.skeletalGuardian.getChild("feet");
        this.left = this.feet.getChild("left");
        this.right = this.feet.getChild("right");
        this.Scythe = this.skeletalGuardian.getChild("Scythe");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition skeletalGuardian = partdefinition.addOrReplaceChild("skeletalGuardian", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = skeletalGuardian.addOrReplaceChild("head", CubeListBuilder.create().texOffs(94, 114).addBox(-5.0F, -14.0F, -13.0F, 10.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(5.0F, -12.0F, -13.0F, 3.0F, 10.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-8.0F, -12.0F, -13.0F, 3.0F, 10.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(106, 33).addBox(-5.0F, -5.0F, -13.0F, 10.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(130, 131).addBox(-2.0F, -10.0F, -13.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(72, 107).addBox(-5.0F, -14.0F, 5.0F, 10.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 0).addBox(-5.0F, -15.0F, -11.0F, 10.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(57, 44).addBox(6.0F, -13.0F, -11.0F, 2.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(1, 58).addBox(-8.0F, -13.0F, -11.0F, 2.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(56, 61).addBox(-6.0F, -15.0F, -11.0F, 1.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(56, 80).addBox(-6.0F, -2.0F, -11.0F, 1.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(90, 61).addBox(5.0F, -2.0F, -11.0F, 1.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 75).addBox(5.0F, -15.0F, -11.0F, 1.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(44, 17).addBox(-5.0F, 0.0F, -11.0F, 10.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -56.0F, 6.0F));

        PartDefinition torso = skeletalGuardian.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, -46.0F, 8.0F));

        PartDefinition arms = torso.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -2.0F));

        PartDefinition leftarm = arms.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(32, 139).addBox(-2.0F, 22.0F, -4.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(118, 124).addBox(-3.25F, 11.0F, -4.0F, 2.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(20, 125).addBox(0.25F, 11.0F, -4.0F, 2.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(126, 10).addBox(-3.0F, 21.0F, -4.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(42, 110).addBox(-2.0F, -1.0F, -5.0F, 3.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(72, 128).addBox(-2.0F, 9.0F, -3.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(16.0F, 0.0F, 0.0F));

        PartDefinition lefthand = leftarm.addOrReplaceChild("lefthand", CubeListBuilder.create().texOffs(48, 143).addBox(-4.0F, 26.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 127).addBox(-5.0F, 24.0F, -5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(112, 130).addBox(-4.0F, 25.0F, -7.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 133).addBox(-4.0F, 25.0F, -5.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 135).addBox(-4.0F, 25.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 137).addBox(-4.0F, 25.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(84, 143).addBox(-4.0F, 26.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 143).addBox(-4.0F, 26.0F, -5.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(88, 143).addBox(-4.0F, 26.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(92, 143).addBox(-5.0F, 25.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = lefthand.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(142, 129).addBox(3.0F, -4.0F, -2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 127).addBox(3.0F, -4.0F, -7.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 139).addBox(3.0F, -4.0F, -4.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 132).addBox(3.0F, -4.0F, -5.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 25.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r2 = lefthand.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(124, 51).addBox(0.0F, -1.0F, -1.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 24.0F, -6.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition rightarm = arms.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(4, 143).addBox(1.0F, 26.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(124, 75).addBox(-2.0F, 21.0F, -4.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(8, 143).addBox(1.0F, 26.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(124, 61).addBox(-2.25F, 11.0F, -4.0F, 2.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(26, 110).addBox(-1.0F, -1.0F, -5.0F, 3.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(32, 125).addBox(1.25F, 11.0F, -4.0F, 2.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 127).addBox(-1.0F, 9.0F, -3.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(68, 139).addBox(-1.0F, 22.0F, -4.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(124, 44).addBox(-2.0F, 24.0F, -7.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(58, 110).addBox(-3.0F, 24.0F, -7.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 115).addBox(-3.0F, 24.0F, -5.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 143).addBox(1.0F, 26.0F, -5.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(140, 111).addBox(-3.0F, 28.0F, -7.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(112, 125).addBox(-3.0F, 24.0F, -2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 134).addBox(1.0F, 26.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 143).addBox(1.0F, 25.0F, -6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 120).addBox(-3.0F, 24.0F, -4.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 125).addBox(-3.0F, 28.0F, -2.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 123).addBox(-3.0F, 28.0F, -4.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 35).addBox(-3.0F, 28.0F, -5.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-16.0F, 0.0F, 0.0F));

        PartDefinition spine = torso.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(62, 99).addBox(-10.0F, -17.0F, -1.0F, 2.0F, 27.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 8.0F, -2.0F));

        PartDefinition ribs = torso.addOrReplaceChild("ribs", CubeListBuilder.create().texOffs(94, 120).addBox(1.0F, -27.0F, 5.0F, 9.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(120, 96).addBox(1.0F, -31.0F, 5.0F, 9.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(120, 101).addBox(1.0F, -35.0F, 5.0F, 9.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(120, 106).addBox(-10.0F, -27.0F, 5.0F, 9.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(122, 80).addBox(-10.0F, -31.0F, 5.0F, 9.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(122, 85).addBox(-10.0F, -35.0F, 5.0F, 9.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 133).addBox(9.0F, -27.0F, 2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(130, 31).addBox(4.0F, -26.0F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(44, 133).addBox(1.0F, -23.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(72, 134).addBox(7.0F, -27.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(136, 58).addBox(-1.0F, -27.0F, -1.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(136, 65).addBox(-4.0F, -23.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(130, 124).addBox(-7.0F, -26.0F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(136, 70).addBox(-10.0F, -27.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(56, 137).addBox(-12.0F, -27.0F, 2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 138).addBox(-12.0F, -31.0F, 2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(138, 15).addBox(-12.0F, -35.0F, 2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(138, 20).addBox(9.0F, -31.0F, 2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(138, 25).addBox(9.0F, -35.0F, 2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(44, 138).addBox(7.0F, -35.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(84, 138).addBox(7.0F, -31.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(96, 138).addBox(-10.0F, -31.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(108, 138).addBox(-10.0F, -35.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(126, 15).addBox(4.0F, -35.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(138, 113).addBox(4.0F, -29.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(138, 118).addBox(-7.0F, -29.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(126, 23).addBox(-7.0F, -35.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(120, 138).addBox(1.0F, -33.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(132, 138).addBox(-4.0F, -33.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(130, 38).addBox(-2.0F, -31.0F, -1.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(86, 132).addBox(1.0F, -28.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(98, 132).addBox(-4.0F, -28.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(142, 75).addBox(-1.0F, -29.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 28.0F, -8.0F));

        PartDefinition hips = torso.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(56, 34).addBox(-11.0F, 0.0F, -2.0F, 22.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(56, 39).addBox(-11.0F, 0.0F, -8.0F, 22.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(122, 90).addBox(3.0F, 0.0F, -5.0F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(72, 123).addBox(-11.0F, 0.0F, -5.0F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(32, 97).addBox(1.0F, -1.0F, 0.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(120, 111).addBox(-10.0F, -1.0F, 0.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(94, 59).addBox(-10.0F, -1.0F, -8.0F, 20.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(118, 114).addBox(10.0F, -1.0F, -8.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 117).addBox(-11.0F, -1.0F, -8.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, -1.0F));

        PartDefinition shoulders = torso.addOrReplaceChild("shoulders", CubeListBuilder.create().texOffs(94, 107).addBox(-1.0F, -2.0F, -1.0F, 8.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 110).addBox(-29.0F, -2.0F, -1.0F, 8.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, -7.0F, -6.0F));

        PartDefinition Legs = skeletalGuardian.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0F, -24.2505F, 3.3809F));

        PartDefinition rightleg = Legs.addOrReplaceChild("rightleg", CubeListBuilder.create(), PartPose.offset(-7.0F, 0.0F, 0.0F));

        PartDefinition rshin = rightleg.addOrReplaceChild("rshin", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = rshin.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(96, 18).addBox(-1.0F, -4.7495F, -1.0F, 5.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 21.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition rknee = rightleg.addOrReplaceChild("rknee", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r4 = rknee.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(12, 139).addBox(-0.5F, -3.7495F, -1.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition rthigh = rightleg.addOrReplaceChild("rthigh", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r5 = rthigh.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(90, 80).addBox(-1.0F, -4.7495F, -1.0F, 5.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 8.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition leftleg = Legs.addOrReplaceChild("leftleg", CubeListBuilder.create(), PartPose.offset(6.0F, 0.0F, 0.0F));

        PartDefinition lshin = leftleg.addOrReplaceChild("lshin", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r6 = lshin.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(94, 44).addBox(-4.0F, -4.7495F, -1.0F, 5.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 21.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition lthigh = leftleg.addOrReplaceChild("lthigh", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r7 = lthigh.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 94).addBox(-4.0F, -4.7495F, -1.0F, 5.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 8.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition lknee = leftleg.addOrReplaceChild("lknee", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r8 = lknee.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(22, 139).addBox(-1.5F, -3.7495F, -1.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 11.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition feet = skeletalGuardian.addOrReplaceChild("feet", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 4.0F));

        PartDefinition left = feet.addOrReplaceChild("left", CubeListBuilder.create().texOffs(86, 128).addBox(-1.1F, -4.0F, 0.625F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 96).addBox(-3.0F, -2.0F, -7.6191F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(142, 30).addBox(-3.2F, -2.0F, -10.6191F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(38, 58).addBox(-0.6F, -2.0F, -9.6191F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 62).addBox(0.7F, -2.0F, -9.6191F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 121).addBox(1.9F, -2.0F, -9.6191F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 2.0F, 1.0F));

        PartDefinition right = feet.addOrReplaceChild("right", CubeListBuilder.create().texOffs(86, 128).addBox(-2.1F, -4.0F, 0.625F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 99).addBox(-3.0F, -2.0F, -7.6191F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(56, 142).addBox(1.2F, -2.0F, -10.6191F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(38, 66).addBox(-0.4F, -2.0F, -9.6191F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 70).addBox(-1.7F, -2.0F, -9.6191F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 117).addBox(-2.9F, -2.0F, -9.6191F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 2.0F, 1.0F));

        PartDefinition Scythe = skeletalGuardian.addOrReplaceChild("Scythe", CubeListBuilder.create().texOffs(72, 99).addBox(-2.0F, -8.0F, -8.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(96, 0).addBox(-2.0F, -25.0F, -14.0F, 3.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(62, 129).addBox(-2.0F, -25.0F, 1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 34).addBox(-2.0F, -26.0F, -2.0F, 3.0F, 47.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -26.0F, 3.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r9 = Scythe.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(126, 0).addBox(-3.0F, -5.9424F, 0.0872F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -18.2576F, -16.6916F, 0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r10 = Scythe.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(34, 84).addBox(-3.0F, -6.3456F, -0.0455F, 3.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -16.2389F, -21.3844F, 0.3054F, 0.0F, 0.0F));

        PartDefinition cube_r11 = Scythe.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(34, 75).addBox(-3.0F, -6.2452F, 0.4638F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -15.5548F, -23.2638F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r12 = Scythe.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(44, 125).addBox(-3.0F, -2.0F, -2.8F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -18.0772F, -25.5573F, -1.0472F, 0.0F, 0.0F));

        PartDefinition cube_r13 = Scythe.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(94, 125).addBox(-3.0F, -1.0F, -2.8F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -17.7674F, -25.7521F, -0.8727F, 0.0F, 0.0F));

        PartDefinition cube_r14 = Scythe.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(110, 132).addBox(-3.0F, -4.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -21.337F, 1.3112F, -0.3927F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(SkeletalGuardianEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(SkeletalGuardianAnimations.SKELETAL_GUARDIAN_WALK, limbSwing, limbSwingAmount, 2f, 2f); //changes animation speed

       //this.animate(entity.idleAnimationState, SkeletalGuardianEntity.SKELETAL_ZOMBIE_IDLE, ageInTicks, 1f);
        //this.animate(entity.attackAnimationState, SkeletalZombieAnimations.SKELETAL_ZOMBIE_ATTACK, ageInTicks, 1f);

    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, - 30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch * ((float)Math.PI / 180f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        skeletalGuardian.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return skeletalGuardian;
    }

}
