package earth.terrarium.potmeetkettle.common.block.base;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

/**
 * An extension of {@link EntityBlockBase} that adds a {@link HorizontalDirectionalBlock horizontal facing} property.
 * @author <a href="https://github.com/Brittank88">Brittank88</a>
 * @author <a href="https://github.com/ThatGravyBoat">ThatGravyBoat</a>
 */
public abstract class HorizontalFacingEntityBlockBase extends EntityBlockBase {

    /** A directional property defining which way the block is currently facing. */
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    /**
     * Creates a new {@link HorizontalFacingEntityBlockBase}.
     * @param properties The block properties.
     */
    public HorizontalFacingEntityBlockBase(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    /**
     * Gets the {@link BlockState} for this block when placed.
     * @param placeContext The block place context.
     * @return The block state.
     */
    @Override public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        return defaultBlockState().setValue(FACING, placeContext.getHorizontalDirection().getOpposite());
    }

    /**
     * Rotates the block.
     * @param state    The block state.
     * @param rotation The rotation type.
     * @return The rotated block state.
     */
    @SuppressWarnings("deprecation")
    @Override public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    /**
     * Mirrors the block.
     * @param state  The block state.
     * @param mirror The mirroring type.
     * @return The mirrored block state.
     */
    @SuppressWarnings("deprecation")
    @Override public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    /**
     * Creates the block state definition, adding the {@link #FACING} property.
     * @param builder The block state definition builder.
     */
    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(FACING); }
}
