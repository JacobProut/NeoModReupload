package com.jacobpmods.neomod.entity.custom.throwables;

import com.jacobpmods.neomod.entity.ModEntities;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;

public class ScytheProjectileEntity extends AbstractArrow {
    private float rotation;
    public Vec2 groundOffset;

    public ScytheProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public ScytheProjectileEntity(LivingEntity shooter, Level level) {
        super(ModEntities.THROWABLE_SCYTHE.get(), shooter, level, new ItemStack(ModItems.THROWABLE_SCYTHE.get()), null);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.THROWABLE_SCYTHE.get());
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();

        if (this.getOwner() instanceof Player player) {
            ItemStack scytheStack = new ItemStack(ModItems.THROWABLE_SCYTHE.get());
            player.getInventory().add(scytheStack);
        }
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4); //dmg amount
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.inGround = true;

        if(result.getDirection() == Direction.SOUTH) {
            groundOffset = new Vec2(215f,0f);
        }
        if(result.getDirection() == Direction.NORTH) {
            groundOffset = new Vec2(215f, 180f);
        }
        if(result.getDirection() == Direction.EAST) {
            groundOffset = new Vec2(215f,90f);
        }
        if(result.getDirection() == Direction.WEST) {
            groundOffset = new Vec2(215f,-90f);
        }

        if(result.getDirection() == Direction.DOWN) {
            groundOffset = new Vec2(115f,180f);
        }
        if(result.getDirection() == Direction.UP) {
            groundOffset = new Vec2(285f,180f);
        }
    }
}
