package club.rigox.coffee.commands.users;

import club.rigox.coffee.Coffee;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

@CommandAlias("spawn")
public class Spawn extends BaseCommand {
    private Coffee plugin;

    public Spawn (Coffee plugin) {
        this.plugin = plugin;
    }

    @Default
    public void onSpawn(CommandSender sender) {
        if (plugin.getCommandUtils().isConsole(sender)) return;

        Location spawn = plugin.getCommandUtils().getSpawn(plugin.getSetting().getString("spawn.location"));

        Player player = (Player) sender;
        player.sendMessage(color("&aYou have been teleported to the Spawn!"));
        player.teleport(spawn);
    }

    @Subcommand("tp|teleport")
    @CommandPermission("coffee.spawn.others")
    public void onSpawnOthers(CommandSender sender, String[] args) {
        if (args.length == 1) {
            Player target = plugin.getServer().getPlayer(args[0]);
            Location spawn = plugin.getCommandUtils().getSpawn(plugin.getSetting().getString("spawn.location"));

            if (plugin.getCommandUtils().playerOffline(sender, target)) return;

            target.teleport(spawn);
            target.sendMessage(color(String.format("&aYou have been teleported to spawn by %s!", sender.getName())));
            return;
        }
        sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.spawn-others")));
    }
}
