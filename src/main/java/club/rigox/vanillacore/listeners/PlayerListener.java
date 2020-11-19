package club.rigox.vanillacore.listeners;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static club.rigox.vanillacore.utils.ConsoleUtils.debug;
import static club.rigox.vanillacore.utils.MsgUtils.color;

public class PlayerListener implements Listener {
    private VanillaCore plugin;

    public PlayerListener(VanillaCore plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("staff.use")) {
            plugin.getStaffMode().put(player, false);
            debug(String.format("%s has been added to getStaffMode method with %s boolean", player,plugin.getStaffMode().get(player)));
        }
    }
}
