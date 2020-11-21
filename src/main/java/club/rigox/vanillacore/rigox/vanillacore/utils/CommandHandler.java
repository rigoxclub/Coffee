package club.rigox.vanillacore.rigox.vanillacore.utils;

import club.rigox.vanillacore.utils.CommandInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class CommandHandler implements CommandExecutor {

    // Aca se almacenan los comandos.
    private static HashMap<String, club.rigox.vanillacore.utils.CommandInterface> commands = new HashMap<String, club.rigox.vanillacore.utils.CommandInterface>();

    // Para registrar los comandos en el onEnable() se usa esto
    public void register(String name, club.rigox.vanillacore.utils.CommandInterface cmd) {
        commands.put(name, cmd);
    }

    // Esto va para verificar si una string existe o non
    public boolean exists(String name) {
        return commands.containsKey(name);
    }

    // Getter method for the Executor(? Mames ni copiando codigo entiendo :chupalo:
    public CommandInterface getExecutor(String name) {
        return commands.get(name);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color("&cSolo jugadores pueden ejecutar este comando."));
            return true;
        }

        if (args.length == 0) { getExecutor("staff").onCommand(sender, command, label, args);
            return true;
        }

        if (exists(args[0])) {
            getExecutor(args[0]).onCommand(sender, command, label, args);
            return true;
        } else {
            sender.sendMessage(color("&cThat command doesn't exists!"));
        }

        return false;
    }

}
