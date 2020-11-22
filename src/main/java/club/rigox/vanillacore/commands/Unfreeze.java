package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Unfreeze implements CommandExecutor {

    private final VanillaCore plugin;
    private Freeze freeze;

    public Unfreeze (VanillaCore plugin) {
        this.plugin = plugin;
        freeze = new Freeze(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("unfreeze")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(color("&cOnly users can execute this command!"));
                return true;
            }

            Player target = plugin.getServer().getPlayer(args[0]);
            Player staff = (Player) sender;
            if (target == null) {
                sender.sendMessage(color("&cPlayer offline"));
                return true;
            }

            plugin.getPlayerModel().get(target).unfreeze();
            target.removePotionEffect(PotionEffectType.BLINDNESS);
            plugin.getInventoryUtils().restoreInventory(target);
            target.sendMessage(color(String.format("&8&l* &fYou have been unfrozed by &c%s", staff.getName())));
            target.sendTitle(color("&a&lYOU HAVE BEEN UNFROZED!"), color(String.format("&eThank you for your time.", staff.getName())), 10, 40, 10);

            return true;
        }
        return false;
    }
}
