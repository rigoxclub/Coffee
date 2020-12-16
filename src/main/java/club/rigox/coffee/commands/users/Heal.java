package club.rigox.coffee.commands.users;

import club.rigox.coffee.Coffee;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

@CommandAlias("heal")
@CommandPermission("coffee.heal")
public class Heal extends BaseCommand {
    private Coffee plugin;

    public Heal(Coffee plugin) {
        this.plugin = plugin;
    }
    @Default
    @CommandCompletion("@players")
    public void onHeal(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (!sender.hasPermission("coffee.heal.others")) {
                sender.sendMessage(color(plugin.getLang().getString("permission.clear-others")));
                return;
            }

            Player target = plugin.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(color(plugin.getLang().getString("player.offline")));
                return;
            }

            if (target.equals(sender)) {
                sender.sendMessage(color("&cJust use /heal."));
                return;
            }

            target.setHealth(20.0);
            target.sendMessage(color(String.format("&aYou have been healed by %s", sender.getName())));
            sender.sendMessage(color(String.format("&aYou healed %s!", target.getName())));
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.heal-others")));
            return;
        }

        Player player = (Player) sender;
        player.setHealth(20.0);
        player.sendMessage(color("&aYou have been healed!"));
    }
}
