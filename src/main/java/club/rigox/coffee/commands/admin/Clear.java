package club.rigox.coffee.commands.admin;

import club.rigox.coffee.Coffee;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

@CommandAlias("clear")
@CommandPermission("coffee.clear")
public class Clear extends BaseCommand {
    private final Coffee plugin;

    public Clear(Coffee plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    public void onDefault(CommandSender sender, String[] args) {
        if (args.length == 1) {
            Player target = plugin.getServer().getPlayer(args[0]);

            if (!plugin.getCommandUtils().hasPermission(sender, "coffee.clear.others")) return;
            if (plugin.getCommandUtils().playerOffline(sender, target)) return;

            if (sender.equals(target)) {
                sender.sendMessage("&cYou could just use /clear");
                return;
            }

            if (plugin.getPlayers().get(target).isOnStaffMode()) {
                sender.sendMessage(color(String.format("You can't clear %s inventory! Is on staff mode.", target.getName())));
                return;
            }

            target.getInventory().clear();
            target.sendMessage(color("&cYour inventory has been cleared."));
            sender.sendMessage(color(String.format("&aYou cleared %s's inventory!", target.getName())));
            return;
        }

        if (plugin.getCommandUtils().isConsole(sender)) return;

        Player player = (Player) sender;
        if (plugin.getPlayers().get(player).isOnStaffMode()) {
            player.sendMessage(color("&cYou can't clear your inventory while on Staff Mode!"));
            return;
        }

        player.getInventory().clear();
        player.sendMessage(color("&aYour inventory has been cleared!"));
    }
}
