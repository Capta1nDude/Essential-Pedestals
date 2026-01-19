package net.captaindude.essentialpedestals.datagen;

import java.util.concurrent.CompletableFuture;

import net.captaindude.essentialpedestals.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class ModRecipeProvider extends FabricRecipeProvider{

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return "Pedestal Recipe";
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.MISC, ModBlocks.PEDESTAL)
                       .pattern("SSS")
                       .pattern(" B ")
                       .pattern("BBB")
                       .input('B', Blocks.STONE)
                       .input('S', Blocks.SMOOTH_STONE)
                       .criterion(hasItem(Blocks.STONE), conditionsFromItem(Blocks.STONE))
                       .offerTo(exporter);
            }
        };
    }
    
}
