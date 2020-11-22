package club.rigox.vanillacore.listeners;

import club.rigox.vanillacore.models.PlayerModel;
import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffectType;

import static club.rigox.vanillacore.utils.ConsoleUtils.debug;

public class PlayerListener implements Listener {
    private final VanillaCore plugin;

    public PlayerListener(VanillaCore plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("staff.use")) {
            plugin.getPlayerModel().put(player, new PlayerModel());
            debug(String.format("%s has been added to getPlayerModel method", player.getName()));
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (plugin.getPlayerModel().containsKey(player)) {
            plugin.getInventoryUtils().restoreInventory(player);
            plugin.getPlayerModel().remove(player);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            debug(String.format("%s has been removed of the getPlayerModel method", player.getName()));
        }

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (plugin.getPlayerModel().get(player).isFrozed()) {
            Location location = player.getLocation();
            player.teleport(location);
        }
    }
}
