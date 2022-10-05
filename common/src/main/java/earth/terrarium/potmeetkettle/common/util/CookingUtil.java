package earth.terrarium.potmeetkettle.common.util;

import earth.terrarium.potmeetkettle.common.block.base.HeatSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A utility class for cooking-related calculations.
 * @author <a href="https://github.com/Brittank88">Brittank88</a>
 */
public final class CookingUtil {
    private CookingUtil() {}

    /**
     * @param level      The level containing the block.
     * @param pos        The block position.
     * @param state      The block state.
     * @param recipeTime The time it takes to cook the recipe.
     * @return The amount of ticks it takes to cook the recipe.
     *         <br />If the block is not a heat supplier, returns {@link Integer#MAX_VALUE}.
     */
    public static int getCookingTime(Level level, BlockPos pos, BlockState state, double recipeTime) {
        return level.getBlockEntity(pos) instanceof HeatSupplier heatSupplier
                ? Mth.ceil(recipeTime / heatSupplier.getHeat(level, pos, state))
                : Integer.MAX_VALUE;
    }

    /**
     * @param heat       The heat of the block.
     * @param recipeTime The time it takes to cook the recipe.
     * @return The amount of ticks it takes to cook the recipe.
     */
    public static int getCookingTime(double heat, double recipeTime) { return Mth.ceil(recipeTime / heat); }
}
