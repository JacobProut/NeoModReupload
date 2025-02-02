package com.jacobpmods.neomod.item.custom.potions;

import com.jacobpmods.neomod.effect.ModMobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class MagmaMinerPotion extends Item {
    public MagmaMinerPotion(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600, 0), 1.0f) // 100% chance of Fire Resistance for 3 minute
                .effect(() -> new MobEffectInstance(ModMobEffects.LAVA_SPEED, 3600, 0), 1.0f) // 100% chance of Lava Speed for 3 minute
                .effect(() -> new MobEffectInstance(ModMobEffects.NO_LAVA_FOG, 3600, 0), 1.0f) // 100% chance of NO LAVA FOG for 3 minute
                .build()));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK; // Makes the item use the drinking animation
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // Play drinking sound when starting the use
        if (!level.isClientSide) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 0.5f, 1.0f);
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
}
