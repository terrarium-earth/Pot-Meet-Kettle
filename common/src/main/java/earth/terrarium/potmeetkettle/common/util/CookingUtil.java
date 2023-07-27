package earth.terrarium.potmeetkettle.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import static earth.terrarium.potmeetkettle.common.registry.PMKRecipeTypes.HEAT_LEVEL_DATA;

/**
 * A utility class for cooking-related calculations.
 * @author <a href="https://github.com/Brittank88">Brittank88</a>
 */
public final class CookingUtil {
    private CookingUtil() {}

    /**
     * @param level      The level containing the block.
     * @param pos        The block position.
     * @param recipeTime The time it takes to cook the recipe.
     * @return The amount of ticks it takes to cook the recipe.
     *         <br />If the block is not a heat supplier, returns {@link Integer#MAX_VALUE}.
     */
    public static int getCookingTime(@NotNull ServerLevel level, BlockPos pos, double recipeTime) {

        return level.getServer().getRecipeManager().getAllRecipesFor(HEAT_LEVEL_DATA.get()).stream()
                .filter(h -> h.predicate().matches(level, pos))
                .findFirst()
                .map(h -> Mth.ceil(recipeTime / h.heatLevel()))
                .orElse(0);
    }

    /**
     * @param heat       The heat of the block.
     * @param recipeTime The time it takes to cook the recipe.
     * @return The amount of ticks it takes to cook the recipe.
     */
    public static int getCookingTime(double heat, double recipeTime) { return Mth.ceil(recipeTime / heat); }
}
