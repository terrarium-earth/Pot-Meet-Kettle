package earth.terrarium.potmeetkettle.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.common.codecs.recipes.IngredientCodec;
import com.teamresourceful.resourcefullib.common.codecs.recipes.ItemStackCodec;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipe;
import earth.terrarium.potmeetkettle.common.registry.PMKRecipeSerializers;
import earth.terrarium.potmeetkettle.common.registry.PMKRecipeTypes;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

@MethodsReturnNonnullByDefault
public record BrewRecipe(ResourceLocation id, List<Ingredient> ingredients, ItemStack result) implements CodecRecipe<Container> {

    public static Codec<BrewRecipe> codec(ResourceLocation id) {
        return RecordCodecBuilder.create(instance -> instance.group(
                instance.point(id),
                IngredientCodec.CODEC.listOf().fieldOf("ingredients").forGetter(BrewRecipe::ingredients),
                ItemStackCodec.CODEC.fieldOf("result").forGetter(BrewRecipe::result)
        ).apply(instance, BrewRecipe::new));
    }

    @Override public boolean matches(Container container, Level level) {
        return false;
    }

    @Override public RecipeSerializer<?> getSerializer() {
        return PMKRecipeSerializers.BREW.get();
    }

    @Override public RecipeType<?> getType() {
        return PMKRecipeTypes.BREW.get();
    }

    // TODO: getGroup()? getToastSymbol()?
}
