package club.rigox.vanillacore.listeners;

import club.rigox.vanillacore.models.PlayerModel;
import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Bukkit;
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
        plugin.getPlayers().put(player, new PlayerModel());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (plugin.getPlayers().containsKey(player)) {
            plugin.getInventoryUtils().restoreInventory(player);
            plugin.getPlayers().remove(player);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            debug(String.format("%s has been removed of the getPlayerModel method", player.getName()));
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        if(!plugin.getPlayers().get(event.getPlayer()).isFrozed()) return; // Al pedo todo lo demas si el tipo no esta freezeado xd

        if (event.getTo().getX() == event.getFrom().getX() && event.getFrom().getZ() == event.getTo().getZ()) return; // No canceles al pedo cuando mueven la cabeza xd

        Player player = event.getPlayer();

        if (plugin.getPlayers().get(player).isFrozed()) {
            event.setCancelled(true);
        }
    }
}
