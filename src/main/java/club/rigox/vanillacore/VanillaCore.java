package club.rigox.vanillacore;

import club.rigox.vanillacore.commands.Staff;
import club.rigox.vanillacore.listeners.PlayerListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;
import java.util.Map;

public final class VanillaCore extends JavaPlugin {
    public static VanillaCore instance;

    private Map<Player, Boolean> staffMode = new LinkedHashMap<>();
    @Override
    public void onEnable() {
        instance = this;

            this.getCommand("staff").setExecutor(new Staff(this));
        new PlayerListener(this);
    }

    @Override
    public void onDisable() {

    }

    public Map<Player, Boolean> getStaffMode() {
        return staffMode;
    }
}
