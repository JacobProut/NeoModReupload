package com.jacobpmods.neomod.item.custom.enchantment;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.custom.enchantment.effects.axes.TimberFellerEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.boots.MagmaWalkerEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.pickaxes.MagmaMineEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.pickaxes.VeinMinerEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.swords.IceBarrageMeleeEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, FirstNeoMod.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> MAGMA_MINE =
            registerEnchantmentEffect("magma_mine", MagmaMineEnchantmentEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> ICE_BARRAGE_MELEE =
            registerEnchantmentEffect("ice_barrage_melee", IceBarrageMeleeEnchantmentEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> TIMBER_FELLER =
            registerEnchantmentEffect("timber_feller", TimberFellerEnchantmentEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> VEIN_MINER =
            registerEnchantmentEffect("vein_miner", VeinMinerEnchantmentEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> MAGMA_WALKER =
            registerEnchantmentEffect("magma_walker", MagmaWalkerEnchantmentEffect.CODEC);


    private static Supplier<MapCodec<? extends EnchantmentEntityEffect>> registerEnchantmentEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return ENTITY_ENCHANTMENT_EFFECTS.register(name, () -> codec);
    }

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
