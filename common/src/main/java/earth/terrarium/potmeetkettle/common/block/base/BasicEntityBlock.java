package earth.terrarium.potmeetkettle.common.block.base;

import com.teamresourceful.resourcefullib.common.caches.CacheableFunction;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import earth.terrarium.potmeetkettle.common.registry.PMKBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A base block entity class that automates assigning the {@link RenderShape} as the model.
 *
 * @author <a href="https://github.com/Brittank88">Brittank88</a>
 * @author <a href="https://github.com/ThatGravyBoat">ThatGravyBoat</a>
 */
public abstract class BasicEntityBlock extends BaseEntityBlock {

    private static final CacheableFunction<Block, BlockEntityType<?>> BLOCK_TO_ENTITY = new CacheableFunction<>(block ->
            PMKBlockEntityTypes.BLOCK_ENTITY_TYPES
                    .getEntries()
                    .stream()
                    .map(RegistryEntry::get)
                    .filter(type -> type.isValid(block.defaultBlockState()))
                    .findFirst()
                    .orElse(null)
    );
    private BlockEntityType<?> entity;

    /**
     * Creates a new {@link BasicEntityBlock}.
     *
     * @param properties The block properties.
     */
    public BasicEntityBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    /**
     * Creates a new block entity.
     *
     * @param pos   The block position.
     * @param state The block state.
     *
     * @return The block entity.
     */
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        if (entity == null) entity = BLOCK_TO_ENTITY.apply(state.getBlock());
        return entity.create(pos, state);
    }

    /**
     * @param blockState The block state.
     *
     * @return The {@link RenderShape} for this block.
     */
    @Override public @NotNull RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    /**
     * Creates a block entity ticker for this block entity.
     *
     * @param <E>       The block entity type.
     * @param <A>       The block entity type to check.
     * @param typeIn    The block entity type.
     * @param typeCheck The block entity type to check.
     * @param ticker    The block entity ticker.
     *
     * @return The block entity ticker.
     */
    @SuppressWarnings("unchecked")
    @Nullable protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(
            BlockEntityType<A> typeIn,
            BlockEntityType<E> typeCheck,
            BlockEntityTickerSingleton<? super E> ticker
    ) {
        return typeIn == typeCheck ? (pLevel, pPos, pState, pBlockEntity) -> ticker.tick((E) pBlockEntity) : null;
    }

    /**
     * A functional interface for ticking a block entity.
     *
     * @param <T> The type of block entity.
     */
    @FunctionalInterface public interface BlockEntityTickerSingleton<T extends BlockEntity> {
        void tick(T pBlockEntity);
    }
}
