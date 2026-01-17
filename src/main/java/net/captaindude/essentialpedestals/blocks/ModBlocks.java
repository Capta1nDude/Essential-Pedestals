package net.captaindude.essentialpedestals.blocks;

import net.captaindude.essentialpedestals.EssentialPedestals;
import net.captaindude.essentialpedestals.blocks.custom.PedestalBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    // Pedestal registration
    public static final Block PEDESTAL = registerBlock("pedestal",
        new PedestalBlock(AbstractBlock.Settings.create().nonOpaque()));

    // Register block to registry (creates the block in the game)
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(EssentialPedestals.MOD_ID, name), block);
    }

    // Register block item to registry (creates the block as an item in the game)
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(EssentialPedestals.MOD_ID, name), 
            new BlockItem(block, new Item.Settings()));
    }


    // Initializer method
    public static void registerModBlocks() {
        EssentialPedestals.LOGGER.info("Registering Mod Blocks for " + EssentialPedestals.MOD_ID);
    }
}
