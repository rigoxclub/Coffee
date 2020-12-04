package club.rigox.vanillacore.player.gui;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class TeleportGui implements Listener {
    private Inventory invList;
    
    public TeleportGui(VanillaCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public void openInventory(Player staff) {
        invList = Bukkit.createInventory(null, 54, "Listing all players...");

        int slot = 0;
        for(Player p: Bukkit.getOnlinePlayers()) {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(p);
            meta.setDisplayName(color(String.format("&b%s", p.getName())));
            head.setItemMeta(meta);
            invList.setItem(slot, head);
            slot++;
        }

        staff.openInventory(invList);
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(invList)) return;

        e.setCancelled(true);
    }

    public Inventory getInvList() {
        return invList;
    }
}
