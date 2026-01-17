package net.captaindude.essentialpedestals.screen;

import net.captaindude.essentialpedestals.EssentialPedestals;
import net.captaindude.essentialpedestals.screen.custom.PedestalScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;


public class ModScreenHandlers {
    public static final ScreenHandlerType<PedestalScreenHandler> PEDESTAL_SCREEN_HANDLER_TYPE =
        Registry.register(Registries.SCREEN_HANDLER, Identifier.of(EssentialPedestals.MOD_ID, "pedstal_screen_handler"),
            new ExtendedScreenHandlerType<>(PedestalScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        EssentialPedestals.LOGGER.info("Registering screen handlers for " + EssentialPedestals.MOD_ID);
    }
}
