package club.rigox.coffee.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;

import static club.rigox.coffee.utils.MsgUtils.color;

@CommandAlias("coffee")
public class CoffeeCore extends BaseCommand {

    @Default
    public void onDefault(CommandSender sender) {
        header(sender);
        sender.sendMessage(color("&8&l* &b/fly (Player) &f- Enable fly"));
        sender.sendMessage(color("&8&l* &b/god (Player) &f- Enable god"));
        sender.sendMessage(color("&8&l* &b/coffee staff &f- Display staff commands"));
        sender.sendMessage(color("&7&m------------------------------------------------"));
        footer(sender);
    }

    @Subcommand("staff")
    public void staffCommand(CommandSender sender) {
        header(sender);
        sender.sendMessage(color("&8&l* &b/freeze (Player) &f- Freeze a player"));
        sender.sendMessage(color("&8&l* &b/invsee (Player) &f- View inventory of a player"));
        sender.sendMessage(color("&8&l* &b/playertp &f- Open a menu to teleport on players"));
        sender.sendMessage(color("&8&l* &b/staff &f- Toggle staff mode"));
        footer(sender);
    }

    @Subcommand("admin")
    public void adminCommand(CommandSender sender) {
        header(sender);
        sender.sendMessage(color("&8&l* &b/clear (Player) &f- Clear your inventory or to a player"));
        footer(sender);
    }

    public void header(CommandSender sender) {
        sender.sendMessage(color("&7&m------------------------------------------------"));
        sender.sendMessage(color("&b&lCoffee Core"));
        sender.sendMessage(color("&7&oCommand Help"));
        sender.sendMessage(color("&7&m------------------------------------------------"));
    }

    public void footer(CommandSender sender) {
        sender.sendMessage(color("&7&m------------------------------------------------"));
    }
}
