package earth.terrarium.potmeetkettle.common.blockentity.base;

import earth.terrarium.botarium.api.fluid.FluidHoldingBlock;
import earth.terrarium.botarium.api.fluid.SimpleUpdatingFluidContainer;
import earth.terrarium.botarium.api.fluid.UpdatingFluidContainer;
import earth.terrarium.botarium.api.item.ItemContainerBlock;
import earth.terrarium.botarium.api.item.SerializbleContainer;
import earth.terrarium.botarium.api.item.SimpleItemContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.stream.IntStream;

@ParametersAreNonnullByDefault
public abstract class CookingVesselBlockEntityBase extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, FluidHoldingBlock, ItemContainerBlock {

    private final SimpleItemContainer itemContainer;
    private final int[] slots;
    private final SimpleUpdatingFluidContainer fluidContainer;

    private double progress;    // TODO: Implement.

    public CookingVesselBlockEntityBase(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, int inventorySize, long fluidCapacity) {
        super(blockEntityType, blockPos, blockState);
        itemContainer = new SimpleItemContainer(this, inventorySize);
        slots = IntStream.range(0, inventorySize).toArray();
        fluidContainer = new SimpleUpdatingFluidContainer(this, fluidCapacity, 1, (amount, fluid) -> true);
    }

    @Override public UpdatingFluidContainer getFluidContainer() { return fluidContainer; }
    @Override public SerializbleContainer getContainer() { return itemContainer; }

    @Override public void update() { setChanged(); }

    @Override public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override public int[] getSlotsForFace(Direction side) { return slots; }

    @Override public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return itemContainer.canPlaceItem(index, itemStack);
    }

    @Override public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return itemContainer.getItem(index).getItem() == stack.getItem();
    }

    @Override protected Component getDefaultName() { return Component.translatable("container.cooking_vessel"); }

    @Override protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) { return null; }

    @Override public int getContainerSize() { return itemContainer.getContainerSize(); }

    @Override public boolean isEmpty() { return itemContainer.isEmpty(); }

    @Override public ItemStack getItem(int slot) { return itemContainer.getItem(slot); }
    @Override public ItemStack removeItem(int slot, int amount) { return itemContainer.removeItem(slot, amount); }
    @Override public ItemStack removeItemNoUpdate(int slot) { return itemContainer.removeItemNoUpdate(slot); }
    @Override public void setItem(int slot, ItemStack stack) { itemContainer.setItem(slot, stack); }

    @Override public boolean stillValid(Player player) { return itemContainer.stillValid(player); }

    @Override public void clearContent() {
        itemContainer.clearContent();
        fluidContainer.getFluids().forEach(holder -> holder.setAmount(0));  // TODO: Better way to clear the fluid container?
    }

    @Override public void setRecipeUsed(@Nullable Recipe<?> recipe) {
        // TODO: Implement?
    }

    @Nullable @Override public Recipe<?> getRecipeUsed() {
        return null;    // TODO: Implement?
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, AbstractFurnaceBlockEntity abstractFurnaceBlockEntity) {
        // TODO: Implement.
    }
}
