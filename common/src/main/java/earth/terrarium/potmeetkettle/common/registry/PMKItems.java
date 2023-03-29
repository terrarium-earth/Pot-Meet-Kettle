package earth.terrarium.potmeetkettle.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class PMKItems {

    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(Registry.ITEM, PotMeetKettle.MOD_ID);

    private PMKItems() {}

    @ExpectPlatform
    public static CreativeModeTab createTab(Supplier<ItemStack> icon) {
        throw new NotImplementedException("This method is implemented by the platform!!!");
    }

    public static final CreativeModeTab TAB = createTab(() -> PMKItems.POT.get().getDefaultInstance());

    public static final Supplier<Item> POT = ITEMS.register("pot", () -> new BlockItem(PMKBlocks.POT.get(), new Item.Properties().tab(TAB)));
    public static final Supplier<Item> PAN = ITEMS.register("pan", () -> new BlockItem(PMKBlocks.PAN.get(), new Item.Properties().tab(TAB)));
    public static final Supplier<Item> WOK = ITEMS.register("wok", () -> new BlockItem(PMKBlocks.WOK.get(), new Item.Properties().tab(TAB)));
}
