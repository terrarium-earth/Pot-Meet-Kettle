package earth.terrarium.potmeetkettle.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.potmeetkettle.PotMeetKettle;
import earth.terrarium.potmeetkettle.common.data.HeatLevelData;
import earth.terrarium.potmeetkettle.common.recipe.BrewRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class PMKRecipeTypes {

    public static final ResourcefulRegistry<RecipeType<?>> RECIPE_TYPES = ResourcefulRegistries.create(BuiltInRegistries.RECIPE_TYPE, PotMeetKettle.MOD_ID);

    private PMKRecipeTypes() {}

    // Synced Data
    public static final RegistryEntry<RecipeType<HeatLevelData>> HEAT_LEVEL_DATA = register("heat_level_data");

    // Actual Recipes
    public static final RegistryEntry<RecipeType<BrewRecipe>> BREW = register("brew");

    private static <T extends Recipe<?>> RegistryEntry<RecipeType<T>> register(String id) {
        return RECIPE_TYPES.register(id, () -> new RecipeType<T>() {
            @Override public String toString() {
                return id;
            }
        });
    }
}
