package club.rigox.vanillacore.placeholders;

import club.rigox.vanillacore.VanillaCore;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceholderHook extends PlaceholderExpansion {
    private VanillaCore plugin;

    public PlaceholderHook(VanillaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "vanillacore";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }

        if (identifier.equals("vanished")) {
            boolean isVanished = plugin.getPlayers().get(player).isVanished();
            return isVanished ? "&aEnabled" : "&eDisabled";
        }

        return null;
    }
}
