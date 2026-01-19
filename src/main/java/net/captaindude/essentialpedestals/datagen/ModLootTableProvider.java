package net.captaindude.essentialpedestals.datagen;

import java.util.concurrent.CompletableFuture;

import net.captaindude.essentialpedestals.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class ModLootTableProvider extends FabricBlockLootTableProvider{

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        // These blocks drop themselves when broken
        addDrop(ModBlocks.PEDESTAL);
    }

}
