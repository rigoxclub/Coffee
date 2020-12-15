package club.rigox.coffee.player.gui;

import club.rigox.coffee.Coffee;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static club.rigox.coffee.utils.MsgUtils.*;

public class TeleportGUI implements Listener {
    private final Coffee plugin;
    private final Map<Integer, Player> listUsers = new LinkedHashMap<>();

    private Inventory invList;

    public TeleportGUI(Coffee plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public void openInventory(Player staff) {
        invList = Bukkit.createInventory(null, 54, "Player Teleport");

        int slot = 0;
        for(Player p: Bukkit.getOnlinePlayers()) {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(p);
            meta.setDisplayName(parseField(plugin.getSetting().getString("teleport-gui.title"), p.getPlayer()));
            ArrayList<String> lore = (ArrayList<String>) parseFieldList(plugin.getSetting().getStringList(color("teleport-gui.lore")), p.getPlayer());
            meta.setLore(lore);
            head.setItemMeta(meta);
            invList.setItem(slot, head);
            listUsers.put(slot, p);
            slot++;
        }

        staff.openInventory(invList);
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(invList)) {
            return;
        }
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        Player target = listUsers.get(e.getRawSlot());
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.equals(Material.AIR)) {
            return;
        }

        if (target.equals(player)) {
            player.closeInventory();
            player.sendMessage(color("&cYou can't teleport on yourself!"));
            return;
        }

        player.teleport(target);
        player.sendMessage(color(String.format("&8&l* &fYou have been teleported to &b%s", target.getName())));
    }

}
