package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class PlayerTP implements CommandExecutor {
    private final VanillaCore plugin;

    public PlayerTP (VanillaCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginCommand("playertp").setExecutor(this);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }

        Player player = (Player) sender;
        if (args.length >= 1) {
            player.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.playertp")));
            return true;
        }

        player.sendMessage(color("&aLoading player list..."));
        plugin.getInventoryTeleport().openInventory(player);
        return false;
    }
}
