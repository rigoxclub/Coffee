package club.rigox.vanillacore.player;

import club.rigox.vanillacore.Models.PlayerModel;
import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.utils.ConsoleUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Inventory {
    private VanillaCore plugin;

    public Inventory(VanillaCore plugin) {
        this.plugin = plugin;
    }


    public void storeAndClearInventory(Player player) {

        float playerExp = player.getExp();
        int playerExpLevel = player.getLevel();

        int playerFood = player.getFoodLevel();

        double playerHealth = player.getHealth();


        PlayerModel staffMember = plugin.getStaffMode().get(player);

        if(staffMember == null) return; //TODO: Error message.

        // Me di cuenta que el PlayerInventory mantiene las props de tu inv osea se estaba actualizando xd
        staffMember.setInventory(player.getInventory().getContents());
        staffMember.setArmor(player.getInventory().getArmorContents());

        ConsoleUtils.debug(String.format("Saving player %s heath %s", player.getName(), playerHealth));
        staffMember.setHealth(playerHealth);
        staffMember.setFoodLevel(playerFood);
        staffMember.setExp(playerExp);
        staffMember.setExpLevel(playerExpLevel);

        //  Clearing player inv
        //  Setting the player health to max
        //  Setting the food level to max
        //  Setting exp to 0
        //  Setting expLevel to 0
        //  Removing all armor

        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setLevel(0);

        remArmor(player);
    }

    public void restoreInventory(Player player) {

        PlayerModel staffMember = plugin.getStaffMode().get(player);


        if(staffMember == null) return; //TODO: Error message.

        ItemStack[] itemsContent = staffMember.getInventory();
        ItemStack[] armorContents = staffMember.getArmor();


        float playerExp = staffMember.getExp();
        int playerFood = staffMember.getFoodLevel();
        int playerExpLevel = staffMember.getExpLevel();

        double playerHealth = staffMember.getHealth();

        if (itemsContent != null) {

            player.getInventory().setContents(itemsContent);

            player.setFoodLevel(playerFood);

            ConsoleUtils.debug(String.format("Player %s heath %s", player.getName(), playerHealth));

            player.setHealth(playerHealth);
        } else {
            player.getInventory().clear();
        }

        if (playerExp != 0) {
            player.setExp(playerExp);

        }

        if (playerExpLevel != 0) {
            player.setLevel(playerExpLevel);
        }

        if (armorContents != null) {
            player.getInventory().setArmorContents(armorContents);
        } else {
            remArmor(player);
        }

    }

    public void remArmor(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }
}
