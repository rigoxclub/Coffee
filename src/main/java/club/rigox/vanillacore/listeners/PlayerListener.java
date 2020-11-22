package club.rigox.vanillacore.listeners;

import club.rigox.vanillacore.Models.PlayerModel;
import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.Inventory;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffectType;

import static club.rigox.vanillacore.utils.ConsoleUtils.debug;
import static club.rigox.vanillacore.utils.MsgUtils.color;

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
            plugin.getStaffMode().put(player, new PlayerModel());
            debug(String.format("%s has been added to getStaffMode method", player.getName()));
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (plugin.getStaffMode().containsKey(player)) {
            plugin.getInventoryUtils().restoreInventory(player);
            plugin.getStaffMode().remove(player);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            debug(String.format("%s has been removed of the getStaffMode method", player.getName()));
        }

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (plugin.getStaffMode().get(player).isFrozed()) {
            Location location = player.getLocation();
            player.teleport(location);
        }
    }
}
