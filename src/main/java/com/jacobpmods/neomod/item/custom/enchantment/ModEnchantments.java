package com.jacobpmods.neomod.item.custom.enchantment;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.custom.enchantment.effects.axes.TimberFellerEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.boots.MagmaWalkerEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.bows.DoubleShotEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.hoe.FastHarvestEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.pickaxes.MagmaMineEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.pickaxes.VeinMinerEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.swords.IceBarrageMeleeEnchantmentEffect;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.Enchantments;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> MAGMA_MINE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "magma_mine"));

    public static final ResourceKey<Enchantment> ICE_BARRAGE_MELEE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "ice_barrage_melee"));

    public static final ResourceKey<Enchantment> TIMBER_FELLER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "timber_feller"));

    public static final ResourceKey<Enchantment> VEIN_MINER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "vein_miner"));

    public static final ResourceKey<Enchantment> MAGMA_WALKER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "magma_walker"));

    public static final ResourceKey<Enchantment> DOUBLE_SHOT = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "double_shot"));

    public static final ResourceKey<Enchantment> FAST_HARVEST = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "fast_harvest"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantment = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, MAGMA_MINE, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.PICKAXES),
                items.getOrThrow(ItemTags.PICKAXES), 5, 1,
                Enchantment.dynamicCost(2, 4), Enchantment.dynamicCost(2, 8), 3, EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(HolderSet.direct(enchantment.getOrThrow(Enchantments.SILK_TOUCH)))
                .withEffect(EnchantmentEffectComponents.HIT_BLOCK, new MagmaMineEnchantmentEffect())
        );

        register(context, VEIN_MINER, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.PICKAXES),
                        items.getOrThrow(ItemTags.PICKAXES), 1, 1,
                        Enchantment.dynamicCost(2, 4), Enchantment.dynamicCost(2, 8), 3, EquipmentSlotGroup.MAINHAND))
                .withEffect(EnchantmentEffectComponents.HIT_BLOCK, new VeinMinerEnchantmentEffect())
        );

        register(context, ICE_BARRAGE_MELEE, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORDS), 5, 3,
                        Enchantment.dynamicCost(2, 4), Enchantment.dynamicCost(2, 8), 3, EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(HolderSet.direct(enchantment.getOrThrow(Enchantments.FIRE_ASPECT)))
                        .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM, new IceBarrageMeleeEnchantmentEffect())
        );


        register(context, TIMBER_FELLER, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.AXES),
                        items.getOrThrow(ItemTags.AXES), 5, 1,
                        Enchantment.dynamicCost(2, 4), Enchantment.dynamicCost(2, 8), 3, EquipmentSlotGroup.MAINHAND))
                        .exclusiveWith(enchantment.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE))
                        .withEffect(EnchantmentEffectComponents.HIT_BLOCK, new TimberFellerEnchantmentEffect())
        );

        register(context, MAGMA_WALKER, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE), 1, 1,
                Enchantment.dynamicCost(10, 10), Enchantment.dynamicCost(25, 10), 4, EquipmentSlotGroup.FEET))
                .withEffect(EnchantmentEffectComponents.TICK, new MagmaWalkerEnchantmentEffect())
        );

        register(context, FAST_HARVEST, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.HOES),
                        items.getOrThrow(ItemTags.HOES), 1, 1,
                        Enchantment.dynamicCost(2, 4), Enchantment.dynamicCost(2, 8), 3, EquipmentSlotGroup.MAINHAND))
                .withEffect(EnchantmentEffectComponents.HIT_BLOCK, new FastHarvestEnchantmentEffect())
        );

       /* register(context, DOUBLE_SHOT, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.BOW_ENCHANTABLE), // Ensure this targets bows
                        items.getOrThrow(ItemTags.BOW_ENCHANTABLE), 5, 1,
                        Enchantment.dynamicCost(2, 4), Enchantment.dynamicCost(2, 8), 3, EquipmentSlotGroup.MAINHAND))
                .withEffect(EnchantmentEffectComponents.PROJECTILE_SPAWNED, new DoubleShotEnchantmentEffect()) // Apply effect when a projectile is fired
        );
        */
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}
