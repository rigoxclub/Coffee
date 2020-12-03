package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class God implements CommandExecutor {
    private final VanillaCore plugin;

    public God (VanillaCore plugin) {
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
            player.sendMessage(color(plugin.getLang().getString("no-permission")));
            return true;
        }

        if (args.length == 1) {
            if (!player.hasPermission("vanillacore.god.others")) {
                player.sendMessage(color("&cYou don't have permission to enable god on others."));
                return true;
            }

            Player target = plugin.getServer().getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(color("The player is not online!"));
                return true;
            }

            if (target.equals(player)) {
                player.sendMessage(color("&cOK WHAT THE FUCK? You can use only /GOD."));
                return true;
            }

            if (plugin.getPlayers().get(target).hasGod()) {
                plugin.getPlayers().get(target).enableGod();
                target.sendMessage(color(String.format("&cYour god has been deactivated by %s", player.getName())));
                return true;
            }

            plugin.getPlayers().get(target).enableGod();
            target.sendMessage(color(String.format("&aYour god has been activated by %s", player.getName())));
            return true;
        }

        if (plugin.getPlayers().get(player).hasGod()) {
            plugin.getPlayers().get(player).disableGod();
            player.sendMessage(color("&cGod disabled"));
            return true;
        }

        plugin.getPlayers().get(player).enableGod();
        player.sendMessage(color("&aGod enabled"));
        return false;
    }
}
