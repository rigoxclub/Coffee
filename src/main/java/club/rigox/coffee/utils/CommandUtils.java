package club.rigox.coffee.utils;

import club.rigox.coffee.Coffee;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

public class CommandUtils {
    private final Coffee plugin;

    public CommandUtils(Coffee plugin) {
        this.plugin = plugin;
    }

    public boolean playerOffline(CommandSender sender, Player target) {
        if (target == null) {
            sender.sendMessage(color(plugin.getLang().getString("player.offline")));
            return true;
        }
        return false;
    }

    public boolean self(CommandSender sender, Player player) {
        if (sender.equals(player)) {
            sender.sendMessage(color("&cYou can't perform this action to yourself!"));
            return true;
        }
        return false;
    }

    public boolean isConsole(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return true;
        }
        return false;
    }

    public String setSpawn(Location location) {
        String worldName = location.getWorld().getName();
        
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        float yaw = location.getYaw();
        float pitch = location.getPitch();

        return String.format("%s:%s:%s:%s:%s:%s", worldName, x, y, z, yaw, pitch);
    }

    public Location getSpawn(String location) {
        String[] split = location.split(":");
        String world = split[0];

        double x = Double.parseDouble(split[1]);
        double y = Double.parseDouble(split[2]);
        double z = Double.parseDouble(split[3]);

        float yaw = Float.parseFloat(split[4]);
        float pitch = Float.parseFloat(split[5]);

        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }
}
