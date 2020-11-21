package club.rigox.vanillacore.Models;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerModel {

    private PlayerInventory playerInventory;

    private float exp;
    private int foodLevel;
    private double health;

    private boolean isHidden;

    public PlayerModel(PlayerInventory playerInventory, float exp, int foodLevel, double health) {
        this.playerInventory = playerInventory;
        this.exp = exp;
        this.foodLevel = foodLevel;
        this.health = health;
    }

    public void hide() {
        this.isHidden = true;
    }

    public void unHide(){
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
        this.playerInventory.setArmorContents(armor);
    }

    public void setInventory(ItemStack[] inv) {
        this.playerInventory.setContents(inv);
    }

    public void setFullInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
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
        return this.playerInventory.getContents();
    }

    public ItemStack[] getArmor() {
        return this.playerInventory.getArmorContents();
    }

    public Inventory getFullInventory() {
        return playerInventory;
    }

    public boolean isHidden() {
        return isHidden;
    }
}
