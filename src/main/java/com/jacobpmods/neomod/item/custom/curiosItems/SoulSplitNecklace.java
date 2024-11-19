package com.jacobpmods.neomod.item.custom.curiosItems;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class SoulSplitNecklace extends Item implements ICurioItem {

    public SoulSplitNecklace() {
        super(new Item.Properties().stacksTo(1).durability(0));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            // Check if the player has damaged an entity in the last tick
            LivingEntity target = player.getLastHurtMob();
            if (target != null && target.hurtTime > 0) { // `hurtTime` > 0 means the entity was recently hurt
                // Heal the player for half a heart
                player.heal(1.0F);

                // Reset the player's last hit target to prevent continuous healing
                player.setLastHurtMob(null);
            }
        }
    }
}
