package club.rigox.coffee.commands.staff;

import club.rigox.coffee.Coffee;
import club.rigox.coffee.player.ToggleVanish;
import club.rigox.coffee.utils.Items;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

@CommandAlias("staff|mod")
@CommandPermission("coffee.staff")
public class Staff extends BaseCommand {
    private final Coffee plugin;
    private final ToggleVanish toggleVanish;
    private final Items items;

    public Staff(Coffee plugin) {
        this.plugin = plugin;
        toggleVanish = new ToggleVanish(plugin);
        items = new Items(plugin);
    }

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(plugin.getLang().getString("only-users")));
            return;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission("vanillacore.staff"))) {
            player.sendMessage(color(plugin.getLang().getString("no-staff-permission")));
            return;
        }

        if (plugin.getPlayers().get(player).hasGod()) {
            toggleVanish.showStaff(player);

            plugin.getPlayers().get(player).unVanish();
            plugin.getPlayers().get(player).disableGod();

            plugin.getInventoryUtils().restoreInventory(player);
            plugin.getScoreBoardAPI().setScoreBoard(player, "general", true);

            player.setGameMode(GameMode.SURVIVAL);

            player.sendMessage(color(plugin.getLang().getString("staff-mode.disabled")));
            return;
        }
        
        toggleVanish.hideStaff(player);

        plugin.getPlayers().get(player).enableGod();
        plugin.getPlayers().get(player).vanish();

        plugin.getInventoryUtils().storeAndClearInventory(player);
        items.giveItems(player);

        plugin.getScoreBoardAPI().setScoreBoard(player, "staff-mode", true);

        player.setGameMode(GameMode.ADVENTURE);

        player.sendMessage(color(plugin.getLang().getString("staff-mode.enabled")));
    }

}
