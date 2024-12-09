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
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
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


    private static final float BREAK_DOOR_CHANCE = 0.1F;
    private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = p_34284_ -> p_34284_ == Difficulty.HARD;
    private final BreakDoorGoal breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
    private boolean canBreakDoors;

    public SkeletalZombieEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
       // this.breakDoorGoal = breakDoorGoal;
        // this.breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new ZombieAttackTurtleEggGoal(this, 1.0, 3));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new SkeletalZombieAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        //this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[]{ZombifiedPiglin.class}));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createLivingAttributes()
                .add(Attributes.FOLLOW_RANGE,35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 4.5)
                .add(Attributes.MAX_HEALTH, 25);

    }


    //          DOOR STUFF
    public boolean canBreakDoors() {
        return this.canBreakDoors;
    }
    public void setCanBreakDoors(boolean canBreakDoors) {
        if (this.supportsBreakDoorGoal() && GoalUtils.hasGroundPathNavigation(this)) {
            if (this.canBreakDoors != canBreakDoors) {
                this.canBreakDoors = canBreakDoors;
                ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(canBreakDoors);
                if (canBreakDoors) {
                    this.goalSelector.addGoal(1, this.breakDoorGoal);
                } else {
                    this.goalSelector.removeGoal(this.breakDoorGoal);
                }
            }
        } else if (this.canBreakDoors) {
            this.goalSelector.removeGoal(this.breakDoorGoal);
            this.canBreakDoors = false;
        }
    }
    protected boolean supportsBreakDoorGoal() {
        return true;
    }



    @Override
    public void addAdditionalSaveData(CompoundTag p_34319_) {
        super.addAdditionalSaveData(p_34319_);
        p_34319_.putBoolean("IsBaby", this.isBaby());
        p_34319_.putBoolean("CanBreakDoors", this.canBreakDoors());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_34305_) {
        super.readAdditionalSaveData(p_34305_);
        this.setBaby(p_34305_.getBoolean("IsBaby"));
        this.setCanBreakDoors(p_34305_.getBoolean("CanBreakDoors"));
    }


    static class ZombieAttackTurtleEggGoal extends RemoveBlockGoal {
        ZombieAttackTurtleEggGoal(PathfinderMob mob, double speedModifier, int verticalSearchRange) {
            super(Blocks.TURTLE_EGG, mob, speedModifier, verticalSearchRange);
        }

        public void playDestroyProgressSound(LevelAccessor level, BlockPos pos) {
           // level.playSound((Player)null, pos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundSource.HOSTILE, 0.5F, 0.9F + Zombie.this.random.nextFloat() * 0.2F);
        }

        public void playBreakSound(Level level, BlockPos pos) {
            level.playSound((Player)null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
        }

        public double acceptedDistance() {
            return 1.14;
        }
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
        // If attacking, reset idle animation and force attack animation
        if (this.isAttacking()) {
            // Stop idle animation if it's playing and start attack animation
            if (this.idleAnimationState.isStarted()) {
                this.idleAnimationState.stop();
            }

            if (!this.attackAnimationState.isStarted()) {
                this.attackAnimationState.start(this.tickCount);  // Start the attack animation
                this.attackAnimationTimeout = 24;  // Reset timeout for attack animation
            }

            // Countdown attack animation timeout if necessary
            if (this.attackAnimationTimeout > 0) {
                --this.attackAnimationTimeout;
            }

            this.setAttacking(true);  // Ensure attacking state is set
        } else {
            // If not attacking, reset attack animation
            if (this.attackAnimationState.isStarted()) {
                this.attackAnimationState.stop();
            }

            // Only start idle animation if not attacking and mob is not targeting
            if (!this.isAttacking() && !this.targeting()) {
                if (this.idleAnimationTimeout <= 0) {
                    this.idleAnimationTimeout = this.random.nextInt(40);  // Reset idle animation timeout
                    this.idleAnimationState.start(this.tickCount);  // Start idle animation
                } else {
                    --this.idleAnimationTimeout;  // Countdown idle animation timeout
                }
            }

            this.setAttacking(false);  // Ensure attacking state is reset
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
