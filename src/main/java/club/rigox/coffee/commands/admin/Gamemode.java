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

    @Subcommand("survival|0")
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
                sender.sendMessage(color(plugin.getLang().getString("gamemode.survival.other.already")));
                return;
            }

            sender.sendMessage(color(String.format(plugin.getLang().getString("gamemode.survival.other.sender"), target.getName())));
            target.sendMessage(color(plugin.getLang().getString("gamemode.survival.set")));
            target.setGameMode(GameMode.SURVIVAL);
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.gamemode-others")));
            return;
        }

        Player player = (Player) sender;
        if (player.getGameMode() == GameMode.SURVIVAL) {
            sender.sendMessage(color(plugin.getLang().getString("gamemode.survival.already")));
            return;
        }

        player.sendMessage(color(plugin.getLang().getString("gamemode.survival.set")));
        player.setGameMode(GameMode.SURVIVAL);
    }

    @Subcommand("creative|1")
    @CommandPermission("coffee.gamemode.creative")
    @CommandCompletion("@players")
    public void creativeGamemode(CommandSender sender, String[] args) {
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

            if (target.getGameMode() == GameMode.CREATIVE) {
                sender.sendMessage(color(plugin.getLang().getString("gamemode.creative.other.already")));
                return;
            }

            sender.sendMessage(color(String.format(plugin.getLang().getString("gamemode.creative.other.sender"), target.getName())));
            target.sendMessage(color(plugin.getLang().getString("gamemode.creative.set")));
            target.setGameMode(GameMode.CREATIVE);
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.gamemode-others")));
            return;
        }

        Player player = (Player) sender;
        if (player.getGameMode() == GameMode.CREATIVE) {
            sender.sendMessage(color(plugin.getLang().getString("gamemode.creative.already")));
            return;
        }

        player.sendMessage(color(plugin.getLang().getString("gamemode.creative.set")));
        player.setGameMode(GameMode.CREATIVE);
    }

    @Subcommand("adventure|2")
    @CommandPermission("coffee.gamemode.adventure")
    @CommandCompletion("@players")
    public void adventureGamemode(CommandSender sender, String[] args) {
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

            if (target.getGameMode() == GameMode.ADVENTURE) {
                sender.sendMessage(color(plugin.getLang().getString("gamemode.adventure.other.already")));
                return;
            }

            sender.sendMessage(color(String.format(plugin.getLang().getString("gamemode.adventure.other.sender"), target.getName())));
            target.sendMessage(color(plugin.getLang().getString("gamemode.adventure.set")));
            target.setGameMode(GameMode.ADVENTURE);
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.gamemode-others")));
            return;
        }

        Player player = (Player) sender;
        if (player.getGameMode() == GameMode.ADVENTURE) {
            sender.sendMessage(color(plugin.getLang().getString("gamemode.adventure.already")));
            return;
        }

        player.sendMessage(color(plugin.getLang().getString("gamemode.adventure.set")));
        player.setGameMode(GameMode.ADVENTURE);
    }

    @Subcommand("spectator|3")
    @CommandPermission("coffee.gamemode.spectator")
    @CommandCompletion("@players")
    public void spectatorGamemode(CommandSender sender, String[] args) {
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

            if (target.getGameMode() == GameMode.SPECTATOR) {
                sender.sendMessage(color(plugin.getLang().getString("gamemode.spectator.other.already")));
                return;
            }

            sender.sendMessage(color(String.format(plugin.getLang().getString("gamemode.spectator.other.sender"), target.getName())));
            target.sendMessage(color(plugin.getLang().getString("gamemode.spectator.set")));
            target.setGameMode(GameMode.SPECTATOR);
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.gamemode-others")));
            return;
        }

        Player player = (Player) sender;
        if (player.getGameMode() == GameMode.SPECTATOR) {
            sender.sendMessage(color(plugin.getLang().getString("gamemode.spectator.already")));
            return;
        }

        player.sendMessage(color(plugin.getLang().getString("gamemode.spectator.set")));
        player.setGameMode(GameMode.SPECTATOR);
    }
}
