package earth.terrarium.potmeetkettle.common.registry.fabric;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import earth.terrarium.potmeetkettle.common.registry.PMKItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

public class PotMeetKettleFabric implements ModInitializer {
    @Override public void onInitialize() {
        PotMeetKettle.init();

        ItemGroupEvents.modifyEntriesEvent(ResourceKey.create(Registries.CREATIVE_MODE_TAB, PMKItems.VESSEL_TAB_KEY)).register(group -> {
            PMKItems.ITEMS.stream().map(RegistryEntry::get).forEach(group::accept);
        });
    }
}