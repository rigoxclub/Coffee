package club.rigox.vanillacore.models;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.tasks.FreezeTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

//Poneme los paquetes en mayusculas hdp
public class PlayerModel {
    private float exp;
    private int expLevel;
    private int foodLevel;
    private double health = 20; // Por si llega a dar un error, mejor darles toda la vida antes que matarlos xd

    private boolean isHidden;
    private boolean isFrozed;

    private ItemStack[] inventory;
    private ItemStack[] armor;
    private BukkitTask task;

    public void hide() {
        this.isHidden = true;
    }

    public void unHide() {
        this.isHidden = false;
    }

    public void freeze(Player target, Player staff) {
        this.isFrozed = true;
        this.task = new FreezeTask(VanillaCore.instance, target, staff).runTaskTimer(VanillaCore.instance, 0L, 3 * 20);
    }

    public void unfreeze() {
        this.isFrozed = false;
        Bukkit.getScheduler().cancelTask(this.task.getTaskId());
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    public void setExp(float exp) {
        this.exp = exp;
    }

    public void setExpLevel(int expLevel) {
        this.expLevel = expLevel;
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

    public int getExpLevel() {
        return expLevel;
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

    public boolean isFrozed() {
        return isFrozed;
    }
}
