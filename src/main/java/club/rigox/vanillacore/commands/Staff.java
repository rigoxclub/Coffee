package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.utils.CommandInterface;
import club.rigox.vanillacore.utils.VanishUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class Staff implements CommandInterface {
    private final VanillaCore plugin;
    private final VanishUtils vanishUtils;

    public Staff(VanillaCore plugin) {
        this.plugin = plugin;
        vanishUtils = new VanishUtils(plugin);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (!(player.hasPermission("staff.use"))) {
            player.sendMessage(color("&cYou don't have enough permission to use this!"));
            return true;
        }

        if (plugin.getStaffMode().get(player)) {
            player.sendMessage(color("&cStaff mode disabled"));
            plugin.getStaffMode().replace(player, true, false);
            vanishUtils.showStaff(player);
            return true;
        }

        vanishUtils.hideStaff(player);
        player.sendMessage(color("&aStaff mode enabled"));
        plugin.getStaffMode().replace(player, false, true);
        return false;
    }

}
