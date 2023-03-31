package earth.terrarium.potmeetkettle.common.registry.forge;

import earth.terrarium.potmeetkettle.PotMeetKettle;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class PMKItemsImpl {
    public static CreativeModeTab createTab(Supplier<ItemStack> icon) {
        return new CreativeModeTab(PotMeetKettle.MOD_ID) {
            @Override public @NotNull ItemStack makeIcon() {
                return icon.get();
            }
        };
    }
}
