package club.rigox.vanillacore.models;

import club.rigox.vanillacore.VanillaCore;
import club.rigox.vanillacore.tasks.FreezeTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class PlayerModel {
    private float exp;
    private int expLevel;
    private int foodLevel;
    private double health = 20; // Por si llega a dar un error, mejor darles toda la vida antes que matarlos xd

    private boolean hasGod;
    private boolean isFrozed;
    private boolean isVanished;
    private boolean isFlying;

    private ItemStack[] inventory;
    private ItemStack[] armor;
    private BukkitTask task;

    public void enableGod() {
        this.hasGod = true;
    }

    public void disableGod() {
        this.hasGod = false;
    }

    public void vanish() {
        this.isVanished = true;
    }

    public void unVanish() {
        this.isVanished = false;
    }

    public void setFly() {
        this.isFlying = true;
    }

    public void removeFly() {
        this.isFlying = false;
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


    // GETTERS
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

    public boolean hasGod() {
        return hasGod;
    }

    public boolean isFrozed() {
        return isFrozed;
    }

    public boolean isVanished() {
        return isVanished;
    }

    public boolean isFlying() {
        return isFlying;
    }
}
