package com.jacobpmods.neomod.entity;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.entity.custom.*;
import com.jacobpmods.neomod.entity.custom.throwables.ScytheProjectileEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, FirstNeoMod.MOD_ID);


    public static final Supplier<EntityType<SkeletalZombieEntity>> SKELETAL_ZOMBIE =
            ENTITY_TYPES.register("skeletalzombie", () -> EntityType.Builder.of(SkeletalZombieEntity::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("skeletalzombie"));

    public static final Supplier<EntityType<SkeletalEndermanEntity>> SKELETAL_ENDERMAN =
            ENTITY_TYPES.register("skeletalenderman", () -> EntityType.Builder.of(SkeletalEndermanEntity::new, MobCategory.MONSTER)
                    .sized(1f, 3f).build("skeletalenderman"));

    public static final Supplier<EntityType<SkeletalGuardianEntity>> SKELETAL_GUARDIAN =
            ENTITY_TYPES.register("skeletal_guardian", () -> EntityType.Builder.of(SkeletalGuardianEntity::new, MobCategory.MONSTER)
                    .sized(2f, 4.4f).build("skeletal_guardian"));

    //Changed MobCategory to Monster to have them spawn
    public static final Supplier<EntityType<SkeletalCowEntity>> SKELETAL_COW =
            ENTITY_TYPES.register("skeletal_cow", () -> EntityType.Builder.of(SkeletalCowEntity::new, MobCategory.MONSTER)
                    .sized(2f, 1f).build("skeletal_cow"));

    public static final Supplier<EntityType<SkeletalWolfEntity>> SKELETAL_WOLF =
            ENTITY_TYPES.register("skeletal_wolf", () -> EntityType.Builder.of(SkeletalWolfEntity::new, MobCategory.MONSTER)
                    .sized(1.5f, 1f).build("skeletal_wolf"));



    public static final Supplier<EntityType<ScytheProjectileEntity>> THROWABLE_SCYTHE =
            ENTITY_TYPES.register("throwable_scythe", () -> EntityType.Builder.<ScytheProjectileEntity>of(ScytheProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1.15f).build("throwable_scythe"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
