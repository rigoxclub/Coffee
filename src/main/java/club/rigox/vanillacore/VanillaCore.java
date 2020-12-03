package club.rigox.vanillacore;

import club.rigox.vanillacore.commands.*;
import club.rigox.vanillacore.hooks.LuckpermsHook;
import club.rigox.vanillacore.listeners.PlayerListener;
import club.rigox.vanillacore.listeners.StaffListener;
import club.rigox.vanillacore.models.PlayerModel;
import club.rigox.vanillacore.hooks.PlaceholderAPI;
import club.rigox.vanillacore.player.FlyStatus;
import club.rigox.vanillacore.player.Inventory;
import club.rigox.vanillacore.player.scoreboard.ScoreBoardAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.EventBus;
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

import static club.rigox.vanillacore.utils.ConsoleUtils.warn;

public final class VanillaCore extends JavaPlugin {
    public static VanillaCore instance;

    private Map<Player, PlayerModel> players = new LinkedHashMap<>();

    private Inventory inventoryUtils;
    private FlyStatus flyStatus;
    private ScoreBoardAPI scoreBoardAPI;

    private LuckPerms luckPerms;

    private FileConfiguration lang;
    private FileConfiguration setting;
    private FileConfiguration scoreboard;

    @Override
    public void onEnable() {
        instance = this;
        this.luckPerms = getServer().getServicesManager().load(LuckPerms.class);

        this.lang = createConfig("lang");
        this.setting = createConfig("settings");
        this.scoreboard = createConfig("scoreboard");

        this.inventoryUtils = new Inventory(this);
        this.flyStatus = new FlyStatus(this);
        this.scoreBoardAPI = new ScoreBoardAPI(this);


        new PlaceholderAPI(this).register();

        new PlayerListener(this);
        new StaffListener(this);
        new LuckpermsHook(this, this.luckPerms).register();

        registerCommands();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            warn("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        inventoryUtils.restoreOnServerStop();
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
        new Fly(this);
        new Invsee(this);
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

    public FileConfiguration getLang() {
        return lang;
    }

    public FileConfiguration getSetting() {
        return setting;
    }

    public FileConfiguration getScoreboard() {
        return scoreboard;
    }

    public String parseField(String field, Player p) {
        return me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p, field);
    }

    public FlyStatus getFlyStatus() {
        return flyStatus;
    }

    public ScoreBoardAPI getScoreBoardAPI() {
        return scoreBoardAPI;
    }
}
