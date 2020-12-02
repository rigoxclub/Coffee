package club.rigox.vanillacore.listeners;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.ToggleVanish;
import club.rigox.vanillacore.utils.Items;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;

import java.util.LinkedHashMap;
import java.util.Map;

import static club.rigox.vanillacore.utils.ConsoleUtils.debug;
import static club.rigox.vanillacore.utils.MsgUtils.color;

public class StaffListener implements Listener {
    private final VanillaCore plugin;
    private final ToggleVanish toggleVanish;

    private final Items items;

    public StaffListener(VanillaCore plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        toggleVanish = new ToggleVanish(plugin);

        this.items = new Items(plugin);
    }

//    @EventHandler
//    public void onPlayerBlockBreak(BlockBreakEvent event) {
//        if (!(plugin.getPlayers().get(event.getPlayer()).isHidden() || plugin.getPlayers().get(event.getPlayer()).isFrozed())) {
//            return;
//        }
//
//        event.getPlayer().sendMessage(color(plugin.getPlayers().get(event.getPlayer()).isFrozed() ? "You can't break blocks while frozed" : "You can't break blocks while in staff mode"));
//        event.setCancelled(true);
//    }
//
//    @EventHandler
//    public void onPlayerBlockPlace(BlockPlaceEvent event) {
//
//        if (!(plugin.getPlayers().get(event.getPlayer()).isHidden() || plugin.getPlayers().get(event.getPlayer()).isFrozed())) {
//            return;
//        }
//
//        event.getPlayer().sendMessage(color(plugin.getPlayers().get(event.getPlayer()).isFrozed() ? "You can't place blocks while frozed" : "You can't place blocks while in staff mode"));
//        event.setCancelled(true);
//    }

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

//            No funca esto atm
//            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL) && plugin.getPlayers().get(player).isFlying()) {
            if (plugin.getPlayers().get(player).isFlying()) {
                event.setCancelled(true);
                plugin.getPlayers().get(player).removeFly();
            }

            debug(String.format("%s received Damage %s", player, event.getCause()));
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

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (plugin.getPlayers().get(player).isHidden()) {
                event.setCancelled(true);
                debug(String.format("Cancelled FoodLevelChangeEvent to %s", player));
            }
        }
    }

    Map<Player, Long> toggleCooldown = new LinkedHashMap<>();
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (!player.getInventory().getItemInMainHand().hasItemMeta()) return;

        String name = plugin.getSetting().getString("staff-items." + e.getPlayer().getInventory().getItemInMainHand().getType().name() + ".name");

        if (toggleCooldown.containsKey(player)) {
            long cooldown = toggleCooldown.get(player);

            if (cooldown >= System.currentTimeMillis()) {
                debug("Updating the current time in millis.");
                e.setCancelled(true);
                debug(String.format("Cooldown time: %s", cooldown - System.currentTimeMillis()));
                return;
            }

            if (cooldown <= System.currentTimeMillis()) {
                debug("Removed the user from the list.");
                toggleCooldown.remove(e.getPlayer());
                // not return cuz I don't wanna double click to toggle :haha:
            }
        }

        debug(String.format("Item %s", name));
        if (name.equals(plugin.getSetting().getString("staff-items.LIME_DYE.name"))) {
            player.getInventory().setItem(4, items.getVanishEnableItem());

            toggleVanish.showStaff(player);
            player.sendMessage(color("&cVanish disabled!"));
            toggleCooldown.put(player, System.currentTimeMillis() + 1000);
            return;
        }

        if (name.equals(plugin.getSetting().getString("staff-items.GRAY_DYE.name"))) {
            player.getInventory().setItem(4, items.getVanishDisableItem());

            toggleVanish.hideStaff(player);
            player.sendMessage(color("&aVanish enabled!"));
            toggleCooldown.put(player, System.currentTimeMillis() + 1000);
            return;
        }
    }

}
