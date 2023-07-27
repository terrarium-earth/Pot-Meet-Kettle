package earth.terrarium.potmeetkettle.common.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import earth.terrarium.potmeetkettle.common.data.HeatLevelData;
import earth.terrarium.potmeetkettle.common.recipe.BrewRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class PMKRecipeSerializers {

    public static final ResourcefulRegistry<RecipeSerializer<?>> RECIPE_SERIALIZERS = ResourcefulRegistries.create(BuiltInRegistries.RECIPE_SERIALIZER, PotMeetKettle.MOD_ID);

    private PMKRecipeSerializers() {}

    public static final RegistryEntry<RecipeSerializer<HeatLevelData>> HEAT_LEVEL_DATA = RECIPE_SERIALIZERS.register("heat_level_data", () -> new CodecRecipeSerializer<>(
            PMKRecipeTypes.HEAT_LEVEL_DATA.get(),
            HeatLevelData::codec
    ));

    public static final RegistryEntry<RecipeSerializer<?>> BREW = RECIPE_SERIALIZERS.register("brew", () -> new CodecRecipeSerializer<>(
            PMKRecipeTypes.BREW.get(),
            BrewRecipe::codec
    ));
}
