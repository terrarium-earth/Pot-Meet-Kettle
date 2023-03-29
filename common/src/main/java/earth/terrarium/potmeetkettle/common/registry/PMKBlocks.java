package earth.terrarium.potmeetkettle.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import earth.terrarium.potmeetkettle.common.block.base.CookingVesselBasicEntityBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

/**
 * A class that holds all PMK blocks.
 *
 * @author <a href="https://github.com/Brittank88">Brittank88</a>
 * @author <a href="https://github.com/ThatGravyBoat">ThatGravyBoat</a>
 */
public class PMKBlocks {

    public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(Registry.BLOCK, PotMeetKettle.MOD_ID);

    private PMKBlocks() {
    }

    public static final BlockBehaviour.Properties BASE_VESSEL_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).isRedstoneConductor((state, reader, pos) -> false);

    public static final Supplier<CookingVesselBasicEntityBlock> POT = BLOCKS.register("pot", () -> new CookingVesselBasicEntityBlock(BASE_VESSEL_PROPERTIES));
    public static final Supplier<CookingVesselBasicEntityBlock> PAN = BLOCKS.register("pan", () -> new CookingVesselBasicEntityBlock(BASE_VESSEL_PROPERTIES));
    public static final Supplier<CookingVesselBasicEntityBlock> WOK = BLOCKS.register("wok", () -> new CookingVesselBasicEntityBlock(BASE_VESSEL_PROPERTIES));
}
