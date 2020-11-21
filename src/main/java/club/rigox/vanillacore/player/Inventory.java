package club.rigox.vanillacore.player;

import club.rigox.vanillacore.Models.PlayerModel;
import club.rigox.vanillacore.VanillaCore;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

public class Inventory {
    private VanillaCore plugin;

    public Inventory(VanillaCore plugin) {
        this.plugin = plugin;
    }



    public void storeAndClearInventory(Player player) {

        float playerExp = player.getExp();
        int playerFood = player.getFoodLevel();

        double playerHealth = player.getHealth();


        PlayerModel staffMember = plugin.getStaffMode().get(player);


        staffMember.setFullInventory(player.getInventory());
        staffMember.setHealth(playerHealth);
        staffMember.setFoodLevel(playerFood);
        staffMember.setExp(playerExp);

        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);

        remArmor(player);
    }

    public void restoreInventory(Player player) {

        PlayerModel staffMember = plugin.getStaffMode().get(player);

        ItemStack[] itemsContent = staffMember.getInventory();
        ItemStack[] armorContents = staffMember.getInventory();

        float playerExp = staffMember.getExp();
        int playerFood = staffMember.getFoodLevel();

        double playerHealth = staffMember.getHealth();

        if (itemsContent != null) {
            player.getInventory().setContents(itemsContent);
            player.setFoodLevel(playerFood);
            player.setHealth(playerHealth);
        } else {
            player.getInventory().clear();
        }

        if (playerExp != 0) {
            player.setExp(playerExp);

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
