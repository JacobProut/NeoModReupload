package com.jacobpmods.neomod.datagen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.item.ModItems;
import com.jacobpmods.neomod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.tags.ItemTags.*;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, FirstNeoMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS);
                /*.add(ModItems.item.get())*/

        tag(LOGS_THAT_BURN)
                .add(ModBlocks.LOG_GHOSTLY.get().asItem())
                .add(ModBlocks.WOOD_GHOSTLY.get().asItem())
                .add(ModBlocks.STRIPPED_GHOSTLY_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_GHOSTLY_WOOD.get().asItem())
                .add(ModBlocks.LOG_BLOODY.get().asItem());

        tag(PLANKS)
                .add(ModBlocks.GHOSTLY_PLANKS.get().asItem())
                .add(ModBlocks.BLOODY_PLANKS.get().asItem());

        tag(SWORDS)
                .add(ModItems.NEXON_SWORD.get())
                .add(ModItems.BLOOD_BONE_SWORD.get()) //No Normal Bone Sword here because I do not want it to have enchantments
                .add(ModItems.GOD_SWORD.get());
        tag(SWORD_ENCHANTABLE)
                .add(ModItems.NEXON_SWORD.get())
                .add(ModItems.SCYTHE.get())
                .add(ModItems.BLOOD_BONE_SWORD.get())
                .add(ModItems.GOD_SWORD.get());

        tag(PICKAXES)
                .add(ModItems.NEXON_PICKAXE.get());
        tag(MINING_ENCHANTABLE)
                .add(ModItems.NEXON_PICKAXE.get());

        tag(AXES)
                .add(ModItems.NEXON_AXE.get());
        tag(SHOVELS)
                .add(ModItems.NEXON_SHOVEL.get());
        tag(HOES)
                .add(ModItems.NEXON_HOE.get());


        tag(ARMOR_ENCHANTABLE)
                .add(ModItems.NEXON_HELMET.get())
                .add(ModItems.NEXON_CHESTPLATE.get())
                .add(ModItems.NEXON_LEGGINGS.get())
                .add(ModItems.NEXON_BOOTS.get());
                //.add(ModItems.SKELETAL_HELMET.get());

        //Unbreaking 3 Enchantment
        tag(DURABILITY_ENCHANTABLE)
                .add(ModItems.NEXON_HELMET.get())
                .add(ModItems.NEXON_CHESTPLATE.get())
                .add(ModItems.NEXON_LEGGINGS.get())
                .add(ModItems.NEXON_BOOTS.get())
                .add(ModItems.NEXON_SWORD.get())
                .add(ModItems.NEXON_AXE.get())
                .add(ModItems.NEXON_HOE.get())
                .add(ModItems.NEXON_SHOVEL.get())
                .add(ModItems.BLOOD_BONE_SWORD.get())
                .add(ModItems.GOD_SWORD.get());
                //Don't add scythe here. It needs to be repaired with a special item.
                //.add(ModItems.SKELETAL_HELMET.get());


        tag(HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.NEXON_HELMET.get());
                //.add(ModItems.SKELETAL_HELMET.get());

        tag(CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.NEXON_CHESTPLATE.get());

        tag(LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.NEXON_LEGGINGS.get());

        tag(FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.NEXON_BOOTS.get());

        this.tag(TRIMMABLE_ARMOR)
                .add(ModItems.NEXON_HELMET.get())
                .add(ModItems.NEXON_CHESTPLATE.get())
                .add(ModItems.NEXON_LEGGINGS.get())
                .add(ModItems.NEXON_BOOTS.get());

        tag(COALS)
                .add(ModItems.SPIRIT_COAL.get());
    }
}
