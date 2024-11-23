package com.jacobpmods.neomod.blockItems;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class BloodBoneBerry extends BlockItem {
    public BloodBoneBerry(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            entity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2400, 1));
        }

        return super.finishUsingItem(stack, level, entity);
    }

    // Override this to allow eating when not hungry
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        // Allows consuming the item even if the player is not hungry
        player.startUsingItem(hand);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
