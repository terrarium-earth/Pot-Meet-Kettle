package earth.terrarium.potmeetkettle.common.registry;

import earth.terrarium.botarium.api.registry.RegistryHolder;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import earth.terrarium.potmeetkettle.common.blockentity.VesselBlockEntity;
import earth.terrarium.potmeetkettle.common.util.Lazy;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class PMKBlockEntities {

    public static final RegistryHolder<BlockEntityType<?>> BLOCK_ENTITIES = new RegistryHolder<>(Registry.BLOCK_ENTITY_TYPE, PotMeetKettle.MOD_ID);

    private PMKBlockEntities() {
    }

    public static Lazy<BlockEntityType<VesselBlockEntity>> lazy(String id, ExtendedBlockEntityType.ExtendedBlockEntityFactory<VesselBlockEntity> factory, Supplier<? extends Block> supplier) {
        return Lazy.of(BLOCK_ENTITIES.register(id, () -> new ExtendedBlockEntityType<>(factory, supplier)));
    }

    public static final Lazy<BlockEntityType<VesselBlockEntity>> POT = lazy("pot", VesselBlockEntity.factory(true, 4, 1), PMKBlocks.POT);
    public static final Lazy<BlockEntityType<VesselBlockEntity>> PAN = lazy("pan", VesselBlockEntity.factory(true, 2, 0), PMKBlocks.PAN);
    public static final Lazy<BlockEntityType<VesselBlockEntity>> WOK = lazy("wok", VesselBlockEntity.factory(true, 3, 1), PMKBlocks.WOK);
}
