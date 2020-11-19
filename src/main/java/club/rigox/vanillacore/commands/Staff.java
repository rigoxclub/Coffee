package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Staff implements CommandExecutor {
    private final VanillaCore plugin;

    public Staff(VanillaCore plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("staff")) {
            Player player = (Player) sender;

            if (sender.hasPermission("staff.use")) {

                if (plugin.getStaffMode().get(player)) {
                    plugin.getStaffMode().replace(player, true, false);
                    player.sendMessage(color("&cStaff mode disabled"));
                    return true;
                }

                plugin.getStaffMode().replace(player, false, true);
                player.sendMessage(color("&aStaff mode enabled!"));
                return true;
            }

            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(color("&aToma comando de ayuda :)"));
                return true;
            }
            return true;
        }

        return false;
    }
}
