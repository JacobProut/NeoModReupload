package com.jacobpmods.neomod.particles;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticlesTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, FirstNeoMod.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_BLOOD = PARTICLES.register("blood_particle",
            () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_BLOOD = PARTICLES.register("falling_blood",
            () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPLASH = PARTICLES.register("splash", () -> new SimpleParticleType(false));


    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}