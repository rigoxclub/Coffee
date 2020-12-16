package club.rigox.coffee.utils;

import club.rigox.coffee.Coffee;
import org.bukkit.Bukkit;

import static club.rigox.coffee.utils.MsgUtils.color;

public class ConsoleUtils {

    public static void debug(String str) {
        if (!Coffee.instance.getSetting().getBoolean("debug")) return;
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&9DEBUG | Coffee&f] %s", str)));
    }

    public static void warn(String str) {
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&eWARN | Coffee&f] %s", str)));
    }

    public static void info(String str) {
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&aINFO | Coffee&f] %s", str)));
    }
}
