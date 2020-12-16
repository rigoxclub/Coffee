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

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return;
        }

        Player player = (Player) sender;
        if (args.length == 1) {
            if (!player.hasPermission("coffee.god.others")) {
                player.sendMessage(color(plugin.getLang().getString("permission.god-others")));
                return;
            }

            Player target = plugin.getServer().getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(color(plugin.getLang().getString("player.offline")));
                return;
            }

            if (target.equals(player)) {
                player.sendMessage(color(plugin.getLang().getString("god.self")));
                return;
            }

            if (plugin.getPlayers().get(target).hasGod()) {
                plugin.getPlayers().get(target).enableGod();
                target.sendMessage(color(String.format(plugin.getLang().getString("god.disabled-other"), player.getName())));
                return;
            }

            plugin.getPlayers().get(target).enableGod();
            target.sendMessage(color(String.format(plugin.getLang().getString("god.enabled-other"), player.getName())));
            return;
        }

        if (args.length >= 2) {
            player.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.god")));
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
