package net.captaindude.essentialpedestals.items;

import net.captaindude.essentialpedestals.EssentialPedestals;
import net.captaindude.essentialpedestals.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;

public class ModItemGroups {
    public static void registerItemGroups() {
        EssentialPedestals.LOGGER.info("Registering Item Groups for " + EssentialPedestals.MOD_ID);

        // Adds pedestal to building block group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
            .register(entries -> {
                entries.add(ModBlocks.PEDESTAL);
            });
    }
}
