package com.jacobpmods.neomod.item.custom.enchantment.effects.helmet;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record NightGlowEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<NightGlowEnchantmentEffect> CODEC = MapCodec.unit(NightGlowEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse itemInUse, Entity entity, Vec3 origin) {
        if (enchantmentLevel > 0) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, true, false));
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
