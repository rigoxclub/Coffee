package club.rigox.vanillacore.listeners;

import club.rigox.vanillacore.VanillaCore;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class StaffListener implements Listener {
    private final VanillaCore plugin;

    public StaffListener (VanillaCore plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent event) {

        if (plugin.getPlayerModel().get(event.getPlayer()).isHidden()) {
            event.getPlayer().sendMessage(color("&cYou can't break blocks while in staff mode!"));
            event.setCancelled(true);
        }

        if (plugin.getPlayerModel().get(event.getPlayer()).isFrozed()) {
            event.getPlayer().sendMessage(color("&cYou can't break blocks while frozed!"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBlockPlace(BlockPlaceEvent event) {

        if (plugin.getPlayerModel().get(event.getPlayer()).isHidden()) {
            event.getPlayer().sendMessage(color("&cYou can't place blocks while in staff mode!"));
            event.setCancelled(true);
        }

        if (plugin.getPlayerModel().get(event.getPlayer()).isFrozed()) {
            event.getPlayer().sendMessage(color("&cYou can't place blocks while frozed!"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onExperiencePickup(PlayerPickupExperienceEvent event) {

        event.setCancelled(plugin.getPlayerModel().get(event.getPlayer()).isHidden());
        event.setCancelled(plugin.getPlayerModel().get(event.getPlayer()).isFrozed());
    }

    @EventHandler
    public void onArrowPickup(PlayerPickupArrowEvent event) {

        event.setCancelled(plugin.getPlayerModel().get(event.getPlayer()).isHidden());
        event.setCancelled(plugin.getPlayerModel().get(event.getPlayer()).isFrozed());

    }

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            event.setCancelled(plugin.getPlayerModel().get(player).isHidden());
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {

        if (plugin.getPlayerModel().get(event.getPlayer()).isHidden()) {
            event.getPlayer().sendMessage(color("&cYou can't drop items while in staff mode!"));
            event.setCancelled(true);
        }

        if (plugin.getPlayerModel().get(event.getPlayer()).isFrozed()) {
            event.getPlayer().sendMessage(color("&cYou can't drop items while frozed!"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            event.setCancelled(plugin.getPlayerModel().get(player).isHidden());
            event.setCancelled(plugin.getPlayerModel().get(player).isFrozed());
        }
    }

    @EventHandler
    public void hidePlayerForEntities(EntityTargetLivingEntityEvent event) {

        if (event.getTarget() instanceof Player) {
            Player player = (Player) event.getTarget();
            if (plugin.getPlayerModel().get(player).isHidden()) {
                event.setCancelled(true);
            }

            if (plugin.getPlayerModel().get(player).isFrozed()) {
                event.setCancelled(true);
            }
        }
    }

}
