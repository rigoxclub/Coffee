package club.rigox.coffee;

import club.rigox.coffee.commands.CoffeeCore;
import club.rigox.coffee.commands.God;
import club.rigox.coffee.commands.admin.Clear;
import club.rigox.coffee.commands.admin.Gamemode;
import club.rigox.coffee.commands.staff.Freeze;
import club.rigox.coffee.commands.staff.Invsee;
import club.rigox.coffee.commands.staff.PlayerTP;
import club.rigox.coffee.commands.staff.Staff;
import club.rigox.coffee.hooks.LuckpermsHook;
import club.rigox.coffee.hooks.Placeholders;
import club.rigox.coffee.listeners.PlayerListener;
import club.rigox.coffee.listeners.StaffItemsListener;
import club.rigox.coffee.listeners.StaffListener;
import club.rigox.coffee.models.PlayerModel;
import club.rigox.coffee.player.Inventory;
import club.rigox.coffee.player.gui.TeleportGUI;
import club.rigox.coffee.player.scoreboard.ScoreBoardAPI;
import co.aikar.commands.PaperCommandManager;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static club.rigox.coffee.utils.ConsoleUtils.warn;

public final class Coffee extends JavaPlugin {
    public static Coffee instance;

    private Map<Player, PlayerModel> players = new LinkedHashMap<>();

    private Inventory inventoryUtils;
    private ScoreBoardAPI scoreBoardAPI;
    private TeleportGUI teleportGui;

    private LuckPerms luckPerms;

    private FileConfiguration lang;
    private FileConfiguration setting;
    private FileConfiguration scoreboard;

    @Override
    public void onEnable() {
        instance = this;
        this.luckPerms = getServer().getServicesManager().load(LuckPerms.class);

        loadConfigs();

        this.inventoryUtils = new Inventory(this);
        this.scoreBoardAPI = new ScoreBoardAPI(this);
        this.teleportGui = new TeleportGUI(this);

        loadHooks();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        inventoryUtils.restoreOnServerStop();
    }

    public void registerCommands() {
        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new CoffeeCore());
        manager.registerCommand(new God(this));
        manager.registerCommand(new Freeze(this));
        manager.registerCommand(new Staff(this));
        manager.registerCommand(new Invsee(this));
        manager.registerCommand(new PlayerTP(this));
        manager.registerCommand(new Clear(this));
        manager.registerCommand(new Gamemode(this));
    }

    public void registerListeners() {
        new PlayerListener(this);
        new StaffListener(this);
        new StaffItemsListener(this);
        new TeleportGUI(this);
    }

    public void loadConfigs() {
        this.lang = createConfig("lang");
        this.setting = createConfig("settings");
        this.scoreboard = createConfig("scoreboard");
    }

    public void loadHooks() {
        new Placeholders(this).register();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            warn("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        new LuckpermsHook(this, this.luckPerms).register();
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") == null) {
            warn("Could not find LuckPerms! This plugin is required.");
        }
    }

    public Inventory getInventoryUtils() {
        return this.inventoryUtils;
    }

    public Map<Player, PlayerModel> getPlayers() {
        return players;
    }

    public FileConfiguration getLang() {
        return lang;
    }

    public FileConfiguration getSetting() {
        return setting;
    }

    public FileConfiguration getScoreboard() {
        return scoreboard;
    }

    public ScoreBoardAPI getScoreBoardAPI() {
        return scoreBoardAPI;
    }

    public TeleportGUI getInventoryTeleport() {
        return teleportGui;
    }

    public FileConfiguration createConfig(String configName) {
        File configFile = new File(getDataFolder(), configName + ".yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource(configName + ".yml", false);
        }

        FileConfiguration cfg = new YamlConfiguration();
        try {
            cfg.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            warn(String.format("A error occurred while copying the config %s.yml to the plugin data folder. Error: %s", configName, e));
            e.printStackTrace();
        }
        return cfg;
    }
}
