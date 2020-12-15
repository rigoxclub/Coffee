package club.rigox.vanillacore.utils;

import club.rigox.vanillacore.Coffee;
import org.bukkit.Bukkit;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class ConsoleUtils {

    public static void debug(String str) {
        if (!Coffee.instance.getSetting().getBoolean("debug")) return;
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&9DEBUG&f] %s", str)));
    }

    public static void warn(String str) {
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&eWARN&f] %s", str)));
    }
}
