package club.rigox.coffee.commands.admin;

import club.rigox.coffee.Coffee;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

@CommandAlias("gamemode|gm")
@CommandPermission("coffee.gamemode")
public class Gamemode extends BaseCommand {
    private final Coffee plugin;

    public Gamemode (Coffee plugin) {
        this.plugin = plugin;
    }

    @Default
    public void onDefault(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.gamemode-others")));
            return;
        }

        sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.gamemode")));
    }

    @Subcommand("survival")
    @CommandPermission("coffee.gamemode.survival")
    @CommandCompletion("@players")
    public void survivalGamemode(CommandSender sender, String[] args) {
        if (args.length == 1) {
            Player target = plugin.getServer().getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(color("&cPlayer is offline!"));
                return;
            }

            if (target.equals(sender)) {
                sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.gamemode")));
                return;
            }

            if (target.getGameMode() == GameMode.SURVIVAL) {
                sender.sendMessage(color(plugin.getLang().getString("gamemode.already-survival")));
                return;
            }

            sender.sendMessage(color(String.format("&aSuccessfully set %s to survival mode.", target.getName())));
            target.sendMessage(color("&aYour gamemode has been set to survival!"));
            target.setGameMode(GameMode.SURVIVAL);
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.gamemode-others")));
            return;
        }

        Player player = (Player) sender;
        if (player.getGameMode() == GameMode.SURVIVAL) {
            sender.sendMessage(color("&cYou're already in survival mode!"));
            return;
        }

        player.sendMessage(color("&aYour gamemode has been updated to Survival!"));
        player.setGameMode(GameMode.SURVIVAL);
    }
}
