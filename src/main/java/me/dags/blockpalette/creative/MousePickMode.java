package me.dags.blockpalette.creative;

import me.dags.blockpalette.gui.PaletteScreen;
import me.dags.blockpalette.palette.PaletteMain;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

/**
 * @author dags <dags@dags.me>
 */
public class MousePickMode extends CreativePickMode {

    private PaletteScreen screen;
    private boolean showDown = false;
    private boolean lCtrlDown = false;
    private boolean lShiftDown = false;

    public MousePickMode(PaletteMain main) {
        super(main);
        this.screen = new PaletteScreen(main);
        this.screen.initGui();
        this.main.newPalette(null);
    }

    @Override
    void drawScreen(Event event) {
        if (main.getPalette().isPresent()) {
            screen.drawScreen(mouseX, mouseY, 0F);
            event.setCanceled(true);
        }
    }

    @Override
    void pressMouse(Event event, int button) {
        if (main.getPalette().isPresent()) {
            try {
                screen.mouseClicked(mouseX, mouseY, button);
                event.setCanceled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (stackUnderMouse != null && button == 0 && !modifierKey()) {
            main.newPalette(stackUnderMouse);
            screen = new PaletteScreen(main);
            screen.setCreativeOverlay(true);
            screen.initGui();
            event.setCanceled(true);
        }
    }

    @Override
    void releaseMouse(Event event, int button) {
        if (main.getPalette().isPresent()) {
            screen.mouseReleased(mouseX, mouseY, button);
            event.setCanceled(true);
        }
    }

    @Override
    void pressKey(Event event, int keyCode) {
        if (keyCode == Keyboard.KEY_LCONTROL) {
            lCtrlDown = true;
        } else if (keyCode == Keyboard.KEY_LSHIFT) {
            lShiftDown = true;
        } else if (keyCode == main.show.getKeyCode()) {
            showDown = true;
        }

        if (main.getPalette().isPresent()) {
            event.setCanceled(true);
        }
    }

    @Override
    void releaseKey(Event event, int keyCode) {
        if (keyCode == Keyboard.KEY_LCONTROL) {
            lCtrlDown = false;
        } else if (keyCode == Keyboard.KEY_LSHIFT) {
            lShiftDown = false;
        } else if (keyCode == main.show.getKeyCode()) {
            showDown = false;
        }

        if (main.getPalette().isPresent()) {
            if (keyCode == Keyboard.KEY_ESCAPE || keyCode == main.show.getKeyCode() || main.isInventoryKey(keyCode)) {
                screen.onGuiClosed();
                main.newPalette(null);
            }
            event.setCanceled(true);
        }
    }

    private boolean modifierKey() {
        return lCtrlDown || lShiftDown || showDown;
    }
}