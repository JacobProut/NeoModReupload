package com.jacobpmods.neomod.event;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.effect.ModEffects;
import com.jacobpmods.neomod.event.gui.HeartOverrides;
import com.jacobpmods.neomod.item.ModItems;
import com.jacobpmods.neomod.villager.ModVillagers;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.List;

@EventBusSubscriber(modid = FirstNeoMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.TAXIDERMIST.value()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            //trades.get(1) = the first level of villager trades.
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.SHREDDED_LEATHER, 3),
                                                    //Count,     //MaxUses,     //Xp       //PriceMultiplier
                    new ItemStack(Items.LEATHER, 1), 20, 2, 0f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.COIN, 1),
                                                  //Count,     //MaxUses,     //Xp       //PriceMultiplier
                    new ItemStack(Items.LEATHER, 6), 10, 3, 0.05f));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.COIN, 1),
                    //Count,     //MaxUses,     //Xp       //PriceMultiplier
                    new ItemStack(Items.RABBIT_HIDE, 24), 10, 3, 0.05f));
        }

        if(event.getType() == ModVillagers.BANKER.value()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            //trades.get(1) = the first level of villager trades.
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.UNDEAD_BONE, 3),
                                                        //Count,     //MaxUses,     //Xp       //PriceMultiplier
                    new ItemStack(ModItems.COIN.get(), 1), 10, 4, 0.05f
            ));
        }
    }

    //Commented out. Will use once i have items that the wandering villager can sell.
    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event) {
      /*  List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 3),
                                                            //Count,   //MaxUses,    //Xp         //PriceMultiplier
                new ItemStack(ModItems.SPEED_APPLE.get(), 2), 10, 4, 0.05f
        ));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 3),
                                                             //Count,   //MaxUses,    //Xp         //PriceMultiplier
                new ItemStack(ModItems.SPEED_APPLE.get(), 2), 10, 4, 0.05f
        ));*/
    }


    @SubscribeEvent
    public static void onRenderHealthOverlay(RenderGuiLayerEvent.Post event) {
        // Access the player and the GUI rendering context
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player != null && player.hasEffect(MobEffects.POISON) && player.hasEffect(ModEffects.POISON_RESISTANCE)) {
            GuiGraphics guiGraphics = event.getGuiGraphics();
            int screenWidth = minecraft.getWindow().getGuiScaledWidth();
            int screenHeight = minecraft.getWindow().getGuiScaledHeight();
            Gui gui = minecraft.gui;

            // Calculate positions and parameters
            int healthBarX = screenWidth / 2 - 91; // Default health bar X position
            int healthBarY = screenHeight - 39;   // Default health bar Y position
            int health = Mth.ceil(player.getHealth());
            int maxHealth = Mth.ceil(player.getMaxHealth());
            int absorb = Mth.ceil(player.getAbsorptionAmount());
            boolean isHardcore = player.level().getLevelData().isHardcore();
            Gui.HeartType heartType = HeartOverrides.getHeartTypeForPlayer(player);


            HeartOverrides.renderCustomHearts(guiGraphics, gui, player, healthBarX, healthBarY, health, maxHealth, absorb, isHardcore, heartType);
        }
    }

}
