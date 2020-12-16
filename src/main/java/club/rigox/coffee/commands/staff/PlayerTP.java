package club.rigox.coffee.commands.staff;

import club.rigox.coffee.Coffee;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

@CommandAlias("playertp")
@CommandPermission("coffee.playertp")
public class PlayerTP extends BaseCommand {
    private final Coffee plugin;

    public PlayerTP (Coffee plugin) {
        this.plugin = plugin;
    }

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return;
        }

        Player player = (Player) sender;
        if (args.length >= 1) {
            player.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.playertp")));
            return;
        }

        player.sendMessage(color("&aLoading player list..."));
        plugin.getInventoryTeleport().openInventory(player);
    }
}
