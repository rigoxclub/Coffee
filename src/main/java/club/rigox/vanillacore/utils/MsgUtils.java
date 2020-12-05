package club.rigox.vanillacore.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class MsgUtils {
    public static String color (String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static List<String> parseFieldList(List<String> field, Player p) {
        return PlaceholderAPI.setPlaceholders(p, field);
    }

    public static String parseField(String field, Player p) {
        return PlaceholderAPI.setPlaceholders(p, field);
    }
}
