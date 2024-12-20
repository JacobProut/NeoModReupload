package com.jacobpmods.neomod.particles;

import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SplashParticle;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
public class ModParticleFactories {
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSprite(ModParticlesTypes.DRIPPING_BLOOD.get(), (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed) -> new ModDripParticle.DripHangParticle(level, x, y, z, Fluids.WATER, ModParticlesTypes.FALLING_BLOOD.get()) {
            @Override
            public ParticleRenderType getRenderType() {
                return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
            }
        });
        event.registerSprite(ModParticlesTypes.FALLING_BLOOD.get(), (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed) -> new ModDripParticle.FallAndLandParticle(level, x, y, z, Fluids.WATER, ModParticlesTypes.SPLASH.get()) {
            @Override
            public ParticleRenderType getRenderType() {
                return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
            }
        });
        event.registerSpriteSet(ModParticlesTypes.SPLASH.get(), spriteSet -> (particle, level, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            SplashParticle splashParticle = new SplashParticle(level, x, y, z, xSpeed, ySpeed, zSpeed) {
                @Override
                public ParticleRenderType getRenderType() {
                    return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
                }
            };
            splashParticle.pickSprite(spriteSet);
            return splashParticle;
        });

        event.registerSpriteSet(ModParticlesTypes.AFTERLIFE_TORCH.get(), AfterLifeTorchParticle.Provider::new);
        event.registerSpriteSet(ModParticlesTypes.AFTERLIFE_FIRE_FLAME.get(), FlameParticle.Provider::new);

        }
}
