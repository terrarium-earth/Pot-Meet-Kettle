package earth.terrarium.potmeetkettle.common.block;

import earth.terrarium.potmeetkettle.common.block.base.CookingVesselEntityBlockBase;
import earth.terrarium.potmeetkettle.common.blockentity.PotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class PotBlock extends CookingVesselEntityBlockBase {
    public PotBlock(Properties properties) { super(properties); }

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new PotBlockEntity(pos, state); }
}
