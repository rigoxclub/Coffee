package club.rigox.vanillacore.player;

import club.rigox.vanillacore.models.PlayerModel;
import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.utils.ConsoleUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Inventory {
    private final VanillaCore plugin;

    public Inventory(VanillaCore plugin) {
        this.plugin = plugin;
    }


    public void storeAndClearInventory(Player player) {

        float playerExp = player.getExp();
        int playerExpLevel = player.getLevel();

        int playerFood = player.getFoodLevel();

        double playerHealth = player.getHealth();


        PlayerModel staffMember = plugin.getPlayerModel().get(player);

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

        PlayerModel staffMember = plugin.getPlayerModel().get(player);

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
            player.setHealth(playerHealth);

            ConsoleUtils.debug("Restored successfully Inventory, FoodLevel and Health");
        } else {
            player.getInventory().clear();
            ConsoleUtils.debug("Inventory has been cleared because the inventory is empty");
        }

        if (playerExp != 0) {
            player.setExp(playerExp);
            ConsoleUtils.debug("Restored successfully player experience");
        }

        if (playerExpLevel != 0) {
            player.setLevel(playerExpLevel);
            ConsoleUtils.debug("Restored successfully player experience level");
        }

        if (armorContents != null) {
            player.getInventory().setArmorContents(armorContents);
            ConsoleUtils.debug("Restored successfully player armor");
        } else {
            remArmor(player);
            ConsoleUtils.debug("Armor has been cleared because it's empty");
        }
    }

    public void remArmor(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }
}
