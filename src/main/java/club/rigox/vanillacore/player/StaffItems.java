package club.rigox.vanillacore.player;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static club.rigox.vanillacore.utils.MsgUtils.color;


public class StaffItems {
    private VanillaCore plugin;

    public StaffItems (VanillaCore plugin) {
        this.plugin = plugin;
    }


    public void giveStaffItems(Player player) {
        ItemStack vanishItem = new ItemStack(Material.LIME_DYE); //Creates the stack
        ItemMeta meta = vanishItem.getItemMeta();

        meta.setDisplayName(color(plugin.getSetting().getString("staff-items.LIME_DYE.name")));
        vanishItem.setItemMeta(meta);
        player.getInventory().setItem(4, vanishItem);
    }
}
