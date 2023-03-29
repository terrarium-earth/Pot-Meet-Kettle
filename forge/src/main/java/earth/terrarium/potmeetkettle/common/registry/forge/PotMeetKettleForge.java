package earth.terrarium.potmeetkettle.common.forge.registry;

import earth.terrarium.potmeetkettle.PotMeetKettle;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PotMeetKettle.MOD_ID)
public class PotMeetKettleForge {
    public PotMeetKettleForge() {
        PotMeetKettle.init();
    }
}