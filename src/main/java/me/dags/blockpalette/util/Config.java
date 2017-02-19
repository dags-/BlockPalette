package me.dags.blockpalette.util;

import me.dags.blockpalette.color.ColorMode;
import me.dags.blockpalette.creative.PickMode;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * @author dags <dags@dags.me>
 */
public class Config {

    public static boolean show_settings = true;
    public static boolean match_textures = true;
    public static PickMode pick_mode = PickMode.MOUSE;
    public static int highlight_red = 185;
    public static int highlight_green = 185;
    public static int highlight_blue = 185;
    public static float highlight_scale = 1.05F;

    public static ColorMode color_mode = ColorMode.ADJACENT;
    public static int group_size = 3;
    public static int angle = 30;
    public static float leniency = 0.25F;

    private static Configuration cfg = new Configuration();

    public static void init(File file) {
        cfg = new Configuration(file);
        cfg.load();
        show_settings = cfg.get("general", "show_settings", false).getBoolean();
        match_textures = cfg.get("general", "match_textures", true).getBoolean();
        pick_mode = PickMode.fromId(cfg.get("general", "pick_mode", 0).getInt());

        highlight_red = cfg.get("general", "highlight_red", highlight_red).getInt();
        highlight_green = cfg.get("general", "highlight_green", highlight_green).getInt();
        highlight_blue = cfg.get("general", "highlight_blue", highlight_blue).getInt();
        highlight_scale = (float) cfg.get("general", "highlight_scale", highlight_scale).getDouble();

        color_mode = ColorMode.fromId(cfg.get("color", "color_mode", ColorMode.getId(color_mode)).getInt());

        angle = cfg.get("color", "angle", angle).getInt();
        group_size = cfg.get("color", "group_size", group_size).getInt();
        leniency = (float) cfg.get("color", "leniency", leniency).getDouble();
        cfg.save();
    }

    public static void save() {
        cfg.get("general", "show_settings", show_settings).set(show_settings);
        cfg.get("general", "match_textures", match_textures).set(match_textures);
        cfg.get("general", "pick_mode", 0).set(PickMode.toId(pick_mode));
        cfg.get("general", "highlight_red", highlight_red).set(highlight_red);
        cfg.get("general", "highlight_green", highlight_green).set(highlight_green);
        cfg.get("general", "highlight_blue", highlight_blue).set(highlight_blue);
        cfg.get("general", "highlight_scale", highlight_scale).set(highlight_scale);
        cfg.get("color", "color_mode", 0).set(ColorMode.getId(color_mode));
        cfg.get("color", "angle", angle).set(angle);
        cfg.get("color", "group_size", group_size).set(group_size);
        cfg.get("color", "leniency", leniency).set(leniency);
        cfg.save();
    }
}
