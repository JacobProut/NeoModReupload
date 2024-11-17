package com.jacobpmods.neomod.datagen.curios;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.concurrent.CompletableFuture;

public class CuriosDataGen extends CuriosDataProvider {

    public CuriosDataGen(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(FirstNeoMod.MOD_ID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        // Generation code here
        this.createEntities("player")
                .addPlayer()
                .addSlots("back")
                .addSlots("necklace")
                .addSlots("hands")
                .addSlots("bracelet")
                .addSlots("ring");

        /*this.createSlot("ring")
                .size(1)
                .renderToggle(true)
                .dropRule(ICurio.DropRule.ALWAYS_KEEP)
                .addCosmetic(true);*/
    }


}
