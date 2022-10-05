package earth.terrarium.potmeetkettle.common.registry;

import earth.terrarium.botarium.api.registry.RegistryHolder;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import earth.terrarium.potmeetkettle.common.block.PotBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PMKBlocks {

    public static final RegistryHolder<Block> BLOCKS = new RegistryHolder<>(Registry.BLOCK, PotMeetKettle.MOD_ID);

    private PMKBlocks() {}

    public static final Supplier<PotBlock> POT = BLOCKS.register("pot", () -> new PotBlock(
            BlockBehaviour.Properties
                    .copy(Blocks.IRON_BLOCK)
                    .isRedstoneConductor((state, reader, pos) -> false))
    );

    public static final Supplier<PotBlock> PAN = BLOCKS.register("pan", () -> new PotBlock(
            BlockBehaviour.Properties
                    .copy(Blocks.IRON_BLOCK)
                    .isRedstoneConductor((state, reader, pos) -> false))
    );

    public static final Supplier<PotBlock> WOK = BLOCKS.register("wok", () -> new PotBlock(
            BlockBehaviour.Properties
                    .copy(Blocks.IRON_BLOCK)
                    .isRedstoneConductor((state, reader, pos) -> false))
    );

    protected class PMKBlockBuilder {
        private BlockBehaviour.Properties properties;
        private String name;
        private Supplier<Block> supplier;

        public PMKBlockBuilder(BlockBehaviour.Properties properties, String name) {
            this.properties = properties;
            this.name = name;
        }

        public PMKBlockBuilder setSupplier(Supplier<Block> supplier) {
            this.supplier = supplier;
            return this;
        }

        public Supplier<Block> build() {
            return BLOCKS.register(name, supplier);
        }
    }
}
