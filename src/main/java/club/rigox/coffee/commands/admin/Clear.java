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
    private Coffee plugin;

    public Clear(Coffee plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    public void onDefault(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (plugin.getPlayers().get(player).hasGod())
        player.getInventory().clear();
        player.sendMessage(color("&aYour inventory has been cleared!"));
    }
}
