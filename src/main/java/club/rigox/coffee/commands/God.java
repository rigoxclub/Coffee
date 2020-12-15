package club.rigox.coffee.commands;

import club.rigox.coffee.Coffee;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static club.rigox.coffee.utils.MsgUtils.color;

public class God implements CommandExecutor {
    private final Coffee plugin;

    public God (Coffee plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginCommand("god").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("vanillacore.god")) {
            player.sendMessage(color(plugin.getLang().getString("permission.general-no")));
            return true;
        }

        if (args.length == 1) {
            if (!player.hasPermission("vanillacore.god.others")) {
                player.sendMessage(color(plugin.getLang().getString("permission.god-others")));
                return true;
            }

            Player target = plugin.getServer().getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(color(plugin.getLang().getString("player.offline")));
                return true;
            }

            if (target.equals(player)) {
                player.sendMessage(color(plugin.getLang().getString("god.self")));
                return true;
            }

            if (plugin.getPlayers().get(target).hasGod()) {
                plugin.getPlayers().get(target).enableGod();
                target.sendMessage(color(String.format(plugin.getLang().getString("god.disabled-other"), player.getName())));
                return true;
            }

            plugin.getPlayers().get(target).enableGod();
            target.sendMessage(color(String.format(plugin.getLang().getString("god.enabled-other"), player.getName())));
            return true;
        }

        if (args.length >= 2) {
            player.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.god")));
            return true;
        }

        if (plugin.getPlayers().get(player).hasGod()) {
            plugin.getPlayers().get(player).disableGod();
            player.sendMessage(color(plugin.getLang().getString("god.disabled")));
            return true;
        }

        plugin.getPlayers().get(player).enableGod();
        player.sendMessage(color(plugin.getLang().getString("god.enabled")));
        return false;
    }
}
