package earth.terrarium.potmeetkettle.common.block.base;

import earth.terrarium.potmeetkettle.PotMeetKettle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A base class for all blocks that supply heat.
 * @author <a href="https://github.com/Brittank88">Brittank88</a>
 * @author <a href="https://github.com/ThatGravyBoat">ThatGravyBoat</a>
 */
public interface HeatSupplier {

    /** By default, 10 seconds of overcooking before the product is Charred Sludge.  */
    int OVERCOOK_THRESHOLD = 200;

    /** Tag used to mark a block as a heat supplier. */
    TagKey<Block> HEAT_SUPPLIER_TAG = TagKey.create(Registry.BLOCK_REGISTRY, PotMeetKettle.id("heat_supplier"));

    /**
     * @param level The level this block is in.
     * @param pos   The position of this block.
     * @param state The state of this block.
     * @return The amount of heat this block can supply, subtracted from the recipe's cooking time per tick.
     */
    default double getHeat(Level level, BlockPos pos, BlockState state) { return state.is(HEAT_SUPPLIER_TAG) ? 1 : 0; }
}
