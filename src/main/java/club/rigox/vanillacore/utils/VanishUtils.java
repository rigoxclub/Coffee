package club.rigox.vanillacore.utils;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class VanishUtils {
    private final VanillaCore plugin;

    public VanishUtils (VanillaCore plugin) {
        this.plugin = plugin;
    }

    public void hideStaff(Player vanishedStaff) {
        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
            all.hidePlayer(plugin, vanishedStaff);
        }


    }

    public void showStaff (Player vanishedStaff) {
        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
            all.showPlayer(plugin, vanishedStaff);
        }
    }
}
