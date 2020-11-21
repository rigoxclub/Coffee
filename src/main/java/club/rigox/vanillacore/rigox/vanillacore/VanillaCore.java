package club.rigox.vanillacore.rigox.vanillacore;

import club.rigox.vanillacore.Models.PlayerModel;
import club.rigox.vanillacore.commands.Help;
import club.rigox.vanillacore.commands.Staff;
import club.rigox.vanillacore.listeners.PlayerListener;
import club.rigox.vanillacore.utils.CommandHandler;

import club.rigox.vanillacore.player.Inventory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



import java.util.LinkedHashMap;
import java.util.Map;

public final class VanillaCore extends JavaPlugin {
    public static VanillaCore instance;

    private Map<Player, PlayerModel> staffMode = new LinkedHashMap<>();

    private Inventory inventoryUtils;

    @Override
    public void onEnable() {
        instance = this;

        this.inventoryUtils = new Inventory(this);

        new PlayerListener(this);
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    public Inventory getInventoryUtils() {
        return this.inventoryUtils;
    }

    public Map<Player, PlayerModel> getStaffMode() {
        return staffMode;
    }

    public void registerCommands() {
        CommandHandler handler = new CommandHandler();
        handler.register("staff", new Staff(this));
        handler.register("help", new Help());
        getCommand("staff").setExecutor(handler);
    }
}
