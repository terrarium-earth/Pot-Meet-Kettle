package earth.terrarium.potmeetkettle.common.registry;

import earth.terrarium.botarium.api.registry.RegistryHolder;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

public class PMKItems {

    public static final RegistryHolder<Item> ITEMS = new RegistryHolder<>(Registry.ITEM, PotMeetKettle.MOD_ID);

    private PMKItems() {}

}
