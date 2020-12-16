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
            if (plugin.getCommandUtils().hasPermission(sender, "coffee.heal.others")) return;

            Player target = plugin.getServer().getPlayer(args[0]);
            if (plugin.getCommandUtils().playerOffline(sender, target)) return;
            if (plugin.getCommandUtils().self(sender, target)) return;

            target.setHealth(20.0);
            target.sendMessage(color(String.format("&aYou have been healed by %s", sender.getName())));
            sender.sendMessage(color(String.format("&aYou healed %s!", target.getName())));
            return;
        }

        if (plugin.getCommandUtils().isConsole(sender)) return;

        Player player = (Player) sender;
        player.setHealth(20.0);
        player.sendMessage(color("&aYou have been healed!"));
    }
}
