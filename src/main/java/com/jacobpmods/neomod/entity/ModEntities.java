package com.jacobpmods.neomod.entity;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.entity.custom.SkeletalEndermanEntity;
import com.jacobpmods.neomod.entity.custom.SkeletalGuardianEntity;
import com.jacobpmods.neomod.entity.custom.SkeletalZombieEntity;
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


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
