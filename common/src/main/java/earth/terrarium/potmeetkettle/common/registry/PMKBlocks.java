package earth.terrarium.potmeetkettle.common.registry;

import earth.terrarium.botarium.api.registry.RegistryHolder;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import earth.terrarium.potmeetkettle.common.block.base.CookingVesselEntityBlockBase;
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

    public static final RegistryHolder<Block> BLOCKS = new RegistryHolder<>(Registry.BLOCK, PotMeetKettle.MOD_ID);

    private PMKBlocks() {
    }

    public static final BlockBehaviour.Properties BASE_VESSEL_PROPERTIES = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).isRedstoneConductor((state, reader, pos) -> false);

    public static final Supplier<CookingVesselEntityBlockBase> POT = BLOCKS.register("pot", () -> new CookingVesselEntityBlockBase(BASE_VESSEL_PROPERTIES, PMKBlockEntities.POT));
    public static final Supplier<CookingVesselEntityBlockBase> PAN = BLOCKS.register("pan", () -> new CookingVesselEntityBlockBase(BASE_VESSEL_PROPERTIES, PMKBlockEntities.PAN));
    public static final Supplier<CookingVesselEntityBlockBase> WOK = BLOCKS.register("wok", () -> new CookingVesselEntityBlockBase(BASE_VESSEL_PROPERTIES, PMKBlockEntities.WOK));
}
