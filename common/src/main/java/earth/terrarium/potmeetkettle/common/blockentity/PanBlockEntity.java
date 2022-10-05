package earth.terrarium.potmeetkettle.common.blockentity;

import earth.terrarium.potmeetkettle.common.blockentity.base.CookingVesselBlockEntityBase;
import earth.terrarium.potmeetkettle.common.registry.PMKBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PanBlockEntity extends CookingVesselBlockEntityBase {

    public PanBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(PMKBlockEntities.PAN.get(), blockPos, blockState, 2, 0);
    }
}
