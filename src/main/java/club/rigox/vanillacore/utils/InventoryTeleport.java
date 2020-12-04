package club.rigox.vanillacore.utils;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class InventoryTeleport {
    private VanillaCore plugin;
    
    public InventoryTeleport (VanillaCore plugin) {
        this.plugin = plugin;
    }
    
    public void openInventory(Player staff) {
        Inventory invList = Bukkit.createInventory(null, 54, "Listing all players...");

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
}
