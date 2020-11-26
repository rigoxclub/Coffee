package club.rigox.vanillacore.commands;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.player.StaffItems;
import club.rigox.vanillacore.utils.CommandInterface;
import club.rigox.vanillacore.player.Vanish;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.vanillacore.utils.MsgUtils.color;


public class Staff implements CommandExecutor {
    private final VanillaCore plugin;
    private final Vanish vanish;
    private final StaffItems staffItems;

    public Staff(VanillaCore plugin) {
        this.plugin = plugin;
        vanish = new Vanish(plugin);
        staffItems = new StaffItems(plugin);
        plugin.getServer().getPluginCommand("staff").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;


        if (!(player.hasPermission("staff.use"))) {
            player.sendMessage(color("&cYou don't have enough permission to use this!"));
            return true;
        }

        if(args.length == 1){
            if (args[0].equalsIgnoreCase("help")) { //! Pa que queres un commandHandler si podes hacer esto? xd
                sendHelp(sender);
                return true;
            }

            //TODO: Send help.
            //Capaz le queres decir q no existe?
        }


        if (plugin.getPlayers().get(player).isHidden()) {
            player.sendMessage(color("&cStaff mode disabled"));

            plugin.getPlayers().get(player).unHide();

            vanish.showStaff(player);
            plugin.getInventoryUtils().restoreInventory(player);
            return true;
        }

        plugin.getInventoryUtils().storeAndClearInventory(player);
        staffItems.giveStaffItems(player);

        plugin.getPlayers().get(player).hide();

        vanish.hideStaff(player);

        player.sendMessage(color("&aStaff mode enabled"));
        return false;
    }


    private void sendHelp(CommandSender sender) {
        //Esto lo podes pasar a un comando como vanillacore o algo asi xd
        sender.sendMessage(color("&7&m------------------------------------------------"));
        sender.sendMessage(color("&c&lRigox Vanilla Core"));
        sender.sendMessage(color("&7&oCommand Help"));
        sender.sendMessage(color("&7&m------------------------------------------------"));
        sender.sendMessage(color("&8&l* &c/staff &f- Toggles staff mode."));
        sender.sendMessage(color("&7&m------------------------------------------------"));
    }

}
