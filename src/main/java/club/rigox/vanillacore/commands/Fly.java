package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.FlyStatus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Fly implements CommandExecutor {
    private final VanillaCore plugin;
    private final FlyStatus flyStatus;

    public Fly (VanillaCore plugin) {
        this.plugin = plugin;
        flyStatus = new FlyStatus(plugin);
        plugin.getServer().getPluginCommand("fly").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("vanillacore.fly")) {
            player.sendMessage(color(plugin.getLang().getString("no-permission")));
            return true;
        }

        if (args.length == 1) {
            if (!player.hasPermission("vanillacore.fly.others")) {
                player.sendMessage(color("&cYou don't have permission to enable fly on others."));
                return true;
            }

            Player target = plugin.getServer().getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(color("The player is not online!"));
                return true;
            }

            if (target.equals(player)) {
                player.sendMessage(color("&cOK WHAT THE FUCK? You can use only /FLY."));
            }

            if (target.isFlying()) {
                flyStatus.disable(target);
                target.sendMessage(color(String.format("&cYour fly has been deactivated by %s", player.getName())));
                return true;
            }

            flyStatus.enable(target);
            target.sendMessage(color(String.format("&aYour fly has been activated by %s", player.getName())));
            return true;
        }

        if (player.isFlying()) {
            flyStatus.disable(player);
            player.sendMessage(color("&cFly disabled"));

            return true;
        }

        flyStatus.enable(player);
        player.sendMessage(color("&aFly enabled"));
        return false;
    }
}
