package earth.terrarium.potmeetkettle.common.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.common.codecs.predicates.RestrictedBlockPredicate;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipe;
import earth.terrarium.potmeetkettle.common.registry.PMKRecipeSerializers;
import earth.terrarium.potmeetkettle.common.registry.PMKRecipeTypes;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

@MethodsReturnNonnullByDefault
public record HeatLevelData(ResourceLocation id, RestrictedBlockPredicate predicate, int heatLevel) implements CodecRecipe<Container> {

    public static Codec<HeatLevelData> codec(ResourceLocation id) {
        return RecordCodecBuilder.create(instance -> instance.group(
                instance.point(id),
                RestrictedBlockPredicate.CODEC.fieldOf("predicate").forGetter(HeatLevelData::predicate),
                Codec.intRange(1, 3).fieldOf("heat_level").forGetter(HeatLevelData::heatLevel)
        ).apply(instance, HeatLevelData::new));
    }

    @Override public boolean matches(Container container, Level level) {
        return false;
    }

    @Override public RecipeSerializer<?> getSerializer() {
        return PMKRecipeSerializers.HEAT_LEVEL_DATA.get();
    }

    @Override public RecipeType<?> getType() {
        return PMKRecipeTypes.HEAT_LEVEL_DATA.get();
    }
}
