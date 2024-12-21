package com.jacobpmods.neomod.event;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.ModItems;
import com.jacobpmods.neomod.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
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
                    new ItemCost(Items.EMERALD, 3),
                                                                //Count,   //MaxUses,    //Xp         //PriceMultiplier
                    new ItemStack(ModItems.SPEED_APPLE.get(), 2), 10, 4, 0.05f
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

}
