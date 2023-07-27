package earth.terrarium.potmeetkettle;

import earth.terrarium.potmeetkettle.common.registry.*;
import net.minecraft.resources.ResourceLocation;

public class PotMeetKettle {
    public static final String MOD_ID = "potmeetkettle";

    public static ResourceLocation id(String path) { return new ResourceLocation(MOD_ID, path); }

    public static void init() {
        PMKRecipeSerializers.RECIPE_SERIALIZERS.init();
        PMKRecipeTypes.RECIPE_TYPES.init();
        PMKBlocks.BLOCKS.init();
        PMKBlockEntityTypes.BLOCK_ENTITY_TYPES.init();
        PMKItems.ITEMS.init();
        PMKItems.TABS.init();
    }
}