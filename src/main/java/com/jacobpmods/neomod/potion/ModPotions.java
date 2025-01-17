package com.jacobpmods.neomod.potion;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.effect.ModMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, FirstNeoMod.MOD_ID);

    public static final Holder<Potion> POISON_RESISTANCE_POTION = POTIONS.register("poison_resistance_potion",
            () -> new Potion(new MobEffectInstance(ModMobEffects.POISON_RESISTANCE, 3600, 0))); //Duration, Amplifier
    public static final Holder<Potion> EXTENDED_POISON_RESISTANCE_POTION = POTIONS.register("extended_poison_resistance_potion",
            () -> new Potion(new MobEffectInstance(ModMobEffects.POISON_RESISTANCE, 9600, 0))); //Duration, Amplifier

    public static final Holder<Potion> WATER_VELOCITY_POTION = POTIONS.register("water_velocity_potion",
            () -> new Potion(new MobEffectInstance(ModMobEffects.WATER_VELOCITY, 3600, 1))); //Duration, Amplifier

    public static final Holder<Potion> EXTENDED_WATER_VELOCITY_POTION = POTIONS.register("extended_water_velocity_potion",
            () -> new Potion(new MobEffectInstance(ModMobEffects.WATER_VELOCITY, 9600, 1))); //Duration, Amplifier


    public static void register(IEventBus bus) {
        POTIONS.register(bus);
    }

}
