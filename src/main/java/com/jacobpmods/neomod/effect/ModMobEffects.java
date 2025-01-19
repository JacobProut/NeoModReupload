package com.jacobpmods.neomod.effect;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, FirstNeoMod.MOD_ID);

    public static final Holder<MobEffect> POISON_RESISTANCE = MOB_EFFECTS.register("poison_resistance",
            () -> new PoisonResistanceEffect(MobEffectCategory.BENEFICIAL, 0x80B080));

    public static final Holder<MobEffect> SWIMMERS_VELOCITY = MOB_EFFECTS.register("swimmers_velocity",
            () -> new SwimmersVelocityEffect(MobEffectCategory.BENEFICIAL, 0x6D7F8B));

    public static final Holder<MobEffect> LAVA_SPEED = MOB_EFFECTS.register("lava_speed",
            () -> new LavaSpeedEffect(MobEffectCategory.BENEFICIAL, 0x625355));

    public static final Holder<MobEffect> NO_LAVA_FOG = MOB_EFFECTS.register("no_lava_fog",
            () -> new NoLavaFogEffect(MobEffectCategory.BENEFICIAL, 0x625355));



    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }
}
