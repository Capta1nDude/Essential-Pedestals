package net.captaindude.essentialpedestals.blocks.entitity;


import net.captaindude.essentialpedestals.EssentialPedestals;
import net.captaindude.essentialpedestals.blocks.ModBlocks;
import net.captaindude.essentialpedestals.blocks.entitity.custom.PedestalBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    // Pedestal
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
    Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        Identifier.of(EssentialPedestals.MOD_ID, "pedestal_be"),
        FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build()
    );

    public static void registerBlockEntities() {
        EssentialPedestals.LOGGER.info("Registering Block Entities for " + EssentialPedestals.MOD_ID);
    }
}
