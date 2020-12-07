package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class CoffeeCore implements CommandExecutor {
    private VanillaCore plugin;

    public CoffeeCore (VanillaCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginCommand("coffee").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1 && args[0].equals("staff")) {
            player.sendMessage(color("&7&m------------------------------------------------"));
            player.sendMessage(color("&b&lCoffee Core"));
            player.sendMessage(color("&7&oCommand Help"));
            player.sendMessage(color("&7&m------------------------------------------------"));
            player.sendMessage(color("&8&l* &b/freeze (Player) &f- Freeze a player"));
            player.sendMessage(color("&8&l* &b/invsee (Player) &f- View inventory of a player"));
            player.sendMessage(color("&8&l* &b/playertp &f- Open a menu to teleport on players"));
            player.sendMessage(color("&8&l* &b/staff &f- Toggle staff mode"));
            player.sendMessage(color("&7&m------------------------------------------------"));
            return true;
        }

        player.sendMessage(color("&7&m------------------------------------------------"));
        player.sendMessage(color("&b&lCoffee Core"));
        player.sendMessage(color("&7&oCommand Help"));
        player.sendMessage(color("&7&m------------------------------------------------"));
        player.sendMessage(color("&8&l* &b/fly (Player) &f- Enable fly"));
        player.sendMessage(color("&8&l* &b/god (Player) &f- Enable god"));
        player.sendMessage(color("&8&l* &b/coffee staff &f- Display staff commands"));
        player.sendMessage(color("&7&m------------------------------------------------"));
        return false;
    }
}
