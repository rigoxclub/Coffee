package club.rigox.vanillacore.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandInterface {
        boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args);
}
