package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Invsee implements CommandExecutor {
    private final VanillaCore plugin;

    public Invsee (VanillaCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginCommand("invsee").setExecutor(this);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }

        Player player = (Player) sender;
        if(args.length != 1){
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.invsee")));
            return true;
        }

        if (!player.hasPermission("vanillacore.invsee")) {
            player.sendMessage(color(plugin.getLang().getString("permission.general-no")));
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(color(plugin.getLang().getString("player.offline")));
            return true;
        }

        if (target.equals(sender)) {
            sender.sendMessage(color(plugin.getLang().getString("invsee.self")));
            return true;
        }

        player.openInventory(target.getInventory());
        player.sendMessage(color(String.format(plugin.getLang().getString("invsee.open"), target.getName())));
        return false;
    }
}
