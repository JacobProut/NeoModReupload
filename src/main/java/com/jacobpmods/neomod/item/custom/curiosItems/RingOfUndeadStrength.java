package com.jacobpmods.neomod.item.custom.curiosItems;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RingOfUndeadStrength extends Item implements ICurioItem {

    public RingOfUndeadStrength() {
        super(new Item.Properties().stacksTo(1).durability(0));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        // Ensure the entity is a player
        if (slotContext.entity() instanceof Player player) {
            // Check if the player doesn't already have the effect
            if (!player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                // Apply Strength I effect (amplifier 0 means level 1)
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 220, 0, true, false));
            }
        }
    }

}
