package earth.terrarium.potmeetkettle;

import earth.terrarium.potmeetkettle.common.registry.PMKBlockEntityTypes;
import earth.terrarium.potmeetkettle.common.registry.PMKBlocks;
import earth.terrarium.potmeetkettle.common.registry.PMKItems;
import net.minecraft.resources.ResourceLocation;

public class PotMeetKettle {
    public static final String MOD_ID = "potmeetkettle";

    public static ResourceLocation id(String path) { return new ResourceLocation(MOD_ID, path); }

    public static void init() {
        PMKBlocks.BLOCKS.init();
        PMKBlockEntityTypes.BLOCK_ENTITY_TYPES.init();
        PMKItems.ITEMS.init();
    }
}