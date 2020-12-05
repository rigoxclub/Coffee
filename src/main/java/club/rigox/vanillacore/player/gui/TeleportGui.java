package club.rigox.vanillacore.player.gui;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static club.rigox.vanillacore.utils.ConsoleUtils.debug;
import static club.rigox.vanillacore.utils.MsgUtils.color;

public class TeleportGui implements Listener {
    private final VanillaCore plugin;
    private Inventory invList;
    private Map<Player, Integer> listUsers = new LinkedHashMap<>();

    // TODO Make players on leave delete from the invList
    public TeleportGui(VanillaCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public void openInventory(Player staff) {
        invList = Bukkit.createInventory(null, 54, "Player Teleport");

        int slot = 0;
        for(Player p: Bukkit.getOnlinePlayers()) {
            if (p.equals(staff)) {
                return;
            }

            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(p);
            meta.setDisplayName(color(plugin.getSetting().getString("teleport-gui.title")
                    .replaceAll("%player%", p.getName())));
            head.setItemMeta(meta);
            invList.setItem(slot, head);
            listUsers.put(p, slot);
            slot++;
        }

        staff.openInventory(invList);
    }

    public Map<Player, Integer> getListUsers() {
        return listUsers;
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(invList)) {
            return;
        }
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
//        Player target = listUsers.get(e.getRawSlot());
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.equals(Material.AIR)) {
            return;
        }

        player.teleport(listUsers.get(e.getRawSlot());
        player.sendMessage(color(String.format("&8&l* &fYou have been teleported to &b%s", target.getName())));
    }

}
