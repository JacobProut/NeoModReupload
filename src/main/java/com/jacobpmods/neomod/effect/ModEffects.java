package com.jacobpmods.neomod.effect;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, FirstNeoMod.MOD_ID);

    public static final Holder<MobEffect> POISON_RESISTANCE = MOB_EFFECTS.register("poison_resistance",
            () -> new PoisonResistanceEffect(MobEffectCategory.BENEFICIAL, 0x80B080));


    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }
}
