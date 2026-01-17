package net.captaindude.essentialpedestals;

import net.captaindude.essentialpedestals.blocks.entitity.ModBlockEntities;
import net.captaindude.essentialpedestals.blocks.entitity.renderer.PedestalBlockEntityRenderer;
import net.captaindude.essentialpedestals.screen.ModScreenHandlers;
import net.captaindude.essentialpedestals.screen.custom.PedestalScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class EssentialPedestalsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);
		HandledScreens.register(ModScreenHandlers.PEDESTAL_SCREEN_HANDLER_TYPE, PedestalScreen::new);
	}
}