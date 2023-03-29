package earth.terrarium.potmeetkettle.common.fabric;

import earth.terrarium.potmeetkettle.PotMeetKettle;
import net.fabricmc.api.ModInitializer;

public class PotMeetKettleFabric implements ModInitializer {
    @Override public void onInitialize() { PotMeetKettle.init(); }
}