package com.jacobpmods.neomod.util;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import static net.minecraft.tags.TagEntry.tag;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> NEEDS_NEXON_TOOL = createTag("needs_nexon_tool");
        public static final TagKey<Block> INCORRECT_FOR_NEXON_TOOL = createTag("incorrect_for_netherite_tool");

        public static final TagKey<Block> INCORRECT_FOR_SCYTHE = createTag("incorrect_for_scythe_tool");

        public static final TagKey<Block> INCORRECT_FOR_BONE_TOOL = createTag("incorrect_for_bone_tool");

        public static final TagKey<Block> INCORRECT_FOR_GOD_TOOL = createTag("incorrect_for_bone_tool");
        public static final TagKey<Block> PORTAL_FRAME = createTag("portal_frame");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, name));
        }

    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, name));
        }

    }
}
