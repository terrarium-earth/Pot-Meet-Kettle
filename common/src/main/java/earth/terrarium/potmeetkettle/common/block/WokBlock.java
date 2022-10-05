package earth.terrarium.potmeetkettle.common.block;

import earth.terrarium.potmeetkettle.common.block.base.CookingVesselEntityBlockBase;
import earth.terrarium.potmeetkettle.common.blockentity.WokBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class WokBlock extends CookingVesselEntityBlockBase {
    public WokBlock(Properties properties) { super(properties); }

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new WokBlockEntity(pos, state); }
}
