package club.rigox.vanillacore.player;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.entity.Player;

public class FlyStatus {
    private final VanillaCore plugin;

    public FlyStatus (VanillaCore plugin) {
        this.plugin = plugin;
    }

    public void enable(Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
        plugin.getFlyingPlayers().add(player);

    }

    public void disable(Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
    }
}
