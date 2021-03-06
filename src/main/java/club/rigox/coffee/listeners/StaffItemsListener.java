package club.rigox.coffee.listeners;

import club.rigox.coffee.Coffee;
import club.rigox.coffee.player.ToggleVanish;
import club.rigox.coffee.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.LinkedHashMap;
import java.util.Map;

import static club.rigox.coffee.utils.ConsoleUtils.debug;
import static club.rigox.coffee.utils.MsgUtils.color;

public class StaffItemsListener implements Listener {
    private final Coffee plugin;

    private final ToggleVanish toggleVanish;
    private final Items items;

    public StaffItemsListener (Coffee plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

        toggleVanish = new ToggleVanish(plugin);
        items = new Items(plugin);
    }

    Map<Player, Long> toggleCooldown = new LinkedHashMap<>();

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (!player.getInventory().getItemInMainHand().hasItemMeta()) return;

        if (plugin.getPlayers().get(player).hasGod() && e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }

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
            toggleCooldown.put(player, System.currentTimeMillis() + 150);
            plugin.getScoreBoardAPI().setScoreBoard(player, "staff-mode", true);
            return;
        }

        if (name.equals(plugin.getSetting().getString("staff-items.GRAY_DYE.name"))) {
            player.getInventory().setItem(4, items.getVanishDisableItem());

            toggleVanish.hideStaff(player);
            player.sendMessage(color("&aVanish enabled!"));
            toggleCooldown.put(player, System.currentTimeMillis() + 150);
            plugin.getScoreBoardAPI().setScoreBoard(player, "staff-mode", true);
        }

        if (name.equals(plugin.getSetting().getString("staff-items.FIREWORK_ROCKET.name"))) {
            player.setVelocity(player.getLocation().getDirection().multiply(1.5));
            toggleCooldown.put(player, System.currentTimeMillis() + 5);
        }

        if (name.equals(plugin.getSetting().getString("staff-items.COMPASS.name"))) {
            player.sendMessage(color("&aLoading player list..."));
            plugin.getInventoryTeleport().openInventory(player);
            toggleCooldown.put(player, System.currentTimeMillis() + 1000);
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
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

        if (e.getRightClicked() instanceof Player) {
            Player target = (Player) e.getRightClicked();
            if (name.equals(plugin.getSetting().getString("staff-items.ICE.name"))) {
                if (plugin.getPlayers().get(target).isFrozed()) {
                    plugin.getPlayers().get(target).unfreeze(target, player);
                    toggleCooldown.put(player, System.currentTimeMillis() + 1000);
                    return;
                }
                plugin.getPlayers().get(target).freeze(target, player);
                toggleCooldown.put(player, System.currentTimeMillis() + 1000);
            }

            if (name.equals(plugin.getSetting().getString("staff-items.BOOK.name"))) {
                player.openInventory(target.getInventory());
                player.sendMessage(color(String.format("&aOpening inventory of %s", target.getName())));
            }
        }
    }
}
