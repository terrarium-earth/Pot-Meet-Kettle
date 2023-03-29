package earth.terrarium.potmeetkettle.common.fabric;

import earth.terrarium.potmeetkettle.PotMeetKettle;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class PMKItemsImpl {
    public static CreativeModeTab createTab(Supplier<ItemStack> icon) {
        return FabricItemGroupBuilder.build(new ResourceLocation(PotMeetKettle.MOD_ID, "tab"), icon);
    }
}
