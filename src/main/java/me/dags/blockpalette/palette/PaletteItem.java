package me.dags.blockpalette.palette;

import me.dags.blockpalette.color.ColorF;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author dags <dags@dags.me>
 */
@SideOnly(Side.CLIENT)
public final class PaletteItem {

    public static final PaletteItem EMPTY = new PaletteItem();

    private final ItemStack itemStack;
    private final ColorF colorF;
    private final int hashCode;

    private PaletteItem() {
        this.itemStack = null;
        this.colorF = ColorF.EMPTY;
        this.hashCode = super.hashCode();
    }

    private PaletteItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.colorF = ColorF.EMPTY;
        this.hashCode = itemStack.getUnlocalizedName().hashCode();
    }

    private PaletteItem(ItemStack itemStack, ColorF colorF) {
        this.itemStack = itemStack;
        this.colorF = colorF;
        this.hashCode = itemStack.getUnlocalizedName().hashCode();
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ColorF getColor() {
        return colorF;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass() == this.getClass() && o.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public static PaletteItem of(ItemStack stack) {
        return stack == null ? EMPTY : new PaletteItem(stack.copy());
    }

    public static PaletteItem of(ItemStack stack, ColorF color) {
        return stack == null ? EMPTY : new PaletteItem(stack.copy(), color);
    }
}
