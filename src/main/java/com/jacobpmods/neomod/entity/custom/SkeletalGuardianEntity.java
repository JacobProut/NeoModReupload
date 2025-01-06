package com.jacobpmods.neomod.entity.custom;

import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;

public class SkeletalGuardianEntity extends Monster {

    public SkeletalGuardianEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(PathType.WATER, -1.0F);
    }

    private final ServerBossEvent bossEvent =
            new ServerBossEvent(Component.literal("Skeletal Guardian"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_10);

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Endermite.class, true, false));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal(this, false));
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel serverLevel, DamageSource damageSource, boolean recentlyHit) {
        super.dropCustomDeathLoot(serverLevel, damageSource, recentlyHit);

        int bloodShard = 1;

        ItemStack shardItem = new ItemStack(ModItems.SKELETAL_BLOOD_SHARD.get(), bloodShard);
        serverLevel.addFreshEntity(new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), shardItem));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 250.0)
                .add(Attributes.MOVEMENT_SPEED, 0.30)
                .add(Attributes.ATTACK_DAMAGE, 12.0)
                .add(Attributes.FOLLOW_RANGE, 80.0)
                .add(Attributes.STEP_HEIGHT, 2.0);
    }


    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }


    //Boss Bar
    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}
