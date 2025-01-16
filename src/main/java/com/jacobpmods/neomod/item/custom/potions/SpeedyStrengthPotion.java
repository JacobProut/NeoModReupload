package com.jacobpmods.neomod.item.custom.potions;

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

public class SpeedyStrengthPotion extends Item {
    public SpeedyStrengthPotion(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(0) // No hunger restored
                .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 0), 1.0f) // 100% chance of Speed I for 3 minute
                .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 0), 1.0f) // 100% chance of Strength I for 3 minute
                .build()));
    }
    public SpeedyStrengthPotion(Properties properties, boolean II) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(0) // No hunger restored
                .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1800, 1), 1.0f) // 100% chance of Speed I for 30 seconds
                .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1800, 1), 1.0f) // 100% chance of Strength I for 3
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
