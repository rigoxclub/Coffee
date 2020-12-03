package club.rigox.vanillacore.listeners;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.ToggleVanish;
import club.rigox.vanillacore.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;

import static club.rigox.vanillacore.utils.ConsoleUtils.debug;
import static club.rigox.vanillacore.utils.MsgUtils.color;

public class StaffItemsListener implements Listener {
    private final VanillaCore plugin;

    private final ToggleVanish toggleVanish;
    private final Items items;

    public StaffItemsListener (VanillaCore plugin) {
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
            plugin.getScoreBoardAPI().setScoreBoard(player, "staff-mode", true);
            return;
        }

        if (name.equals(plugin.getSetting().getString("staff-items.GRAY_DYE.name"))) {
            player.getInventory().setItem(4, items.getVanishDisableItem());

            toggleVanish.hideStaff(player);
            player.sendMessage(color("&aVanish enabled!"));
            toggleCooldown.put(player, System.currentTimeMillis() + 1000);
            plugin.getScoreBoardAPI().setScoreBoard(player, "staff-mode", true);
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();

        if (!player.getInventory().getItemInMainHand().hasItemMeta()) return;

        String name = plugin.getSetting().getString("staff-items." + e.getPlayer().getInventory().getItemInMainHand().getType().name() + ".name");

        if (name.equals(plugin.getSetting().getString("staff-items.ICE.name"))) {
            if (e.getRightClicked() instanceof Player) {
//                Bukkit.dispatchCommand(player, "freeze ", e.getRightClicked().getName());
            }
        }
    }
}
