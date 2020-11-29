package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.FlyStatus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static club.rigox.vanillacore.utils.ConsoleUtils.debug;
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
