package me.dags.blockpalette.gui;

import me.dags.blockpalette.util.Value;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author dags <dags@dags.me>
 */
@SideOnly(Side.CLIENT)
public class Tooltip {

    private final FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    private final Provider tooltip;
    private final int width;
    private final int pad = 5;
    private final int offsetX;
    private final GuiButton button;

    private Tooltip(GuiButton button, Provider tooltip, int width) {
        this.button = button;
        this.tooltip = tooltip;
        this.width = width;
        this.offsetX = width / 2;
    }

    public void draw(int x, int y, float ticks) {
        if (button.isMouseOver()) {
            String message = I18n.format(tooltip.getUnlocalized());

            int stringWidth = fontRenderer.getStringWidth(message);
            int lines = 2 + (stringWidth / width);
            int height = (fontRenderer.FONT_HEIGHT * lines);
            int offsetY = height + pad + pad;

            int left = x - offsetX - pad;
            int top = y - offsetY - pad;

            int right = left + width + pad + pad;
            int bottom = top + height + pad + pad;
            Gui.drawRect(left, top, right, bottom, 0xDD000000);

            int textLeft = left + pad;
            int textTop = top + pad;
            fontRenderer.drawSplitString(message, textLeft, textTop, width, 0xFFFFFF);
        }
    }

    public static Tooltip of(GuiButton button, Provider provider) {
        return new Tooltip(button, provider, 150);
    }

    public interface Provider {

        String getUnlocalized();
    }

    static class Simple implements Provider {

        private final String tooltip;

        Simple(String tooltip) {
            this.tooltip = tooltip;
        }

        @Override
        public String getUnlocalized() {
            return tooltip;
        }
    }

    static class PointerTip<T extends Provider> implements Provider {

        private final Value<T> option;

        PointerTip(Value<T> option) {
            this.option = option;
        }

        @Override
        public String getUnlocalized() {
            return option.get().getUnlocalized();
        }
    }
}
