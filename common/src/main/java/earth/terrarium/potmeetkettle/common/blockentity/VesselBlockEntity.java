package earth.terrarium.potmeetkettle.common.blockentity;

import earth.terrarium.botarium.common.fluid.base.BotariumFluidBlock;
import earth.terrarium.botarium.common.fluid.impl.SimpleFluidContainer;
import earth.terrarium.botarium.common.fluid.impl.WrappedBlockFluidContainer;
import earth.terrarium.botarium.common.item.ItemContainerBlock;
import earth.terrarium.botarium.common.item.SerializableContainer;
import earth.terrarium.botarium.common.item.SimpleItemContainer;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

@MethodsReturnNonnullByDefault
public abstract class VesselBlockEntity extends BlockEntity implements RecipeHolder, BotariumFluidBlock<WrappedBlockFluidContainer>, ItemContainerBlock {

    private SimpleItemContainer itemContainer;
    private WrappedBlockFluidContainer fluidContainer;

    public VesselBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    // Abstract Getters //
    protected abstract boolean canBeHeated();
    protected abstract int getInventorySize();
    protected abstract long getFluidCapacity();

    // Container Getters //
    @Override public WrappedBlockFluidContainer getFluidContainer() {
        if (fluidContainer == null) fluidContainer = new WrappedBlockFluidContainer(this, new SimpleFluidContainer(getFluidCapacity(), 1, (amount, fluid) -> true));
        return fluidContainer;
    }
    @Override public SerializableContainer getContainer() {
        if (itemContainer == null) itemContainer = new SimpleItemContainer(this, getInventorySize());
        return itemContainer;
    }

    @Override public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override public void setRecipeUsed(@Nullable Recipe<?> recipe) {
        // TODO: Implement?
    }

    @Nullable @Override public Recipe<?> getRecipeUsed() {
        return null;    // TODO: Implement?
    }

    public int getProgress() {
        return 0;   // TODO: Implement.
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, AbstractFurnaceBlockEntity abstractFurnaceBlockEntity) {
        // TODO: Implement.
    }
}
