package club.rigox.coffee.listeners;

import club.rigox.coffee.Coffee;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;

import static club.rigox.coffee.utils.ConsoleUtils.debug;
import static club.rigox.coffee.utils.MsgUtils.color;

public class StaffListener implements Listener {
    private final Coffee plugin;

    public StaffListener(Coffee plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onExperiencePickup(PlayerPickupExperienceEvent event) {

        if (!(plugin.getPlayers().get(event.getPlayer()).hasGod() || plugin.getPlayers().get(event.getPlayer()).isFrozed())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onArrowPickup(PlayerPickupArrowEvent event) {
        if (!(plugin.getPlayers().get(event.getPlayer()).hasGod() || plugin.getPlayers().get(event.getPlayer()).isFrozed())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            event.setCancelled(plugin.getPlayers().get(player).hasGod());
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (!(plugin.getPlayers().get(event.getPlayer()).hasGod() || plugin.getPlayers().get(event.getPlayer()).isFrozed())) {
            return;
        }

        event.getPlayer().sendMessage(color(plugin.getPlayers().get(event.getPlayer()).isFrozed() ? "You can't drop items while frozed" : "You can't drop items while in staff mode"));
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (plugin.getPlayers().get(player).hasGod() || plugin.getPlayers().get(player).isFrozed()) {
                event.setCancelled(true);
                return;
            }

            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL) && plugin.getPlayers().get(player).isFlying()) {
                event.setCancelled(true);
                plugin.getPlayers().get(player).removeFly();
                debug(String.format("Removed player from the isFlying method %s", player));
                return;
            }

            debug(String.format("Value of player flying is %s for %s", plugin.getPlayers().get(player).isFlying(), player));
            debug(String.format("%s received Damage %s", player, event.getCause()));
        }
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (plugin.getPlayers().get(player).isVanished() || plugin.getPlayers().get(player).isFrozed()) {
                event.setCancelled(true);
                player.sendMessage(color("&cYou can't attack while in vanish or frozed!"));

            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (plugin.getPlayers().get(player).isVanished()) {
            event.setCancelled(true);
            player.sendMessage(color("&cYou can't send messages while vanished!"));
        }
    }

    @EventHandler
    public void hidePlayerForEntities(EntityTargetLivingEntityEvent event) {
        if (event.getTarget() instanceof Player) {
            Player player = (Player) event.getTarget();
            if (plugin.getPlayers().get(player).hasGod() || plugin.getPlayers().get(player).isFrozed()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (plugin.getPlayers().get(player).hasGod()) {
                event.setCancelled(true);
//                debug(String.format("Cancelled FoodLevelChangeEvent to %s", player));
            }
        }
    }

    @EventHandler
    public void onClickSlot(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (plugin.getPlayers().get(player).hasGod()) {
            e.setCancelled(true);
        }
    }
}
