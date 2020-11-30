package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.FlyStatus;
import club.rigox.vanillacore.player.StaffItems;
import club.rigox.vanillacore.player.Vanish;
import club.rigox.vanillacore.player.scoreboard.ScoreBoardAPI;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.vanillacore.utils.MsgUtils.color;


public class Staff implements CommandExecutor {
    private final VanillaCore plugin;
    private final Vanish vanish;
    private final StaffItems staffItems;
    private final ScoreBoardAPI scoreBoardAPI;
    private final FlyStatus flyStatus;

    public Staff(VanillaCore plugin) {
        this.plugin = plugin;
        vanish = new Vanish(plugin);
        staffItems = new StaffItems(plugin);
        plugin.getServer().getPluginCommand("staff").setExecutor(this);
        scoreBoardAPI = new ScoreBoardAPI(plugin);
        flyStatus = new FlyStatus(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }

        if (!(player.hasPermission("staff.use"))) {
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


        if (plugin.getPlayers().get(player).isHidden()) {
            vanish.showStaff(player);

            plugin.getPlayers().get(player).unVanish();
            plugin.getPlayers().get(player).unHide();

            plugin.getInventoryUtils().restoreInventory(player);

            scoreBoardAPI.setScoreBoard(player, "general", true);

            player.setGameMode(GameMode.SURVIVAL);
            flyStatus.disable(player);

            player.sendMessage(color(plugin.getLang().getString("staff-mode.disabled")));
            return true;
        }
        vanish.hideStaff(player);

        plugin.getPlayers().get(player).hide();
        plugin.getPlayers().get(player).vanish();

        plugin.getInventoryUtils().storeAndClearInventory(player);
        staffItems.giveStaffItems(player);

        scoreBoardAPI.setScoreBoard(player, "staff-mode", true);

        player.setGameMode(GameMode.ADVENTURE);
        flyStatus.enable(player);

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
