package club.rigox.vanillacore.utils;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Items {
    private final ItemStack vanishEnableItem;
    private final ItemStack vanishDisableItem;
//    private final ItemStack freezeItem;

    public Items(VanillaCore plugin) {

        // Vanish enable item
        ItemStack i = new ItemStack(Material.GRAY_DYE);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(color(plugin.getSetting().getString("staff-items.GRAY_DYE.name")));
        i.setItemMeta(meta);

        this.vanishEnableItem = i;

        // Vanish disable item
        ItemStack a = new ItemStack(Material.LIME_DYE);
        ItemMeta meta1 = a.getItemMeta();
        meta1.setDisplayName(color(plugin.getSetting().getString("staff-items.LIME_DYE.name")));
        a.setItemMeta(meta1);

        this.vanishDisableItem = a;

        // Freeze item
//        ItemStack freeze = new ItemStack(Material.ICE);
//        ItemMeta meta2 = freeze.getItemMeta();
//        meta2.setDisplayName(color(plugin.getSetting().getString("staff-items.ICE.name")));
//        freeze.setItemMeta(meta2);
//
//        this.freezeItem = freeze;

    }

    public ItemStack getVanishEnableItem() {
        return vanishEnableItem;
    }

    public ItemStack getVanishDisableItem() {
        return vanishDisableItem;
    }

//    public ItemStack getFreezeItem() {
//        return freezeItem;
//    }
}
