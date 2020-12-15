package club.rigox.vanillacore.player;

import club.rigox.vanillacore.Coffee;
import org.bukkit.entity.Player;

import static club.rigox.vanillacore.utils.ConsoleUtils.debug;

public class FlyStatus {
    private final Coffee plugin;

    public FlyStatus (Coffee plugin) {
        this.plugin = plugin;
    }

    public void enable(Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
        plugin.getPlayers().get(player).setFly();
        debug(String.format("Player %s has flying value to %s", player, plugin.getPlayers().get(player).isFlying()));
    }

    public void disable(Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
    }
}
