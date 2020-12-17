package club.rigox.coffee.listeners;

import club.rigox.coffee.Coffee;
import club.rigox.coffee.models.PlayerModel;
import club.rigox.coffee.player.scoreboard.ScoreBoardAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import static club.rigox.coffee.utils.ConsoleUtils.debug;

public class PlayerListener implements Listener {
    private final Coffee plugin;
    private final ScoreBoardAPI scoreBoardAPI;

    public PlayerListener(Coffee plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        scoreBoardAPI = new ScoreBoardAPI(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayers().put(player, new PlayerModel(plugin));
        scoreBoardAPI.setScoreBoard(player, "general", true);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Modificado xq anteriormente te reiniciaba el inventario por mas que no estes en staff y rip inv
        if (plugin.getPlayers().get(player).hasGod() || plugin.getPlayers().get(player).isFrozed()) {
            plugin.getInventoryUtils().restoreInventory(player);
            plugin.getPlayers().remove(player);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            debug(String.format("%s has been removed of the getPlayerModel method", player.getName()));
        }

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (!plugin.getPlayers().get(player).isFrozed())
            return;

        if (event.getTo().getX() == event.getFrom().getX() &&
                event.getFrom().getZ() == event.getTo().getZ() &&
                event.getTo().getY() == event.getFrom().getY())
            return;

        if (plugin.getPlayers().get(player).isFrozed()) {
            event.setCancelled(true);
        }
    }
}
