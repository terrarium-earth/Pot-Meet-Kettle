package earth.terrarium.potmeetkettle.common.block.base;

import earth.terrarium.botarium.api.fluid.FluidHooks;
import earth.terrarium.botarium.api.item.SerializbleContainer;
import earth.terrarium.potmeetkettle.common.blockentity.VesselBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A base block entity class representing all cooking vessels.
 * @author <a href="https://github.com/Brittank88">Brittank88</a>
 * @author <a href="https://github.com/AlexNijjar">AlexNijjar</a>
 * @author <a href="https://github.com/ThatGravyBoat">ThatGravyBoat</a>
 */
public class CookingVesselEntityBlockBase extends HorizontalFacingEntityBlockBase implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");

    private final Supplier<BlockEntityType<VesselBlockEntity>> type;

    public CookingVesselEntityBlockBase(BlockBehaviour.Properties properties, Supplier<BlockEntityType<VesselBlockEntity>> type) {
        super(properties);
        this.type = type;
        defaultBlockState().setValue(WATERLOGGED, false);
    }

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return type.get().create(pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (!level.isClientSide && level.getBlockEntity(pos) instanceof VesselBlockEntity vesselBlockEntity) {

            // Picking up the vessel.
            if (player.isShiftKeyDown()) {
                ItemStack itemStack = new ItemStack(this.asItem());
                itemStack.setTag(vesselBlockEntity.getUpdateTag());
                player.getInventory().placeItemBackInInventory(itemStack);

            } else {
                SerializbleContainer itemContainer = vesselBlockEntity.getContainer();

                // Take items out of the vessel.
                if (player.getItemInHand(hand).isEmpty()) {
                    if (itemContainer.hasAnyMatching(Predicate.not(ItemStack::isEmpty))) {
                        for (int i = 0; i < itemContainer.getContainerSize(); i++) {
                            ItemStack itemStack = itemContainer.getItem(i);
                            if (!itemStack.isEmpty()) {
                                player.setItemInHand(hand, itemStack);
                                itemContainer.getItem(i).shrink(1);
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
                                itemContainer.setItem(i, player.getItemInHand(hand));
                                player.getItemInHand(hand).shrink(1);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @SuppressWarnings("deprecation")
    @Override public void onRemove(BlockState state1, Level level, BlockPos pos, BlockState state2, boolean moving) {
        if (!state1.is(state2.getBlock())) {
            if (level.getBlockEntity(pos) instanceof VesselBlockEntity vesselBlockEntity) {
                if (level instanceof ServerLevel) Containers.dropContents(level, pos, vesselBlockEntity);

                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state1, level, pos, state2, moving);
        }
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
    @Override public BlockState updateShape(BlockState state1, Direction direction, BlockState state2, LevelAccessor levelAccessor, BlockPos pos1, BlockPos pos2) {
        if (state1.getValue(WATERLOGGED)) levelAccessor.scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        return super.updateShape(state1, direction, state2, levelAccessor, pos1, pos2);
    }

    /**
     * @param state The block state.
     * @return The {@link FluidState} for this block.
     */
    @SuppressWarnings("deprecation")
    @Override public FluidState getFluidState(BlockState state) {
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
            vesselBlockEntity.getFluidContainer().insertFluid(FluidHooks.newFluidHolder(Fluids.WATER, FluidHooks.buckets(1), null), false);
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
}
