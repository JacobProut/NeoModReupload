package com.jacobpmods.neomod.item;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.blockItems.BloodBoneBerry;
import com.jacobpmods.neomod.entity.ModEntities;
import com.jacobpmods.neomod.item.custom.FuelItem;
import com.jacobpmods.neomod.item.custom.ModArmorItem;
import com.jacobpmods.neomod.item.custom.curiosItems.RingOfUndeadStrength;
import com.jacobpmods.neomod.item.custom.curiosItems.SoulSplitNecklace;
import com.jacobpmods.neomod.item.custom.food.speedapple;
import com.jacobpmods.neomod.item.custom.igniter.PortalIgniter;
import com.jacobpmods.neomod.item.custom.potions.AgilityPotion;
import com.jacobpmods.neomod.item.custom.potions.NightTimeExplorerPotion;
import com.jacobpmods.neomod.item.custom.potions.SpeedyStrengthPotion;
import com.jacobpmods.neomod.item.custom.throwables.ThrowableScythe;
import com.jacobpmods.neomod.item.custom.tools.NexonAxe;
import com.jacobpmods.neomod.item.custom.tools.NexonPickaxe;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FirstNeoMod.MOD_ID);
    public static final DeferredItem<Item> UNDEAD_BEEF = ITEMS.register("undead_beef",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8).saturationModifier(12.8F).build())));
    public static final DeferredItem<PortalIgniter> FIRE_BALL = ITEMS.register("throwablefireball", PortalIgniter::new);
    public static final DeferredItem<Item> UNDEAD_KEY = ITEMS.register("undead_key", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COIN = ITEMS.register("coin", () -> new Item(new Item.Properties().stacksTo(99)));
    public static final DeferredItem<Item> SHREDDED_LEATHER = ITEMS.register("shredded_leather", () -> new Item(new Item.Properties().stacksTo(99)));

    //Fuel Items
    public static final DeferredItem<Item> SPIRIT_COAL = ITEMS.registerItem("spirit_coal", properties -> new FuelItem(properties, 2400), new Item.Properties());


    public static final DeferredItem<Item> nexon = ITEMS.register("nexon", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HEATED_NEXON = ITEMS.register("nexonheated", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NEXON_INGOT = ITEMS.register("nexoningot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NEXON_REINFORCED_INGOT = ITEMS.register("nexonreinforcedingot", () -> new Item(new Item.Properties()));

    //Tools and weapons
    public static final DeferredItem<Item> NEXON_PICKAXE = ITEMS.register("nexonpickaxe",
            () -> new NexonPickaxe(ModToolTiers.NEXON, new Item.Properties().fireResistant()
                    .attributes(NexonPickaxe.createAttributes(ModToolTiers.NEXON,1.0F, -2.8F))));
    public static final DeferredItem<Item> NEXON_SHOVEL = ITEMS.register("nexonshovel",
            () -> new ShovelItem(ModToolTiers.NEXON, new Item.Properties().fireResistant()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.NEXON,1.5F, -3.0F))));
    public static final DeferredItem<Item> NEXON_HOE = ITEMS.register("nexonhoe",
            () -> new HoeItem(ModToolTiers.NEXON, new Item.Properties().fireResistant()
                    .attributes(HoeItem.createAttributes(ModToolTiers.NEXON,0, -3.0F))));
    public static final DeferredItem<Item> NEXON_SWORD = ITEMS.register("nexonsword",
            () -> new SwordItem(ModToolTiers.NEXON, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.NEXON,5, -2.4F))));
    public static final DeferredItem<Item> NEXON_AXE = ITEMS.register("nexonaxe",
            () -> new NexonAxe(ModToolTiers.NEXON, new Item.Properties().fireResistant()
                    .attributes(NexonAxe.createAttributes(ModToolTiers.NEXON,6, -3.2F))));
    public static final DeferredItem<Item> SCYTHE = ITEMS.register("scythe",
            () -> new ThrowableScythe(ModToolTiers.SCYTHE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.SCYTHE,5, -2.3F))));
    public static final DeferredItem<Item> BONE_SWORD = ITEMS.register("bone_sword",
            () -> new SwordItem(ModToolTiers.BONE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.BONE, 3, -2.3F))));
    public static final DeferredItem<Item> BLOOD_BONE_SWORD = ITEMS.register("blood_bone_sword",
            () -> new SwordItem(ModToolTiers.BONE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.BONE, 4, -2.3F))));

    public static final DeferredItem<Item> GOD_SWORD = ITEMS.register("god_sword",
            () -> new SwordItem(ModToolTiers.GOD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.GOD, 6, -2.2F))));


    //Armor
    public static final DeferredItem<Item> NEXON_HELMET = ITEMS.register("nexonhelmet",
            () -> new ModArmorItem(ModArmorMaterials.NEXON, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().durability(ArmorItem.Type.HELMET.getDurability(42))));
    public static final DeferredItem<Item> NEXON_CHESTPLATE = ITEMS.register("nexonchestplate",
            () -> new ModArmorItem(ModArmorMaterials.NEXON, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(42))));
    public static final DeferredItem<Item> NEXON_LEGGINGS = ITEMS.register("nexonleggings",
            () -> new ModArmorItem(ModArmorMaterials.NEXON, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(42))));
    public static final DeferredItem<Item> NEXON_BOOTS = ITEMS.register("nexonboots",
            () -> new ModArmorItem(ModArmorMaterials.NEXON, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(42))));
    public static final DeferredItem<Item> BONE_HELMET = ITEMS.register("bone_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SKELETAL, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().durability(ArmorItem.Type.HELMET.getDurability(42))));
    public static final DeferredItem<Item> BONE_CHESTPLATE = ITEMS.register("bone_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.SKELETAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(42))));
    public static final DeferredItem<Item> BONE_LEGGINGS = ITEMS.register("bone_leggings",
            () -> new ModArmorItem(ModArmorMaterials.SKELETAL, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(42))));
    public static final DeferredItem<Item> BONE_BOOTS = ITEMS.register("bone_boots",
            () -> new ModArmorItem(ModArmorMaterials.SKELETAL, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(42))));

    public static final DeferredItem<Item> UNDEAD_BONE = ITEMS.register("undead_bone", () -> new Item(new Item.Properties()));


    //rings
    public static final DeferredItem<Item> UNDEAD_STRENGTH_RING = ITEMS.register("undead_strength_ring", RingOfUndeadStrength::new);
    //Necklaces
    public static final DeferredItem<Item> SOUL_SPLIT_NECKLACE = ITEMS.register("soul_split_necklace", SoulSplitNecklace::new);


    //Spawn Eggs
    public static final DeferredItem<Item> SKELETAL_ZOMBIE_SPAWN_EGG = ITEMS.register("skeletal_zombie_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SKELETAL_ZOMBIE, 0xFFFFFF, 0xD3D3D3, new Item.Properties()));
    public static final DeferredItem<Item> SKELETAL_ENDERMAN_SPAWN_EGG = ITEMS.register("skeletal_enderman_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SKELETAL_ENDERMAN, 0xFFFFFF, 0xD3D3D3, new Item.Properties()));
    public static final DeferredItem<Item> SKELETAL_GUARDIAN_SPAWN_EGG = ITEMS.register("skeletal_guardian_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SKELETAL_GUARDIAN, 0xFFFFFF, 0xD3D3D3, new Item.Properties()));
    public static final DeferredItem<Item> SKELETAL_COW_EGG = ITEMS.register("skeletal_cow_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SKELETAL_COW, 0xFFFFFF, 0xD3D3D3, new Item.Properties()));
    public static final DeferredItem<Item> SKELETAL_WOLF_EGG = ITEMS.register("skeletal_wolf_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SKELETAL_WOLF, 0xFFFFFF, 0xD3D3D3, new Item.Properties()));


    //Berries
    public static final DeferredItem<Item> BLOOD_BONE_FRUIT_BERRIES = ITEMS.register("blood_bone_fruit_berries",
            () -> new BloodBoneBerry(ModBlocks.BLOOD_BONE_FRUIT_BUSH.get(), new Item.Properties().food(ModFoodProperties.BLOOD_BONE_FRUIT_BERRY)));

    //Food
    public static final DeferredItem<Item> SPEED_APPLE = ITEMS.register("speedapple", () -> new speedapple(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build())));

    //Custom Combined Potions
    public static final DeferredItem<Item> SPEEDY_STRENGTH_POTION_I = ITEMS.register("speedy_strength_potion_i", () -> new SpeedyStrengthPotion(new Item.Properties()));
    public static final DeferredItem<Item> SPEEDY_STRENGTH_POTION_II = ITEMS.register("speedy_strength_potion_ii", () -> new SpeedyStrengthPotion(new Item.Properties(), true));
    public static final DeferredItem<Item> AGILITY_POTION_I = ITEMS.register("agility_potion_i", () -> new AgilityPotion(new Item.Properties()));
    public static final DeferredItem<Item> AGILITY_POTION_II = ITEMS.register("agility_potion_ii", () -> new AgilityPotion(new Item.Properties(), true));
    public static final DeferredItem<Item> NIGHTTIME_EXPLORER_POTION_I = ITEMS.register("nighttime_explorer_potion_i", () -> new NightTimeExplorerPotion(new Item.Properties()));
    public static final DeferredItem<Item> NIGHTTIME_EXPLORER_POTION_II = ITEMS.register("nighttime_explorer_potion_ii", () -> new NightTimeExplorerPotion(new Item.Properties(), true));

    //Mixer Ingredients
    public static final DeferredItem<Item> GHOSTLY_DUST = ITEMS.register("ghostly_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ENHANCED_GHOSTLY_DUST = ITEMS.register("enhanced_ghostly_dust", () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> SHATTERED_FRAGMENT = ITEMS.register("shattered_fragment", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SKELETAL_BLOOD_SHARD = ITEMS.register("skeletal_blood_shard", () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
