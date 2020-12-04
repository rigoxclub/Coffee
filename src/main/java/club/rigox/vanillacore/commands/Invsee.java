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
        Player player = (Player) sender;

        if (args.length == 1) {
            if (!player.hasPermission("vanillacore.invsee")) {
                player.sendMessage(color("&cYou don't have permission to enable fly on others."));
                return true;
            }

            Player target = plugin.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(color("Player is offline!"));
                return true;
            }

            if (target.equals(sender)) {
                sender.sendMessage(color("&cYou can't open your inventory!"));
                return true;
            }

            player.openInventory(target.getInventory());
            player.sendMessage(color(String.format("&aOpening inventory of %s", target.getName())));
            return true;
        }
        player.sendMessage("Hmm");
        plugin.getInventoryTeleport().openInventory(player);
        return false;
    }
}
