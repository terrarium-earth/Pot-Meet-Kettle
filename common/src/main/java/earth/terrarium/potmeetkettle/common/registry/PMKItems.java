package earth.terrarium.potmeetkettle.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class PMKItems {

    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, PotMeetKettle.MOD_ID);
    public static final ResourcefulRegistry<CreativeModeTab> TABS = ResourcefulRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, PotMeetKettle.MOD_ID);

    private PMKItems() {}

    private static final Item.Properties DEFAULT_PROPERTIES = new Item.Properties().stacksTo(1);

    public static final Supplier<Item> POT = ITEMS.register("pot", () -> new BlockItem(PMKBlocks.POT.get(), DEFAULT_PROPERTIES));
    public static final Supplier<Item> PAN = ITEMS.register("pan", () -> new BlockItem(PMKBlocks.PAN.get(), DEFAULT_PROPERTIES));
    public static final Supplier<Item> WOK = ITEMS.register("wok", () -> new BlockItem(PMKBlocks.WOK.get(), DEFAULT_PROPERTIES));

    public static final ResourceLocation VESSEL_TAB_KEY = new ResourceLocation(PotMeetKettle.MOD_ID, "vessel_tab");
    public static final Supplier<CreativeModeTab> VESSEL_TAB = TABS.register(VESSEL_TAB_KEY.getPath(), () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 0)
            .icon(PMKItems.POT.get()::getDefaultInstance)
            .title(Component.translatable(VESSEL_TAB_KEY.toLanguageKey("itemGroup")))
            .build()
    );
}
