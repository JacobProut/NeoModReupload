package com.jacobpmods.neomod.entity.ai;

import com.jacobpmods.neomod.entity.custom.SkeletalZombieEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;

public class SkeletalZombieAttackGoal extends MeleeAttackGoal {
    private final SkeletalZombieEntity entity;
    private int attackDelay = 14;
    private int ticksUntilNextAttack = 10;
    private boolean shouldCountTillNextAttack = false;
    private double lastX, lastZ; // For detecting if stuck
    private int stuckTicks = 0;

    public SkeletalZombieAttackGoal(PathfinderMob skeletalZombie, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(skeletalZombie, speedModifier, followingTargetEvenIfNotSeen);
        this.entity = (SkeletalZombieEntity) skeletalZombie;
        this.lastX = skeletalZombie.getX();
        this.lastZ = skeletalZombie.getZ();
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 14;
        ticksUntilNextAttack = 10;
        lastX = this.mob.getX();
        lastZ = this.mob.getZ();
        stuckTicks = 0;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target) {
        double distToEnemySqr = this.mob.distanceToSqr(target);

        if (isEnemyWithinAttackDistance(target, distToEnemySqr)) {
            shouldCountTillNextAttack = true;

            if (isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            if (isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(target.getX(), target.getEyeY(), target.getZ());
                resetAttackCooldown();
                this.mob.swing(InteractionHand.MAIN_HAND);
                performAttack(target);
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity target, double distanceSq) {
        return distanceSq <= this.getAttackReachSqr(target);
    }

    public double getAttackReachSqr(LivingEntity target) {
        double baseReach = this.mob.getBbWidth() * 2.0 + 2.0; // Adjusted for better diagonal reach
        return baseReach * baseReach;
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

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = this.mob.getTarget();

        if (target != null) {
            double currentX = this.mob.getX();
            double currentZ = this.mob.getZ();

            // Detect if stuck (minimal movement over time)
            if (Math.abs(currentX - lastX) < 0.1 && Math.abs(currentZ - lastZ) < 0.1) {
                stuckTicks++;
                if (stuckTicks > 10) { // Recalculate path if stuck for >10 ticks
                    this.mob.getNavigation().recomputePath();
                    stuckTicks = 0;
                }
            } else {
                stuckTicks = 0;
            }

            lastX = currentX;
            lastZ = currentZ;

            if (shouldCountTillNextAttack) {
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            }
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }

    protected void performAttack(LivingEntity target) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(target);
    }
}
