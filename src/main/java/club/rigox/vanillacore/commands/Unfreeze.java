package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Unfreeze implements CommandExecutor {

    private final VanillaCore plugin;

    public Unfreeze (VanillaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("unfreeze")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(color("&cOnly users can execute this command!"));
                return true;
            }

            Player target = plugin.getServer().getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(color("&cPlayer offline"));
                return true;
            }

            plugin.getStaffMode().get(target).unfreeze();
            target.removePotionEffect(PotionEffectType.BLINDNESS);
            plugin.getInventoryUtils().restoreInventory(target);

            return true;
        }
        return false;
    }
}
