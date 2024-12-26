package com.jacobpmods.neomod.block;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.custom.ModFlammableRotatedPillarBlock;
import com.jacobpmods.neomod.block.custom.ModFlowerBlock;
import com.jacobpmods.neomod.block.custom.ModSaplingBlock;
import com.jacobpmods.neomod.block.custom.blocks.villagerJobBlocks.BankChestBlock;
import com.jacobpmods.neomod.block.entity.blockentities.EnhancerBlock;
import com.jacobpmods.neomod.block.entity.blockentities.PedestalBlock;
import com.jacobpmods.neomod.block.custom.blocks.*;
import com.jacobpmods.neomod.block.custom.portal.GhostlyPortalBlock;
import com.jacobpmods.neomod.block.terrainblocks.AfterlifeGrassBlock;
import com.jacobpmods.neomod.block.terrainblocks.BloodyGrassBlock;
import com.jacobpmods.neomod.block.terrainblocks.GhostlyBlock;
import com.jacobpmods.neomod.block.terrainblocks.GhostlyDirtBlock;
import com.jacobpmods.neomod.item.ModItems;
import com.jacobpmods.neomod.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FirstNeoMod.MOD_ID);

    public static final DeferredBlock<Block> OTHERWORLDLY_CRAFTING_TABLE = registerBlock("otherworldly_crafting_table", () -> new OtherwordlyCraftingTable(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));

    //Villager Job Blocks
    public static final DeferredBlock<Block> TAXIDERMY_TABLE = registerBlock("taxidermy_table", () ->new Block(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> BANK_CHEST = registerBlock("bank_chest", () -> new BankChestBlock(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.WOOD).noOcclusion()));

    //Light Blocks
    public static final DeferredBlock<Block> AFTERLIFE_TORCH = register("afterlife_torch", () -> new AfterlifeTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH)));
    public static final DeferredBlock<Block> AFTERLIFE_WALL_TORCH = BLOCKS.register("afterlife_wall_torch", () -> new AfterlifeWallTorchBlock(Block.Properties.ofFullCopy(Blocks.WALL_TORCH)));

    //Ores and Ore Blocks
    public static final DeferredBlock<Block> AFTERLIFE_IRON_ORE_BLOCK = registerBlock("afterlife_iron_ore_block", () ->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));
    public static final DeferredBlock<Block> AFTERLIFE_GOLD_ORE_BLOCK = registerBlock("afterlife_gold_ore_block", () ->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE)
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.NETHER_GOLD_ORE)));
    public static final DeferredBlock<Block> AFTERLIFE_DIAMOND_ORE_BLOCK = registerBlock("afterlife_diamond_ore_block", () ->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> AFTERLIFE_REDSTONE_ORE_BLOCK = registerBlock("afterlife_redstone_ore_block", () ->new Block(BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.NETHER_GOLD_ORE)));
    public static final DeferredBlock<Block> NEXON_BLOCK = registerBlock("nexon_block", () ->new Block(BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));
    public static final DeferredBlock<Block> NEXON_ORE_BLOCK = registerBlock("nexon_ore_block", () ->new Block(BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));

    public static final DeferredBlock<Block> SHATTERED_FRAGMENT_ORE_BLOCK = registerBlock("shattered_fragment_ore_block", () ->new Block(BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));
    public static final DeferredBlock<Block> SHATTERED_FRAGMENT_BLOCK = registerBlock("shattered_fragment_block", () ->new Block(BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));

    public static final DeferredBlock<Block> SPIRIT_COAL_ORE_BLOCK = registerBlock("spirit_coal_ore_block", () ->new Block(BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> SPIRIT_COAL_BLOCK = registerBlock("spirit_coal_block", () ->new Block(BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));



    //Grass & Dirt Blocks
    public static final DeferredBlock<Block> GHOSTLY_GRASS_BLOCK = registerBlock("ghostly_grass_block", GhostlyBlock::new);
    public static final DeferredBlock<Block> BLOODY_GRASS_BLOCK = registerBlock("bloody_grass_block", BloodyGrassBlock::new);
    public static final DeferredBlock<Block> AFTERLIFE_GRASS_BLOCK = registerBlock("afterlife_grass_block", AfterlifeGrassBlock::new);
    public static final DeferredBlock<Block> GHOSTLY_DIRT = registerBlock("ghostly_dirt", GhostlyDirtBlock::new);
    public static final DeferredBlock<Block> AFTERLIFE_DIRT = registerBlock("afterlife_dirt", GhostlyDirtBlock::new);


   //Stone Related
    public static final DeferredBlock<Block> GHOSTLY_STONE = registerBlock("ghostly_stone", () ->new Block(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONE_STAIRS = registerBlock("ghostly_stone_stairs", () ->new StairBlock(ModBlocks.GHOSTLY_STONE.get().defaultBlockState(), BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONE_SLAB = registerBlock("ghostly_stone_slab", () ->new SlabBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONE_FENCE = registerBlock("ghostly_stone_fence", () -> new FenceBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONE_FENCE_GATE = registerBlock("ghostly_stone_fence_gate", () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONE_WALL = registerBlock("ghostly_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONE_PRESSURE_PLATE = registerBlock("ghostly_stone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONE_BUTTON = registerBlock("ghostly_stone_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noCollission()));
    public static final DeferredBlock<Block> GHOSTLY_COBBLESTONE = registerBlock("ghostly_cobblestone", () ->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_COBBLESTONE_STAIRS = registerBlock("ghostly_cobblestone_stairs", () ->new StairBlock(ModBlocks.GHOSTLY_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_COBBLESTONE_SLAB = registerBlock("ghostly_cobblestone_slab", () ->new SlabBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_COBBLESTONE_FENCE = registerBlock("ghostly_cobblestone_fence", () -> new FenceBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_COBBLESTONE_FENCE_GATE = registerBlock("ghostly_cobblestone_fence_gate", () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_COBBLESTONE_WALL = registerBlock("ghostly_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_COBBLESTONE_PRESSURE_PLATE = registerBlock("ghostly_cobblestone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_COBBLESTONE_BUTTON = registerBlock("ghostly_cobblestone_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noCollission()));
    public static final DeferredBlock<Block> GHOSTLY_STONEBRICKS = registerBlock("ghostly_stone_bricks", () ->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONEBRICK_STAIRS = registerBlock("ghostly_stone_brick_stairs", () ->new StairBlock(ModBlocks.GHOSTLY_STONEBRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONEBRICK_SLAB = registerBlock("ghostly_stone_brick_slab", () ->new SlabBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONEBRICK_FENCE = registerBlock("ghostly_stone_brick_fence", () -> new FenceBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONEBRICK_FENCE_GATE = registerBlock("ghostly_stone_brick_fence_gate", () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONEBRICK_WALL = registerBlock("ghostly_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONEBRICK_PRESSURE_PLATE = registerBlock("ghostly_stone_brick_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GHOSTLY_STONEBRICK_BUTTON = registerBlock("ghostly_stone_brick_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noCollission()));


    //Bone Blocks
    public static final DeferredBlock<Block> BONE_BRICK = registerBlock("bone_brick", () ->new Block(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BONE_BRICK_STAIRS = registerBlock("bone_brick_stairs", () ->new StairBlock(ModBlocks.BONE_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BONE_BRICK_SLAB = registerBlock("bone_brick_slab", () ->new SlabBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BONE_BRICK_FENCE = registerBlock("bone_brick_fence", () -> new FenceBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BONE_BRICK_FENCE_GATE = registerBlock("bone_brick_fence_gate", () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BONE_BRICK_WALL = registerBlock("bone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BONE_BRICK_PRESSURE_PLATE = registerBlock("bone_brick_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BONE_BRICK_BUTTON = registerBlock("bone_brick_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noCollission()));


    //Ghostly Wood and Terrain items
    public static final DeferredBlock<Block> LOG_GHOSTLY = registerBlock("log_ghostly", () ->new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> STRIPPED_GHOSTLY_LOG = registerBlock("stripped_ghostly_log", () ->new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            .strength(1f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> WOOD_GHOSTLY = registerBlock("wood_ghostly", () ->new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> STRIPPED_GHOSTLY_WOOD = registerBlock("stripped_ghostly_wood", () ->new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            .strength(1f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> GHOSTLY_PLANKS = registerBlock("planks_ghostly", () ->new Block(BlockBehaviour.Properties.of()
            .strength(1f).sound(SoundType.WOOD)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_PLANK_STAIRS = registerBlock("ghostly_plank_stairs", () ->new StairBlock(ModBlocks.GHOSTLY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD))
    {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_PLANK_SLAB = registerBlock("ghostly_plank_slab", () ->new SlabBlock(BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_PLANK_FENCE = registerBlock("ghostly_plank_fence", () -> new FenceBlock(BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_PLANK_FENCE_GATE = registerBlock("ghostly_plank_fence_gate", () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD))
    {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_PLANK_WALL = registerBlock("ghostly_plank_wall", () -> new WallBlock(BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD))
    {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_PLANK_PRESSURE_PLATE = registerBlock("ghostly_plank_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD))
    {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_PLANK_BUTTON = registerBlock("ghostly_plank_button", () -> new ButtonBlock(BlockSetType.OAK, 20, BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD).noCollission())
    {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_DOOR = registerBlock("ghostly_door", () -> new DoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.of()
            .strength(2f).noOcclusion()){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_TRAPDOOR = registerBlock("ghostly_trapdoor", () -> new TrapDoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.of()
            .strength(2f).noOcclusion()){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_LEAVES = registerBlock("ghostly_leaves", () ->new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            .strength(0.2f).sound(SoundType.CHERRY_LEAVES)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 60;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 30;
        }
    });
    public static final DeferredBlock<Block> GHOSTLY_SAPLING = registerBlock("ghostly_sapling",
            () -> new ModSaplingBlock(ModTreeGrowers.GHOSTLY, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SAPLING)
            .strength(0.2f).sound(SoundType.CHERRY_SAPLING),  ModBlocks.GHOSTLY_GRASS_BLOCK.get(), ModBlocks.GHOSTLY_DIRT.get(), Blocks.GRASS_BLOCK, Blocks.DIRT));



    //Bloody Wood and Terrain items
    public static final DeferredBlock<Block> LOG_BLOODY = registerBlock("log_bloody", () ->new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> BLOODY_PLANKS = registerBlock("planks_bloody", () ->new Block(BlockBehaviour.Properties.of()
            .strength(1f).sound(SoundType.WOOD)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> BLOODY_PLANK_STAIRS = registerBlock("bloody_plank_stairs", () ->new StairBlock(ModBlocks.BLOODY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> BLOODY_PLANK_SLAB = registerBlock("bloody_plank_slab", () ->new SlabBlock(BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> BLOODY_PLANK_FENCE = registerBlock("bloody_plank_fence", () -> new FenceBlock(BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> BLOODY_PLANK_FENCE_GATE = registerBlock("bloody_plank_fence_gate", () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> BLOODY_PLANK_WALL = registerBlock("bloody_plank_wall", () -> new WallBlock(BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> BLOODY_PLANK_PRESSURE_PLATE = registerBlock("bloody_plank_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.STONE)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> BLOODY_PLANK_BUTTON = registerBlock("bloody_plank_button", () -> new ButtonBlock(BlockSetType.OAK, 20, BlockBehaviour.Properties.of()
            .strength(2f).sound(SoundType.STONE).noCollission()){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> BLOODY_DOOR = registerBlock("bloody_door", () -> new DoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.of()
            .strength(2f).noOcclusion()){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> BLOODY_TRAPDOOR = registerBlock("bloody_trapdoor", () -> new TrapDoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.of()
            .strength(2f).noOcclusion()){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final DeferredBlock<Block> BLOODY_LEAVES = registerBlock("bloody_leaves", () ->new BloodyLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            .strength(0.2f).sound(SoundType.CHERRY_LEAVES)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 60;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 30;
        }
    });
    public static final DeferredBlock<Block> BLOODY_SAPLING = registerBlock("bloody_sapling",
            () -> new ModSaplingBlock(ModTreeGrowers.BLOODY, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SAPLING)
                    .strength(0.2f).sound(SoundType.CHERRY_SAPLING), ModBlocks.BLOODY_GRASS_BLOCK.get(), ModBlocks.GHOSTLY_GRASS_BLOCK.get(), ModBlocks.GHOSTLY_DIRT.get(), Blocks.GRASS_BLOCK, Blocks.DIRT));
    public static final DeferredBlock<Block> BLOODY_VINE = registerBlock("bloody_vine", () -> new BloodyVine(
            BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).noOcclusion().replaceable().noCollission()
                    .randomTicks().strength(0.2F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY)));


    public static final DeferredBlock<SkullNBones> SKULL_N_BONES = registerBlock("skull_n_bones", () ->new SkullNBones(BlockBehaviour.Properties.of()
            .strength(0.2f).instabreak().noCollission().sound(SoundType.STONE)));


    public static final DeferredBlock<Block> GHOSTLY_WEB = registerBlock("ghostly_web", () ->new GhostlyWeb(BlockBehaviour.Properties.ofFullCopy(Blocks.COBWEB)
            .strength(4.0f).noOcclusion().noCollission().forceSolidOn().requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY).sound(SoundType.COBWEB)));


    //Flowers and Bushes
    public static final DeferredBlock<Block> OOZING_FLOWER = registerBlock("oozing_flower",
            () -> new ModFlowerBlock(MobEffects.HARM, 2, BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM)));
    public static final DeferredBlock<Block> BLOOD_BONE_FRUIT_BUSH = BLOCKS.register("blood_bone_blossom_bush",
            () -> new BloodBoneBlossomBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));









    //Block Entities
    public static final DeferredBlock<Block> PEDESTAL = registerBlock("pedestal", () ->new PedestalBlock(BlockBehaviour.Properties.of()
            .strength(-1.0F, Float.MAX_VALUE).destroyTime(100000).noOcclusion().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ENHANCER = registerBlock("enhancer",
            () -> new EnhancerBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));


    //Portal Blocks
    public static final DeferredBlock<Block> GHOSTLY_PORTAL_BLOCK = registerBlock("ghostly_portal_block", () ->new GhostlyPortalBlock(BlockBehaviour.Properties.of()
            .strength(7f).destroyTime(100000).sound(SoundType.GLASS)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    //used for torch
    private static <T extends Block> DeferredBlock<T> baseRegister(String name, Supplier<? extends T> block, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
        DeferredBlock<T> register = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, item.apply(register));
        return register;
    }
    private static <B extends Block> DeferredBlock<B> register(String name, Supplier<B> block) {
        return baseRegister(name, block, ModBlocks::registerBlockItem);
    }
    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final DeferredBlock<T> deferredBlock) {
        return () -> {
            DeferredBlock<T> block = Objects.requireNonNull(deferredBlock);
            if (block == AFTERLIFE_TORCH) {
                return new StandingAndWallBlockItem(AFTERLIFE_TORCH.get(), AFTERLIFE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN);
            } else {
                return new BlockItem(block.get(), new Item.Properties());
            }
        };
    }
}
