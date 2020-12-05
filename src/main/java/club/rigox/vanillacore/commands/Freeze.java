package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Material;
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

        if (!staff.hasPermission("vanillacore.freeze")) {
            sender.sendMessage(color(plugin.getLang().getString("permission.general-no")));
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

        if (target.equals(staff)) {
            sender.sendMessage(color(plugin.getLang().getString("freeze.self")));
            return true;
        }

        if (target.hasPermission("vanillacore.freeze.bypass")) {
            sender.sendMessage(color(plugin.getLang().getString("freeze.player-bypass")));
            return true;
        }

        if (plugin.getPlayers().get(target).isFrozed()) {
            plugin.getPlayers().get(target).unfreeze(target, staff);
            return true;
        }

        plugin.getPlayers().get(target).freeze(target, staff);
        return true;
    }
}
