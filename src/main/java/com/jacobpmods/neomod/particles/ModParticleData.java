package com.jacobpmods.neomod.particles;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;

public class ModParticleData extends ParticleDescriptionProvider {
    public ModParticleData(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }
    @Override
    protected void addDescriptions() {
        this.sprite(ModParticlesTypes.DRIPPING_BLOOD.get(), ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "dripping_blood"));
        this.sprite(ModParticlesTypes.FALLING_BLOOD.get(), ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "falling_blood"));
        this.spriteSet(ModParticlesTypes.SPLASH.get(), ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "splash"), 4, false);

    }
}
