package club.rigox.vanillacore;

import club.rigox.vanillacore.commands.Help;
import club.rigox.vanillacore.commands.Staff;
import club.rigox.vanillacore.listeners.PlayerListener;
import club.rigox.vanillacore.utils.CommandHandler;
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

        new PlayerListener(this);
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    public Map<Player, Boolean> getStaffMode() {
        return staffMode;
    }

    public void registerCommands() {
        CommandHandler handler = new CommandHandler();
        handler.register("staff", new Staff(this));
        handler.register("help", new Help());
        getCommand("staff").setExecutor(handler);
    }
}
