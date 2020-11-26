package club.rigox.vanillacore;

import club.rigox.vanillacore.listeners.StaffListener;
import club.rigox.vanillacore.models.PlayerModel;
import club.rigox.vanillacore.commands.Freeze;
import club.rigox.vanillacore.commands.Staff;
import club.rigox.vanillacore.commands.Unfreeze;
import club.rigox.vanillacore.listeners.PlayerListener;

import club.rigox.vanillacore.player.Inventory;
import club.rigox.vanillacore.utils.CommandHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.LinkedHashMap;
import java.util.Map;

public final class VanillaCore extends JavaPlugin {
    public static VanillaCore instance;

    private Map<Player, PlayerModel> players = new LinkedHashMap<>();

    private Inventory inventoryUtils;

    @Override
    public void onEnable() {
        instance = this;

        this.inventoryUtils = new Inventory(this);

        new PlayerListener(this);
        new StaffListener(this);
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    public Inventory getInventoryUtils() {
        return this.inventoryUtils;
    }

    public Map<Player, PlayerModel> getPlayers() {
        return players;
    }

    public void registerCommands() {

        new Freeze(this);
        new Unfreeze(this);
        new Staff(this);


//        A la verga con tu commandHandler, si haces mas sub-comandos usalo xd

//        CommandHandler handler = new CommandHandler();
//        handler.register("staff", new Staff(this));
//        handler.register("help", new Help());
//        getCommand("staff").setExecutor(handler);
//
//        this.getCommand("freeze").setExecutor(new Freeze(this));
//        this.getCommand("unfreeze").setExecutor(new Unfreeze(this));
    }
}
