package com.jacobpmods.neomod.entity.custom;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.entity.ModEntities;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SkeletalCowEntity extends Animal {
    public SkeletalCowEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.FOLLOW_RANGE, 240);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this,1.25, p_335486 -> p_335486.is(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this,1.25));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static boolean checkAnimalSpawnRules(
            EntityType<? extends Animal> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos p_218108_, RandomSource source
    ) {
        // Check if the block below the spawn position is Ghostly_Grass_Block
        return levelAccessor.getBlockState(p_218108_.below()).is(ModBlocks.GHOSTLY_GRASS_BLOCK.get());
    }


    @Override
    protected void dropCustomDeathLoot(ServerLevel serverLevel, DamageSource damageSource, boolean recentlyHit) {
        super.dropCustomDeathLoot(serverLevel, damageSource, recentlyHit);

        int beefCount = 1 + this.random.nextInt(2);  // 1-2
        int shreddedCount = 2 + this.random.nextInt(2);  // 2-3

        // Drop beef
        ItemStack beefStack = new ItemStack(ModItems.UNDEAD_BEEF.get(), beefCount);
        serverLevel.addFreshEntity(new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), beefStack));

        // Drop shredded leather
        ItemStack leatherStack = new ItemStack(ModItems.SHREDDED_LEATHER.get(), shreddedCount);
        serverLevel.addFreshEntity(new ItemEntity(serverLevel, this.getX() + 0.1, this.getY(), this.getZ(), leatherStack));
    }

    @Override
    public InteractionResult mobInteract(Player p_28298_, InteractionHand p_28299_) {
        ItemStack itemstack = p_28298_.getItemInHand(p_28299_);
        if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
            p_28298_.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, p_28298_, Items.MILK_BUCKET.getDefaultInstance());
            p_28298_.setItemInHand(p_28299_, itemstack1);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(p_28298_, p_28299_);
        }
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.WHEAT);
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return ModEntities.SKELETAL_COW.get().create(level);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource p_28306_) {
        return SoundEvents.COW_HURT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }
    @Override
    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }
    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }
}
