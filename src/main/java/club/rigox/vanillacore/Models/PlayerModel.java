package club.rigox.vanillacore.Models;

import club.rigox.vanillacore.utils.ConsoleUtils;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel {
    private float exp;
    private int foodLevel;
    private double health = 20; // Por si llega a dar un error, mejor darles toda la vida antes que matarlos xd

    private boolean isHidden;

    private ItemStack[] inventory;
    private ItemStack[] armor;

    public void hide() {
        this.isHidden = true;
    }

    public void unHide() {
        this.isHidden = false;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    public void setExp(float exp) {
        this.exp = exp;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setArmor(ItemStack[] armor) {
        this.armor = (armor);
    }

    public void setInventory(ItemStack[] inv) {
        this.inventory = inv;
    }


    //Geters
    public double getHealth() {
        return health;
    }

    public float getExp() {
        return exp;
    }

    public int getFoodLevel() {
        return foodLevel;
    }


    public ItemStack[] getInventory() {
        return this.inventory;
    }

    public ItemStack[] getArmor() {
        return this.armor;
    }

    public boolean isHidden() {
        return isHidden;
    }
}
