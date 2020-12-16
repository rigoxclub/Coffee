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

@CommandAlias("freeze")
@CommandPermission("coffee.freeze")
public class Freeze extends BaseCommand {
    private final Coffee plugin;

    public Freeze(Coffee plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    public void onDefault(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return;
        }

        Player staff = (Player) sender;

        if(args.length != 1){
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.freeze")));
            return;
        }

        Player target = plugin.getServer().getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(color(plugin.getLang().getString("player.offline")));
            return;
        }

        if (target.equals(staff)) {
            sender.sendMessage(color(plugin.getLang().getString("freeze.self")));
            return;
        }

        if (target.hasPermission("coffee.freeze.bypass")) {
            sender.sendMessage(color(plugin.getLang().getString("freeze.player-bypass")));
            return;
        }

        if (plugin.getPlayers().get(target).isFrozed()) {
            plugin.getPlayers().get(target).unfreeze(target, staff);
            return;
        }

        plugin.getPlayers().get(target).freeze(target, staff);
    }
}
