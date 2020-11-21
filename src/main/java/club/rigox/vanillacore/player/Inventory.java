package club.rigox.vanillacore.player;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Inventory {
    private VanillaCore plugin;

    public Inventory (VanillaCore plugin) {
        this.plugin = plugin;
    }

    static Map<UUID, ItemStack[]> items = new HashMap<UUID, ItemStack[]>();
    static Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();

    static Map<UUID, Float> exp = new HashMap<UUID, Float>();
    static Map<UUID, Integer> food = new HashMap<UUID, Integer>();
    static Map<UUID, Double> health = new HashMap<UUID, Double>();

    public static void storeAndClearInventory (Player player) {
        UUID uuid = player.getUniqueId();

        ItemStack[] itemsContent = player.getInventory().getContents();
        ItemStack[] armorContents = player.getInventory().getArmorContents();

        float playerExp = player.getExp();
        int playerFood = player.getFoodLevel();

        double playerHealth = player.getHealth();

        items.put(uuid, itemsContent);
        armor.put(uuid, armorContents);

        exp.put(uuid, playerExp);
        food.put(uuid, playerFood);

        health.put(uuid, playerHealth);

        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);

        remArmor(player);
    }

    public static void restoreInventory (Player player) {
        UUID uuid = player.getUniqueId();

        ItemStack[] itemsContent = items.get(uuid);
        ItemStack[] armorContents = armor.get(uuid);

        float playerExp = exp.get(uuid);
        int playerFood = food.get(uuid);

        double playerHealth = health.get(uuid);

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

    public static void remArmor(Player player){
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }
}
