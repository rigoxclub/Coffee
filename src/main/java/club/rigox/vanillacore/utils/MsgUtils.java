package club.rigox.vanillacore.utils;

import org.bukkit.ChatColor;

public class MsgUtils {
    public static String color (String str) {
        return str = ChatColor.translateAlternateColorCodes('&', str);
    }
}
