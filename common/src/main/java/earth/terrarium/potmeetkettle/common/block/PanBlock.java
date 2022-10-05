package earth.terrarium.potmeetkettle.common.block;

import earth.terrarium.potmeetkettle.common.block.base.CookingVesselEntityBlockBase;
import earth.terrarium.potmeetkettle.common.blockentity.PanBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class PanBlock extends CookingVesselEntityBlockBase {
    public PanBlock(Properties properties) { super(properties); }

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new PanBlockEntity(pos, state); }
}
