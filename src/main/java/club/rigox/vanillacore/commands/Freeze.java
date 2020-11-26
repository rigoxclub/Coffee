package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
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
    private FreezeTask task;

    public Freeze(VanillaCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginCommand("freeze").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color("&cOnly users can execute this command!"));
            return true;
        }

        if(args.length != 1){
            sender.sendMessage(color("&8&l* &fCommand usage: &b/freeze (player)"));
            return true;
        }
        Player target = plugin.getServer().getPlayer(args[0]);
        Player staff = (Player) sender;

        if (target == null) {
            sender.sendMessage(color("&cPlayer offline"));
            return true;
        }

        if (plugin.getPlayers().get(target).isFrozed()) {
            sender.sendMessage(color("&cPlayer is already frozed!"));
            return true;
        }

        plugin.getPlayers().get(target).freeze(target, staff);
        target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 50));
        plugin.getInventoryUtils().storeAndClearInventory(target);
        target.sendMessage(color(String.format("&8&l* &fYou have been frozed by &c%s", staff.getName())));
        target.getInventory().setHelmet(new ItemStack(Material.ICE));
        
        this.task = new FreezeTask(VanillaCore.instance, target, staff);

        return true;

    }
}
