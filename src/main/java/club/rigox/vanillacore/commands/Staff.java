package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.StaffItems;
import club.rigox.vanillacore.player.Vanish;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.vanillacore.utils.MsgUtils.color;


public class Staff implements CommandExecutor {
    private final VanillaCore plugin;
    private final Vanish vanish;
    private final StaffItems staffItems;

    public Staff(VanillaCore plugin) {
        this.plugin = plugin;
        vanish = new Vanish(plugin);
        staffItems = new StaffItems(plugin);
        plugin.getServer().getPluginCommand("staff").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }

        if (!(player.hasPermission("staff.use"))) {
            player.sendMessage(color(plugin.getLang().getString("no-staff-permission")));
            return true;
        }

        if(args.length == 1){
            if (args[0].equalsIgnoreCase("help")) {
                sendHelp(sender);
                return true;
            }
            sender.sendMessage(color("&cThis command doesn't exist!"));
            return true;
        }


        if (plugin.getPlayers().get(player).isHidden()) {
            player.sendMessage(color(plugin.getLang().getString("staff-mode.disabled")));

            vanish.showStaff(player);

            plugin.getPlayers().get(player).unHide();
            plugin.getInventoryUtils().restoreInventory(player);
            return true;
        }

        plugin.getPlayers().get(player).hide();
        plugin.getInventoryUtils().storeAndClearInventory(player);

        staffItems.giveStaffItems(player);

        vanish.hideStaff(player);

        player.sendMessage(color(plugin.getLang().getString("staff-mode.enabled")));
        return false;
    }


    private void sendHelp(CommandSender sender) {
        sender.sendMessage(color("&7&m------------------------------------------------"));
        sender.sendMessage(color("&c&lRigox Vanilla Core"));
        sender.sendMessage(color("&7&oCommand Help"));
        sender.sendMessage(color("&7&m------------------------------------------------"));
        sender.sendMessage(color("&8&l* &c/staff &f- Toggles staff mode."));
        sender.sendMessage(color("&7&m------------------------------------------------"));
    }

}
