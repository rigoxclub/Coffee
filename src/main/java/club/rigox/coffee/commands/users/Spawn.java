package club.rigox.coffee.commands.users;

import club.rigox.coffee.Coffee;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("spawn")
public class Spawn extends BaseCommand {
    private Coffee plugin;

    public Spawn (Coffee plugin) {
        this.plugin = plugin;
    }

    @Default
    public void onSpawn(CommandSender sender) {

        if (plugin.getCommandUtils().isConsole(sender)) return;

        Player player = (Player) sender;
        Location spawn = plugin.getCommandUtils().getSpawn(plugin.getSetting().getString("spawn.location"));

        player.teleport(spawn);
    }
}
