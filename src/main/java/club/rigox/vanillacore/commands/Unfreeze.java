package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.scoreboard.ScoreBoardAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Unfreeze implements CommandExecutor {

    private final VanillaCore plugin;
    private final ScoreBoardAPI scoreBoardAPI;

    public Unfreeze (VanillaCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginCommand("unfreeze").setExecutor(this);
        scoreBoardAPI = new ScoreBoardAPI(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("unfreeze")) {
            Player staff = (Player) sender;

            if (!(sender instanceof Player)) {
                sender.sendMessage(color(plugin.getLang().getString("only-users")));
                return true;
            }

            if (!staff.hasPermission("staff.use")) {
                 staff.sendMessage(color(plugin.getLang().getString("no-staff-permission")));
                 return true;
            }

            if(args.length != 1){
                sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.unfreeze")));
                return true;
            }

            Player target = plugin.getServer().getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(color(plugin.getLang().getString("player.offline")));
                return true;
            }

            if (!plugin.getPlayers().get(target).isFrozed()) {
                sender.sendMessage(color(plugin.getLang().getString("player.not-frozen")));
                return true;
            }

            plugin.getPlayers().get(target).unfreeze();
            target.removePotionEffect(PotionEffectType.BLINDNESS);
            plugin.getInventoryUtils().restoreInventory(target);
            target.sendMessage(color(String.format(plugin.getLang().getString("unfreeze.player-unfrozed"), staff.getName())));
            scoreBoardAPI.setScoreBoard(target, "general", true);

            // TITLE AND SUBTITLE
            target.sendTitle(color(plugin.getSetting().getString("titles.unfreeze.title")),
                    color(String.format(plugin.getSetting().getString("titles.unfreeze.subtitle"), staff.getName())),
                    plugin.getSetting().getInt("titles.unfreeze.options.fadein"),
                    plugin.getSetting().getInt("titles.unfreeze.options.stay"),
                    plugin.getSetting().getInt("titles.unfreeze.options.fadeout"));;

            return true;
        }
        return false;
    }
}
