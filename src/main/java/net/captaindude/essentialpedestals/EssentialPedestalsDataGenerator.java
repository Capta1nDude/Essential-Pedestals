package net.captaindude.essentialpedestals;

import net.captaindude.essentialpedestals.datagen.ModBlockTagProvider;
import net.captaindude.essentialpedestals.datagen.ModLootTableProvider;
import net.captaindude.essentialpedestals.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EssentialPedestalsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		// Adds providers for each provider class
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	
	}
}
