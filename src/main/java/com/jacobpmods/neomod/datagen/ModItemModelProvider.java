package com.jacobpmods.neomod.datagen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.fluid.ModFluids;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }


    public ModItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FirstNeoMod.MOD_ID, exFileHelper);
    }


    @Override
    protected void registerModels() {
        basicItem(ModItems.FIRE_BALL.get());
        basicItem(ModItems.SPEED_APPLE.get());

        //Fluids
        basicItem(ModFluids.POISONED_WATER_BUCKET.get());

        //Nexon
        basicItem(ModItems.nexon.get());
        basicItem(ModItems.HEATED_NEXON.get());
        basicItem(ModItems.NEXON_INGOT.get());
        basicItem(ModItems.NEXON_REINFORCED_INGOT.get());

        //Nexon Tools
        handheldItem(ModItems.NEXON_PICKAXE);
        handheldItem(ModItems.NEXON_HOE);
        handheldItem(ModItems.NEXON_SHOVEL);
        handheldItem(ModItems.NEXON_SWORD);
        handheldItem(ModItems.NEXON_AXE);

        //Nexon Armor
        trimmedArmorItem(ModItems.NEXON_HELMET);
        trimmedArmorItem(ModItems.NEXON_CHESTPLATE);
        trimmedArmorItem(ModItems.NEXON_LEGGINGS);
        trimmedArmorItem(ModItems.NEXON_BOOTS);

        //Saplings
        saplingItem(ModBlocks.GHOSTLY_SAPLING);
        saplingItem(ModBlocks.BLOODY_SAPLING);

        //Spawn Eggs
        withExistingParent(ModItems.SKELETAL_ZOMBIE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SKELETAL_ENDERMAN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        //Flowers
        flowerItem(ModBlocks.OOZING_FLOWER);

        handheldItem(ModItems.UNDEAD_BONE.get());

        //curios worn items
        basicItem(ModItems.UNDEAD_STRENGTH_RING.get());
        basicItem(ModItems.SOUL_SPLIT_NECKLACE.get());

        //Berries
        basicItem(ModItems.BLOOD_BONE_FRUIT_BERRIES.get());

        handheldItem(ModItems.SCYTHE);
        handheldItem(ModItems.BONE_SWORD);
        handheldItem(ModItems.BLOOD_BONE_SWORD);

        basicItem(ModItems.SHATTERED_FRAGMENT.get());
        basicItem(ModItems.SKELETAL_BLOOD_SHARD.get());

        basicItem(ModItems.UNDEAD_KEY.get());

    }

    public void flowerItem(DeferredBlock<Block> block) {
        this.withExistingParent(block.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID,
                        "block/" + block.getId().getPath()));
    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(DeferredItem<Item> itemDeferredItem) {
        final String MOD_ID = FirstNeoMod.MOD_ID; // Change this to your mod id
        if(itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;
                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };
                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);
                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");
                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);
                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID,"block/" + item.getId().getPath()));
    }
    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "item/" + item.getId().getPath()));
    }


}

