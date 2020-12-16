package club.rigox.coffee.commands.users;

import club.rigox.coffee.Coffee;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

@CommandAlias("god")
public class God extends BaseCommand {
    private final Coffee plugin;

    public God (Coffee plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandPermission("coffee.god")
    @CommandCompletion("@players")
    public void onDefault(CommandSender sender, String[] args) {
        if (plugin.getCommandUtils().isConsole(sender)) return;

        Player player = (Player) sender;
        if (args.length == 1) {
            if (!plugin.getCommandUtils().hasPermission(sender, "coffee.god.others")) return;

            Player target = plugin.getServer().getPlayer(args[0]);

            if (plugin.getCommandUtils().playerOffline(sender, target)) return;
            if (plugin.getCommandUtils().self(player, target)) return;

            if (plugin.getPlayers().get(target).hasGod()) {
                plugin.getPlayers().get(target).enableGod();
                target.sendMessage(color(String.format(plugin.getLang().getString("god.disabled-other"), player.getName())));
                return;
            }

            plugin.getPlayers().get(target).enableGod();
            target.sendMessage(color(String.format(plugin.getLang().getString("god.enabled-other"), player.getName())));
            return;
        }

        if (plugin.getPlayers().get(player).hasGod()) {
            plugin.getPlayers().get(player).disableGod();
            player.sendMessage(color(plugin.getLang().getString("god.disabled")));
            return;
        }

        plugin.getPlayers().get(player).enableGod();
        player.sendMessage(color(plugin.getLang().getString("god.enabled")));
    }
}
