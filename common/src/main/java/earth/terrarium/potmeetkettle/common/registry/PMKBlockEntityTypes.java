package earth.terrarium.potmeetkettle.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import earth.terrarium.potmeetkettle.common.blockentity.VesselBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class PMKBlockEntityTypes {
    public static final ResourcefulRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = ResourcefulRegistries.create(Registry.BLOCK_ENTITY_TYPE, PotMeetKettle.MOD_ID);

    private PMKBlockEntityTypes() {
    }

    public static Supplier<BlockEntityType<VesselBlockEntity>> register(String id, ExtendedBlockEntityType.ExtendedBlockEntityFactory<VesselBlockEntity> factory, Supplier<? extends Block> supplier) {
        return BLOCK_ENTITY_TYPES.register(id, () -> new ExtendedBlockEntityType<>(factory, supplier));
    }

    public static final Supplier<BlockEntityType<VesselBlockEntity>> POT = register("pot", VesselBlockEntity.factory(true, 4, 1), PMKBlocks.POT);
    public static final Supplier<BlockEntityType<VesselBlockEntity>> PAN = register("pan", VesselBlockEntity.factory(true, 2, 0), PMKBlocks.PAN);
    public static final Supplier<BlockEntityType<VesselBlockEntity>> WOK = register("wok", VesselBlockEntity.factory(true, 3, 1), PMKBlocks.WOK);
}
