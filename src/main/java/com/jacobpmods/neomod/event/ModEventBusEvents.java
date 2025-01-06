package com.jacobpmods.neomod.event;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.entity.ModEntities;
import com.jacobpmods.neomod.entity.client.ModModelLayers;
import com.jacobpmods.neomod.entity.client.skeletal.cow.SkeletalCowModel;
import com.jacobpmods.neomod.entity.client.skeletal.enderman.SkeletalEndermanModel;
import com.jacobpmods.neomod.entity.client.skeletal.guardian.SkeletalGuardianModel;
import com.jacobpmods.neomod.entity.client.skeletal.zombie.SkeletalZombieModel;
import com.jacobpmods.neomod.entity.client.throwables.ScytheProjectileModel;
import com.jacobpmods.neomod.entity.custom.SkeletalCowEntity;
import com.jacobpmods.neomod.entity.custom.SkeletalEndermanEntity;
import com.jacobpmods.neomod.entity.custom.SkeletalGuardianEntity;
import com.jacobpmods.neomod.entity.custom.SkeletalZombieEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = FirstNeoMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.SKELETAL_ZOMBIE, SkeletalZombieModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SKELETAL_ENDERMAN, SkeletalEndermanModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SKELETAL_GUARDIAN, SkeletalGuardianModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SKELETAL_COW, SkeletalCowModel::createBodyLayer);
        event.registerLayerDefinition(ScytheProjectileModel.LAYER_LOCATION, ScytheProjectileModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SKELETAL_ZOMBIE.get(), SkeletalZombieEntity.createAttributes().build());
        event.put(ModEntities.SKELETAL_ENDERMAN.get(), SkeletalEndermanEntity.createAttributes().build());
        event.put(ModEntities.SKELETAL_GUARDIAN.get(), SkeletalGuardianEntity.createAttributes().build());
        event.put(ModEntities.SKELETAL_COW.get(), SkeletalCowEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        //                                  * Hostile Mobs *
        event.register(ModEntities.SKELETAL_ZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.SKELETAL_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        /*event.register(ModEntities.SKELETAL_GUARDIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);*/

        //                                 * Friendly Mobs *
        //Look at Animal:checkAnimalSpawnRules for animals that'll spawn in a light dimension(can create different versions for checkSpawnRules in the respected entity classes
        event.register(ModEntities.SKELETAL_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SkeletalCowEntity::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

    }

}

