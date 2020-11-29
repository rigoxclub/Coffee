package club.rigox.vanillacore.utils;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Items {

    private final VanillaCore plugin;

    private final ItemStack vanishEnableItem;
    private final ItemStack vanishDisableItem;

    public Items(VanillaCore plugin) {

        this.plugin = plugin;

        //Enable item
        vanishEnableItem = new ItemStack(Material.GRAY_DYE);
        ItemMeta meta = vanishEnableItem.getItemMeta();
        meta.setDisplayName(color(plugin.getSetting().getString("staff-items.GRAY_DYE.name")));
        vanishEnableItem.setItemMeta(meta);

        //Disable item
        vanishDisableItem = new ItemStack(Material.LIME_DYE);
        ItemMeta meta1 = vanishDisableItem.getItemMeta();
        meta1.setDisplayName(color(plugin.getSetting().getString("staff-items.LIME_DYE.name")));
        vanishDisableItem.setItemMeta(meta1);

    }

    public ItemStack getVanishEnableItem() {
        return vanishEnableItem;
    }

    public ItemStack getVanishDisableItem() {
        return vanishDisableItem;
    }
}
