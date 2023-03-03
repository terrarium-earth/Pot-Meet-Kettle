package earth.terrarium.potmeetkettle.common.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExtendedBlockEntityType<T extends BlockEntity> extends BlockEntityType<T> {

    private final ExtendedBlockEntityFactory<T> factory;

    @SafeVarargs
    public ExtendedBlockEntityType(ExtendedBlockEntityFactory<T> factory, Supplier<? extends Block>... blocks) {
        //noinspection DataFlowIssue
        super((pos, state) -> null, Arrays.stream(blocks).map(Supplier::get).collect(Collectors.toSet()), null);
        this.factory = factory;
    }

    @Nullable
    @Override
    public T create(BlockPos pos, BlockState state) {
        return factory.create(this, pos, state);
    }

    @FunctionalInterface
    public interface ExtendedBlockEntityFactory<T extends BlockEntity> {

        T create(BlockEntityType<T> type, BlockPos pos, BlockState state);
    }
}