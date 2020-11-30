package club.rigox.vanillacore.player;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Vanish {
    private final VanillaCore plugin;

    public Vanish(VanillaCore plugin) {
        this.plugin = plugin;
    }

    public void hideStaff(Player vanishedStaff) {
        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
            all.hidePlayer(plugin, vanishedStaff);
        }
        plugin.getPlayers().get(vanishedStaff).vanish();
    }

    public void showStaff (Player vanishedStaff) {
        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
            all.showPlayer(plugin, vanishedStaff);
        }
        plugin.getPlayers().get(vanishedStaff).unVanish();
    }
}
