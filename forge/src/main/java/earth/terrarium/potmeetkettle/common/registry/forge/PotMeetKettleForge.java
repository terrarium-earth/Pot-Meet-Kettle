package earth.terrarium.potmeetkettle.common.registry.forge;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import earth.terrarium.potmeetkettle.common.registry.PMKItems;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PotMeetKettle.MOD_ID)
public class PotMeetKettleForge {
    public PotMeetKettleForge() {
        PotMeetKettle.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener((BuildCreativeModeTabContentsEvent event) -> {
            if (event.getTab() == PMKItems.VESSEL_TAB.get()) PMKItems.ITEMS.stream().map(RegistryEntry::get).forEach(event::accept);
        });
    }
}