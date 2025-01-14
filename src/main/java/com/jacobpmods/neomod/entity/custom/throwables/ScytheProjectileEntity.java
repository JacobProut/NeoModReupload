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
    private ItemStack scytheStack; // Store the ItemStack with enchantments
    private int originalSlot = -1; // Store the original inventory slot



    public ScytheProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public ScytheProjectileEntity(LivingEntity shooter, Level level, ItemStack scytheStack, int originalSlot) {
        super(ModEntities.THROWABLE_SCYTHE.get(), shooter, level, scytheStack, null);
        this.scytheStack = scytheStack.copy();  // Copy to ensure enchantments are maintained
        this.originalSlot = originalSlot;  // Store the original slot
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.SCYTHE.get());
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

        // If the owner is a player, we return the scythe with enchantments to the original slot
        if (this.getOwner() instanceof Player player) {
            if (scytheStack != null && originalSlot != -1) {
                // Check if the original slot is already occupied
                ItemStack itemInSlot = player.getInventory().getItem(originalSlot);
                if (!itemInSlot.isEmpty()) {
                    // If the slot is occupied, find the next available slot
                    int nextAvailableSlot = findNextAvailableSlot(player);
                    if (nextAvailableSlot != -1) {
                        // Move the item from the original slot to the next available slot
                        player.getInventory().setItem(nextAvailableSlot, itemInSlot);
                    } else {
                        // If no slot is available, drop the item
                        player.drop(itemInSlot, false);
                    }
                }

                // Place the scythe back into the original slot
                player.getInventory().setItem(originalSlot, scytheStack.copy());  // Put the item back in the original slot
            }
        }

        // Damage the entity
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4); // Damage amount
    }
    // Method to find the next available slot in the player's inventory
    private int findNextAvailableSlot(Player player) {
        for (int i = 0; i < 36; i++) {
            if (player.getInventory().getItem(i).isEmpty()) {
                return i;  // Return the first available slot
            }
        }
        return -1;  // No available slot
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
