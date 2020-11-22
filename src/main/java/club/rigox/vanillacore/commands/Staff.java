package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.StaffItems;
import club.rigox.vanillacore.utils.CommandInterface;
import club.rigox.vanillacore.player.Vanish;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.vanillacore.utils.MsgUtils.color;


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

        if (plugin.getPlayerModel().get(player).isHidden()) {
            player.sendMessage(color("&cStaff mode disabled"));

            plugin.getPlayerModel().get(player).unHide();

            vanish.showStaff(player);
            plugin.getInventoryUtils().restoreInventory(player);
            return true;
        }

        plugin.getInventoryUtils().storeAndClearInventory(player);
        staffItems.giveStaffItems(player);

        plugin.getPlayerModel().get(player).hide();

        vanish.hideStaff(player);

        player.sendMessage(color("&aStaff mode enabled"));
        return false;
    }

}
