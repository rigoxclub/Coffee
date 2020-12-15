package club.rigox.coffee.tasks;

import club.rigox.coffee.Coffee;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static club.rigox.coffee.utils.MsgUtils.color;

public class FreezeTask extends BukkitRunnable {
    private final Player target;
    private final Coffee plugin;
    private final Player staff;

    public FreezeTask (Coffee plugin, Player target, Player staff) {
        this.plugin = plugin;
        this.target = target;
        this.staff = staff;
    }

    @Override
    public void run() {
        target.playSound(target.getLocation(), Sound.BLOCK_GLASS_BREAK, 3.0F, 1F);
        target.playEffect(target.getLocation(), Effect.STEP_SOUND, Material.ICE);
        target.sendTitle(color(plugin.getSetting().getString("titles.freeze.title")),
                color(String.format(plugin.getSetting().getString("titles.freeze.subtitle"), staff.getName())),
                plugin.getSetting().getInt("titles.freeze.options.fadein"),
                plugin.getSetting().getInt("titles.freeze.options.stay"),
                plugin.getSetting().getInt("titles.freeze.options.fadeout"));;

    }
}
