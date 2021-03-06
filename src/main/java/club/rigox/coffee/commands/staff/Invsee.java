package club.rigox.coffee.commands.staff;

import club.rigox.coffee.Coffee;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

@CommandAlias("invsee")
@CommandPermission("coffee.invsee")
public class Invsee extends BaseCommand {
    private final Coffee plugin;

    public Invsee (Coffee plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    public void onDefault(CommandSender sender, String[] args) {
        if (plugin.getCommandUtils().isConsole(sender)) return;

        Player player = (Player) sender;
        if(args.length != 1){
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.invsee")));
            return;
        }

        Player target = plugin.getServer().getPlayer(args[0]);

        if (plugin.getCommandUtils().playerOffline(sender, target))
        if (plugin.getCommandUtils().self(player, target)) return;

        player.openInventory(target.getInventory());
        player.sendMessage(color(String.format(plugin.getLang().getString("invsee.open"), target.getName())));
    }
}
