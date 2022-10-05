package earth.terrarium.potmeetkettle.common.registry;

import earth.terrarium.botarium.api.registry.RegistryHelpers;
import earth.terrarium.botarium.api.registry.RegistryHolder;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import earth.terrarium.potmeetkettle.common.blockentity.PotBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class PMKBlockEntities {

    public static final RegistryHolder<BlockEntityType<?>> BLOCK_ENTITIES = new RegistryHolder<>(Registry.BLOCK_ENTITY_TYPE, PotMeetKettle.MOD_ID);

    private PMKBlockEntities() {}

    public static final Supplier<BlockEntityType<PotBlockEntity>> POT = BLOCK_ENTITIES.register("pot", () ->
            RegistryHelpers.createBlockEntityType(PotBlockEntity::new, PMKBlocks.POT.get())
    );

    public static final Supplier<BlockEntityType<PotBlockEntity>> PAN = BLOCK_ENTITIES.register("pan", () ->
            RegistryHelpers.createBlockEntityType(PotBlockEntity::new, PMKBlocks.PAN.get())
    );

    public static final Supplier<BlockEntityType<PotBlockEntity>> WOK = BLOCK_ENTITIES.register("wok", () ->
            RegistryHelpers.createBlockEntityType(PotBlockEntity::new, PMKBlocks.WOK.get())
    );
}
