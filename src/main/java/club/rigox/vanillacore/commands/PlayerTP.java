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
        Player player = (Player) sender;
        player.sendMessage(color("&aLoading player list..."));
        plugin.getInventoryTeleport().openInventory(player);
        return false;
    }
}
