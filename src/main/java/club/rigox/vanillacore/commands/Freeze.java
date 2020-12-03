package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.scoreboard.ScoreBoardAPI;
import club.rigox.vanillacore.tasks.FreezeTask;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static club.rigox.vanillacore.utils.MsgUtils.color;


public class Freeze implements CommandExecutor {
    private final VanillaCore plugin;

    public Freeze(VanillaCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginCommand("freeze").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }

        Player staff = (Player) sender;

        if (!staff.hasPermission("staff.use")) {
            sender.sendMessage(color(plugin.getLang().getString("no-staff-permission")));
           return true;
        }

        if(args.length != 1){
            sender.sendMessage(color(plugin.getLang().getString("command-usage.base") + plugin.getLang().getString("command-usage.freeze")));
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(color(plugin.getLang().getString("player.offline")));
            return true;
        }

        if (plugin.getPlayers().get(target).isFrozed()) {
            sender.sendMessage(color(plugin.getLang().getString("player.already-frozed")));
            return true;
        }

        plugin.getPlayers().get(target).freeze(target, staff);
        target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 50));
        plugin.getInventoryUtils().storeAndClearInventory(target);
        target.sendMessage(color(String.format(plugin.getLang().getString("freeze.player-frozed"), staff.getName())));
        target.getInventory().setHelmet(new ItemStack(Material.ICE));
        plugin.getScoreBoardAPI().setScoreBoard(target, "freeze", true);

        return true;

    }
}
