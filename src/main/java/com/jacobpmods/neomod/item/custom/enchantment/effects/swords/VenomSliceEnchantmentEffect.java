package com.jacobpmods.neomod.item.custom.enchantment.effects.swords;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record VenomSliceEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<VenomSliceEnchantmentEffect> CODEC = MapCodec.unit(VenomSliceEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse itemInUse, Entity entity, Vec3 origin) {
        if (entity instanceof LivingEntity target) {
            if (enchantmentLevel == 1) {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
            }
            if (enchantmentLevel == 2) {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2));
            }

        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
