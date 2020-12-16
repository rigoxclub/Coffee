package club.rigox.coffee.commands.staff;

import club.rigox.coffee.Coffee;
import club.rigox.coffee.player.ToggleVanish;
import club.rigox.coffee.utils.Items;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;


public class Staff implements CommandExecutor {
    private final Coffee plugin;
    private final ToggleVanish toggleVanish;
    private final Items items;

    public Staff(Coffee plugin) {
        this.plugin = plugin;
        toggleVanish = new ToggleVanish(plugin);
        items = new Items(plugin);
        plugin.getServer().getPluginCommand("staff").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }

        if (!(player.hasPermission("vanillacore.staff"))) {
            player.sendMessage(color(plugin.getLang().getString("no-staff-permission")));
            return true;
        }

        if(args.length == 1){
            if (args[0].equalsIgnoreCase("help")) {
                sendHelp(sender);
                return true;
            }
            sender.sendMessage(color("&cThis command doesn't exist!"));
            return true;
        }


        if (plugin.getPlayers().get(player).hasGod()) {
            toggleVanish.showStaff(player);

            plugin.getPlayers().get(player).unVanish();
            plugin.getPlayers().get(player).disableGod();

            plugin.getInventoryUtils().restoreInventory(player);
            plugin.getScoreBoardAPI().setScoreBoard(player, "general", true);

            player.setGameMode(GameMode.SURVIVAL);

            player.sendMessage(color(plugin.getLang().getString("staff-mode.disabled")));
            return true;
        }
        
        toggleVanish.hideStaff(player);

        plugin.getPlayers().get(player).enableGod();
        plugin.getPlayers().get(player).vanish();

        plugin.getInventoryUtils().storeAndClearInventory(player);
        items.giveItems(player);

        plugin.getScoreBoardAPI().setScoreBoard(player, "staff-mode", true);

        player.setGameMode(GameMode.ADVENTURE);

        player.sendMessage(color(plugin.getLang().getString("staff-mode.enabled")));
        return false;
    }


    private void sendHelp(CommandSender sender) {
        sender.sendMessage(color("&7&m------------------------------------------------"));
        sender.sendMessage(color("&c&lRigox Vanilla Core"));
        sender.sendMessage(color("&7&oCommand Help"));
        sender.sendMessage(color("&7&m------------------------------------------------"));
        sender.sendMessage(color("&8&l* &c/staff &f- Toggles staff mode."));
        sender.sendMessage(color("&7&m------------------------------------------------"));
    }

}
