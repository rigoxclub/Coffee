package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.utils.CommandInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Help implements CommandInterface {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (args.length > 1 && args[0].equalsIgnoreCase("help")) return false;

        player.sendMessage(color("&7&m------------------------------------------------"));
        player.sendMessage(color("&c&lRigox Vanilla Core"));
        player.sendMessage(color("&7&oCommand Help"));
        player.sendMessage(color("&7&m------------------------------------------------"));
        player.sendMessage(color("&8&l* &c/staff &f- Toggles staff mode."));
        player.sendMessage(color("&7&m------------------------------------------------"));
        return false;
    }

}
