package club.rigox.coffee.hooks;

import club.rigox.coffee.Coffee;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class Placeholders extends PlaceholderExpansion {
    private Coffee plugin;

    public Placeholders(Coffee plugin) {
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
