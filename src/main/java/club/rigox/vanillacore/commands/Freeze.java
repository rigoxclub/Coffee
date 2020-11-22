package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

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

            Player target = plugin.getServer().getPlayer(args[0]);
            Player staff = (Player) sender;

            if (target == null) {
                sender.sendMessage(color("&cPlayer offline"));
                return true;
            }

            plugin.getPlayerModel().get(target).freeze();
            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 50));
            plugin.getInventoryUtils().storeAndClearInventory(target);
            target.sendMessage(color(String.format("&8&l* &fYou have been frozed by &c%s", staff.getName())));
            target.getInventory().setHelmet(new ItemStack(Material.ICE));

            BukkitTask freezeSound = new BukkitRunnable() {
                @Override
                public void run() {
                    target.playSound(target.getLocation(), Sound.BLOCK_GLASS_BREAK, 3.0F, 1F);
                    target.playEffect(target.getLocation(), Effect.STEP_SOUND, Material.ICE);
                    target.sendTitle(color("&c&lYOU ARE FROZED!"), color(String.format("&ePlease follow %s instructions in the chat.", staff.getName())), 10, 40, 10);
                }
            }.runTaskTimer(plugin, 0, 3 * 20);

            return true;
        }
        return false;
    }
}
