package com.jacobpmods.neomod.item.custom.throwables;

import com.jacobpmods.neomod.entity.custom.throwables.ScytheProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class ThrowableScythe extends SwordItem {
    private static final int COOLDOWN_TICKS = 100; // 100 = 5 seconds;

    public ThrowableScythe(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);

        // Check if the player is under cooldown for the throwable item
        if (pPlayer.getCooldowns().isOnCooldown(itemstack.getItem())) {
            return InteractionResultHolder.fail(itemstack); // Deny usage if cooldown is active
        }

        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!pLevel.isClientSide) {
            int originalSlot = pPlayer.getInventory().selected;
            ScytheProjectileEntity scytheProjectile = new ScytheProjectileEntity(pPlayer, pLevel, itemstack, originalSlot); // Pass the item stack to the projectile to preserve enchantments
            scytheProjectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 0F);
            pLevel.addFreshEntity(scytheProjectile);
        }

        // Apply cooldown to the player for the thrown item (scythe)
        pPlayer.getCooldowns().addCooldown(itemstack.getItem(), COOLDOWN_TICKS);

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

}
