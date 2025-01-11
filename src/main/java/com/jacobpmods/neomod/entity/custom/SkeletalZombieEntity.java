package com.jacobpmods.neomod.entity.custom;

import com.jacobpmods.neomod.entity.ai.SkeletalZombieAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Predicate;

public class SkeletalZombieEntity extends Monster {

    //attack code
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(SkeletalZombieEntity.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    public SkeletalZombieEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() { //first number is priority
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SkeletalZombieAttackGoal(this, 1.0, true));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createLivingAttributes()
                .add(Attributes.FOLLOW_RANGE,35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 4.5)
                .add(Attributes.MAX_HEALTH, 25);
    }


    @Override
    public void addAdditionalSaveData(CompoundTag p_34319_) {
        super.addAdditionalSaveData(p_34319_);
        p_34319_.putBoolean("IsBaby", this.isBaby());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_34305_) {
        super.readAdditionalSaveData(p_34305_);
        this.setBaby(p_34305_.getBoolean("IsBaby"));
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
    }

    private void setupAnimationStates() {
        if (this.isAttacking()) {
            // Stop idle animation
            if (this.idleAnimationState.isStarted()) {
                this.idleAnimationState.stop();
            }

            // Reset and start attack animation if not already playing
            if (!this.attackAnimationState.isStarted() || this.attackAnimationTimeout <= 0) {
                this.attackAnimationState.start(this.tickCount);
                this.attackAnimationTimeout = 24; // Reset the attack animation duration
            }

            this.attackAnimationTimeout = Math.max(this.attackAnimationTimeout - 1, 0);
        } else {
            // Stop attack animation
            if (this.attackAnimationState.isStarted()) {
                this.attackAnimationState.stop();
            }

            // Handle idle animation
            if (!this.idleAnimationState.isStarted()) {
                this.idleAnimationState.start(this.tickCount);
                this.idleAnimationTimeout = this.random.nextInt(40);
            } else {
                this.idleAnimationTimeout = Math.max(this.idleAnimationTimeout - 1, 0);
            }
        }
    }
    private boolean targeting() {
        return this.getTarget() != null;  // Checks if the mob has a target
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide) {
            this.setupAnimationStates();
        }
    }
}