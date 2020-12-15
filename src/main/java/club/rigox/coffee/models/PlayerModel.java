package club.rigox.coffee.models;

import club.rigox.coffee.Coffee;
import club.rigox.coffee.tasks.FreezeTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import static club.rigox.coffee.utils.MsgUtils.color;

public class PlayerModel {
    private final Coffee plugin;

    public PlayerModel (Coffee plugin) {
        this.plugin = plugin;
    }

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
        this.task = new FreezeTask(Coffee.instance, target, staff).runTaskTimer(Coffee.instance, 0L, 3 * 20);

        target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 50));
        plugin.getInventoryUtils().storeAndClearInventory(target);
        target.sendMessage(color(String.format(plugin.getLang().getString("freeze.player-frozed"), staff.getName())));
        staff.sendMessage(color(String.format("&8&l* &fYou frozed &b%s", target.getName())));
        target.getInventory().setHelmet(new ItemStack(Material.ICE));
        plugin.getScoreBoardAPI().setScoreBoard(target, "freeze", true);
    }

    public void unfreeze(Player target, Player staff) {
        this.isFrozed = false;
        Bukkit.getScheduler().cancelTask(this.task.getTaskId());
        target.removePotionEffect(PotionEffectType.BLINDNESS);
        plugin.getInventoryUtils().restoreInventory(target);
        target.sendMessage(color(String.format(plugin.getLang().getString("freeze.player-unfrozed"), staff.getName())));
        staff.sendMessage(color(String.format("&8&l* &fYou frozed &b%s", target.getName())));
        plugin.getScoreBoardAPI().setScoreBoard(target, "general", true);

        // TITLE AND SUBTITLE
        target.sendTitle(color(plugin.getSetting().getString("titles.unfreeze.title")),
                color(String.format(plugin.getSetting().getString("titles.unfreeze.subtitle"), staff.getName())),
                plugin.getSetting().getInt("titles.unfreeze.options.fadein"),
                plugin.getSetting().getInt("titles.unfreeze.options.stay"),
                plugin.getSetting().getInt("titles.unfreeze.options.fadeout"));
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
