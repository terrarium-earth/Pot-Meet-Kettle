package earth.terrarium.potmeetkettle.common.blockentity;

import earth.terrarium.potmeetkettle.common.blockentity.base.CookingVesselBlockEntityBase;
import earth.terrarium.potmeetkettle.common.registry.PMKBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class WokBlockEntity extends CookingVesselBlockEntityBase {

    public WokBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(PMKBlockEntities.WOK.get(), blockPos, blockState, 3, 1);
    }
}
