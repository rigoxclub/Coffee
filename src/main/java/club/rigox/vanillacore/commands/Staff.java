package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.Inventory;
import club.rigox.vanillacore.player.StaffItems;
import club.rigox.vanillacore.utils.CommandInterface;
import club.rigox.vanillacore.player.Vanish;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.vanillacore.utils.MsgUtils.color;
import static club.rigox.vanillacore.player.Inventory.storeAndClearInventory;
import static club.rigox.vanillacore.player.Inventory.restoreInventory;

public class Staff implements CommandInterface {
    private final VanillaCore plugin;
    private final Vanish vanish;
    private final StaffItems staffItems;

    public Staff(VanillaCore plugin) {
        this.plugin = plugin;
        vanish = new Vanish(plugin);
        staffItems = new StaffItems(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;


        if (!(player.hasPermission("staff.use"))) {
            player.sendMessage(color("&cYou don't have enough permission to use this!"));
            return true;
        }

        if (plugin.getStaffMode().get(player)) {
            player.sendMessage(color("&cStaff mode disabled"));
            plugin.getStaffMode().replace(player, true, false);
            vanish.showStaff(player);
            restoreInventory(player);
            return true;
        }

        vanish.hideStaff(player);
        storeAndClearInventory(player);
        staffItems.giveStaffItems(player);


        player.sendMessage(color("&aStaff mode enabled"));
        plugin.getStaffMode().replace(player, false, true);
        return false;
    }

}
