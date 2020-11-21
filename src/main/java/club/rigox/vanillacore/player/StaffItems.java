package club.rigox.vanillacore.player;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class StaffItems {
    private VanillaCore plugin;

    public StaffItems (VanillaCore plugin) {
        this.plugin = plugin;
    }


    public void giveStaffItems(Player player) {
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        player.getInventory().addItem(compass);
    }
}
