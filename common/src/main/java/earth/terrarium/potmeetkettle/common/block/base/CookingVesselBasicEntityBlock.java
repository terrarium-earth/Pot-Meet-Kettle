package earth.terrarium.potmeetkettle.common.block.base;

import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import earth.terrarium.botarium.common.item.SerializableContainer;
import earth.terrarium.potmeetkettle.common.blockentity.VesselBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

/**
 * A base block entity class representing all cooking vessels.
 * @author <a href="https://github.com/Brittank88">Brittank88</a>
 * @author <a href="https://github.com/AlexNijjar">AlexNijjar</a>
 * @author <a href="https://github.com/ThatGravyBoat">ThatGravyBoat</a>
 */
public class CookingVesselBasicEntityBlock extends HorizontalFacingBasicEntityBlock implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");

    public CookingVesselBasicEntityBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(
                defaultBlockState()
                        .setValue(WATERLOGGED, false)
        );
    }

    @SuppressWarnings("deprecation")
    @Override public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (!level.isClientSide && level.getBlockEntity(pos) instanceof VesselBlockEntity vesselBlockEntity) {

            // Picking up the vessel.
            if (player.isShiftKeyDown()) {
                getDrops(state, (ServerLevel) level, pos, vesselBlockEntity).forEach(player.getInventory()::placeItemBackInInventory);
                level.removeBlock(pos, false);
            } else {
                SerializableContainer itemContainer = vesselBlockEntity.getContainer();

                // TODO: Rework this logic.

                // Take items out of the vessel.
                if (player.getItemInHand(hand).isEmpty()) {
                    if (itemContainer.hasAnyMatching(Predicate.not(ItemStack::isEmpty))) {
                        for (int i = 0; i < itemContainer.getContainerSize(); i++) {
                            ItemStack itemStack = itemContainer.getItem(i);
                            if (!itemStack.isEmpty()) {
                                player.setItemInHand(hand, itemContainer.getItem(i).copyAndClear());
                                vesselBlockEntity.setChanged();
                                level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
                                break;
                            }
                        }
                    }

                // Placing items into the vessel.
                } else {
                    if (itemContainer.hasAnyMatching(ItemStack::isEmpty)) {
                        for (int i = 0; i < itemContainer.getContainerSize(); i++) {
                            ItemStack itemStack = itemContainer.getItem(i);
                            if (itemStack.isEmpty()) {
                                itemContainer.setItem(i, player.getItemInHand(hand).copyAndClear());
                                vesselBlockEntity.setChanged();
                                level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override public List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof VesselBlockEntity vesselBlockEntity) {
            CompoundTag tag = new CompoundTag();
            tag.put("Items", vesselBlockEntity.getContainer().serialize(new CompoundTag()));
            tag.put("Fluids", vesselBlockEntity.getFluidContainer().serialize(new CompoundTag()));

            ItemStack itemStack = new ItemStack(this.asItem());
            itemStack.setTag(tag);

            return List.of(itemStack);
        }

        return super.getDrops(state, params);
    }

    @SuppressWarnings("deprecation")
    @Override public boolean hasAnalogOutputSignal(BlockState state) { return true; }

    @SuppressWarnings("deprecation")
    @Override public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    /**
     * @param placeContext The block place context.
     * @return The {@link BlockState} for this block when placed.
     */
    @Override @Nullable public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        BlockState stateForPlacement = super.getStateForPlacement(placeContext);
        return stateForPlacement == null ? null : stateForPlacement.setValue(
                WATERLOGGED, placeContext.getLevel().getFluidState(placeContext.getClickedPos()).getType() == Fluids.WATER
        );
    }

    @SuppressWarnings("deprecation")
    @Override public @NotNull BlockState updateShape(BlockState state1, Direction direction, BlockState state2, LevelAccessor levelAccessor, BlockPos pos1, BlockPos pos2) {
        if (state1.getValue(WATERLOGGED)) levelAccessor.scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        return super.updateShape(state1, direction, state2, levelAccessor, pos1, pos2);
    }

    /**
     * @param state The block state.
     * @return The {@link FluidState} for this block.
     */
    @SuppressWarnings("deprecation")
    @Override public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    /**
     * @param level      The level.
     * @param pos        The position.
     * @param state      The block state.
     * @param fluidState The fluid state.
     * @return Whether the block pos can be filled with the fluid.
     */
    @Override public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        boolean canPlace = SimpleWaterloggedBlock.super.placeLiquid(level, pos, state, fluidState);
        if (canPlace && level.getBlockEntity(pos) instanceof VesselBlockEntity vesselBlockEntity)
            vesselBlockEntity.getFluidContainer().insertFluid(FluidHooks.newFluidHolder(Fluids.WATER, FluidHooks.buckets(1D), null), false);
        return canPlace;
    }

    /**
     * Creates the block state definition, adding the {@link #WATERLOGGED} property.
     * @param builder The block state definition builder.
     */
    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (level.getBlockEntity(pos) instanceof VesselBlockEntity vesselBlockEntity) {
            CompoundTag tag = stack.getTag();
            if (tag != null) {
                vesselBlockEntity.getContainer().deserialize(tag.getCompound("Items"));
                vesselBlockEntity.getFluidContainer().deserialize(tag.getCompound("Fluids"));
            }
        }
    }
}
