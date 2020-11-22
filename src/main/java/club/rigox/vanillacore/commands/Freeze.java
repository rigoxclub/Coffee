package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Freeze implements CommandExecutor {
    private final VanillaCore plugin;

    public Freeze (VanillaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("freeze")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(color("&cOnly users can execute this command!"));
                return true;
            }

            Player staff = (Player) sender;
            Player target = plugin.getServer().getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(color("&cPlayer offline"));
                return true;
            }

            plugin.getStaffMode().get(target).freeze();
            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 1));
            plugin.getInventoryUtils().storeAndClearInventory(target);
            return true;
        }
        return false;
    }
}
