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
import java.util.List;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class TeleportGui implements Listener {
    private final VanillaCore plugin;
    private Inventory invList;
    private OfflinePlayer headOwner;
    
    public TeleportGui(VanillaCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public void openInventory(Player staff) {
        invList = Bukkit.createInventory(null, 54, "Listing all players...");

        int slot = 0;
        for(Player p: Bukkit.getOnlinePlayers()) {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(p);
            meta.setDisplayName(color(plugin.getSetting().getString("teleport-gui.title")
                    .replaceAll("%player%", p.getName())));
            head.setItemMeta(meta);
            invList.setItem(slot, head);

            headOwner = meta.getOwningPlayer();
            slot++;
        }

        staff.openInventory(invList);
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (!e.getInventory().equals(invList)) return;
        e.setCancelled(true);

        if (clickedItem == null || clickedItem.getType().equals(Material.AIR)) return;

        if (!headOwner.isOnline()) {
            player.closeInventory();
            player.sendMessage(color("&cPlayer has disconnected"));
            return;
        }
        player.teleport(headOwner.getPlayer());
        player.sendMessage(color(String.format("&8&l* &fTeleported to player %s", headOwner.getName())));

    }

}
