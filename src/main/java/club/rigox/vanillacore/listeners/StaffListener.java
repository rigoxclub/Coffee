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

    public StaffListener(VanillaCore plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent event) {
        if (!(plugin.getPlayers().get(event.getPlayer()).isHidden() || plugin.getPlayers().get(event.getPlayer()).isFrozed())) {
            return;
        }

        event.getPlayer().sendMessage(color(plugin.getPlayers().get(event.getPlayer()).isFrozed() ? "You can't break blocks while frozed" : "You can't break blocks while in staff mode"));
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBlockPlace(BlockPlaceEvent event) {

        if (!(plugin.getPlayers().get(event.getPlayer()).isHidden() || plugin.getPlayers().get(event.getPlayer()).isFrozed())) {
            return;
        }

        event.getPlayer().sendMessage(color(plugin.getPlayers().get(event.getPlayer()).isFrozed() ? "You can't place blocks while frozed" : "You can't place blocks while in staff mode"));
        event.setCancelled(true);
    }

    @EventHandler
    public void onExperiencePickup(PlayerPickupExperienceEvent event) {

        if (!(plugin.getPlayers().get(event.getPlayer()).isHidden() || plugin.getPlayers().get(event.getPlayer()).isFrozed())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onArrowPickup(PlayerPickupArrowEvent event) {

        if (!(plugin.getPlayers().get(event.getPlayer()).isHidden() || plugin.getPlayers().get(event.getPlayer()).isFrozed())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            event.setCancelled(plugin.getPlayers().get(player).isHidden());
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (!(plugin.getPlayers().get(event.getPlayer()).isHidden() || plugin.getPlayers().get(event.getPlayer()).isFrozed())) {
            return;
        }

        event.getPlayer().sendMessage(color(plugin.getPlayers().get(event.getPlayer()).isFrozed() ? "You can't drop items while frozed" : "You can't drop items while in staff mode"));
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (plugin.getPlayers().get(player).isHidden() || plugin.getPlayers().get(player).isFrozed())
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void hidePlayerForEntities(EntityTargetLivingEntityEvent event) {
        if (event.getTarget() instanceof Player) {
            Player player = (Player) event.getTarget();
            if (plugin.getPlayers().get(player).isHidden() || plugin.getPlayers().get(player).isFrozed()) {
                event.setCancelled(true);
            }
        }
    }

}
