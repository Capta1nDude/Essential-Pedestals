package net.captaindude.essentialpedestals.blocks.custom;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.captaindude.essentialpedestals.blocks.entitity.custom.PedestalBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

// With help from Kaupenjoe: https://kaupenjoe.net
public class PedestalBlock extends BlockWithEntity {
    private static final VoxelShape SHAPE =
        Block.createCuboidShape(2, 0, 2, 14, 13, 14); // Defines hitbox shape
    public static final MapCodec<PedestalBlock> CODEC = PedestalBlock.createCodec(PedestalBlock::new); // Defines codec (needed for minecraft's data encoding/decoding)

    public PedestalBlock(Settings settings) {
        super(settings);
    }

    // Returns hitbox shape
    @Override
    protected VoxelShape getOutlineShape(BlockState state, net.minecraft.world.BlockView world, BlockPos pos,
            ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    // Returns render type - required else block is invisible
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    // Called when block changes (e.g. broken)
    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PedestalBlockEntity) {
                ItemScatterer.spawn(world, pos, ((PedestalBlockEntity) blockEntity)); // Drops items contained in inventory, like breaking a chest
                world.updateComparators(pos, this); // Does some stuff with saving (I think)
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    // Called when player uses item on block
    // Takes the item from player's hand and puts it on the pedestal
    // Takes the item from the pedestal and puts it in player's hand
    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
            PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity) {
            // Adds item to pedestal if it's empty
            if (pedestalBlockEntity.isEmpty() && !stack.isEmpty()) {
                pedestalBlockEntity.setStack(0, stack.copyWithCount(1)); // Adds item to pedestal inventory
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                if (!player.isInCreativeMode()) {stack.decrement(1);} // Decrements item

                pedestalBlockEntity.markDirty(); // Does some stuff with saving (I think)
                world.updateListeners(pos, state, state, 0); // Updates block
            } else if (stack.isEmpty() && !player.isSneaking() && !pedestalBlockEntity.isEmpty()) {  // Removes item from pedestal and gives it to player if player's hand is empty and player isn't sneaking
                ItemStack stackOnPedestal = pedestalBlockEntity.getStack(0);
                if (!player.isInCreativeMode()) {player.setStackInHand(Hand.MAIN_HAND, stackOnPedestal);} // Adds item on pedestal to player's hand
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
                pedestalBlockEntity.clear(); // Clears inventory of pedestal

                pedestalBlockEntity.markDirty(); // Does some stuff with saving (I think)
                world.updateListeners(pos, state, state, 0); // Updates block
            } else if (player.isSneaking() && !world.isClient()) {
                player.openHandledScreen(pedestalBlockEntity);
            } else {
                return ItemActionResult.FAIL;
            }
        }

        return ItemActionResult.SUCCESS;
    }

}
