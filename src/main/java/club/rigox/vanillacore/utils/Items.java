package club.rigox.vanillacore.utils;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Items {

    private final VanillaCore plugin;


    private ItemStack vanishEnableItem;
    private ItemStack vanishDisableItem;

    public Items(VanillaCore plugin) {

        this.plugin = plugin;

        //Enable item
        ItemStack i = new ItemStack(Material.GRAY_DYE);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(color(plugin.getSetting().getString("staff-items.GRAY_DYE.name")));
        i.setItemMeta(meta);

        this.vanishEnableItem = i;

        //Disable item
        ItemStack a = new ItemStack(Material.LIME_DYE);
        ItemMeta meta1 = a.getItemMeta();
        meta1.setDisplayName(color(plugin.getSetting().getString("staff-items.LIME_DYE.name")));
        a.setItemMeta(meta1);

        this.vanishDisableItem = a;

    }

    public ItemStack getVanishEnableItem() {
        return vanishEnableItem;
    }

    public ItemStack getVanishDisableItem() {
        return vanishDisableItem;
    }
}
