package com.jacobpmods.neomod.entity.ai;

import com.jacobpmods.neomod.entity.custom.SkeletalZombieEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;

public class SkeletalZombieAttackGoal extends MeleeAttackGoal {
    private final SkeletalZombieEntity entity;
    private int attackDelay = 14;
    private int ticksUntilNextAttack = 10;
    private boolean shouldCountTillNextAttack = false;



    public SkeletalZombieAttackGoal(PathfinderMob skeletalZombie, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(skeletalZombie, speedModifier, followingTargetEvenIfNotSeen);
        this.entity = ((SkeletalZombieEntity) skeletalZombie);
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 14;
        ticksUntilNextAttack = 10;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy) {
        double distToEnemySqr = this.mob.distanceToSqr(pEnemy); // Calculate the squared distance to the enemy

        // Check if the enemy is within attack range
        if (isEnemyWithinAttackDistance(pEnemy, distToEnemySqr)) {
            shouldCountTillNextAttack = true;

            // If it's time to start the attack animation
            if (isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            // If it's time to perform the attack
            if (isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());

                // Reset attack cooldown and swing the main hand
                resetAttackCooldown();
                this.mob.swing(InteractionHand.MAIN_HAND);

                // Perform the attack on the enemy
                performAttack(pEnemy);
            }
        } else {
            // Reset attack cooldown if the enemy is out of range
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
        return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
    }

    public double getAttackReachSqr(LivingEntity entity) {
        double d = 4.0D;  // Default attack reach squared value, which corresponds to a reach of 2 blocks.
        if (entity instanceof Player) {
            d = 3.0D;  // For player entities, this is often reduced for balancing.
        }
        return d;
    }


    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }


    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pEnemy);
    }

    @Override
    public void tick() {
        super.tick();

        if(shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }

    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }

}

