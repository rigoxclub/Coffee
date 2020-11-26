package club.rigox.vanillacore.tasks;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static club.rigox.vanillacore.utils.MsgUtils.color;

public class FreezeTask extends BukkitRunnable {
    private final Player target;
    private final VanillaCore plugin;
    private final Player staff;

    public FreezeTask (VanillaCore plugin, Player target, Player staff) {
        this.plugin = plugin;
        this.target = target;
        this.staff = staff;
    }

    @Override
    public void run() {
        target.playSound(target.getLocation(), Sound.BLOCK_GLASS_BREAK, 3.0F, 1F);
        target.playEffect(target.getLocation(), Effect.STEP_SOUND, Material.ICE);
        target.sendTitle(color("&c&lYOU ARE FROZED!"), color(String.format("&ePlease follow %s instructions in the chat.", staff.getName())), 10, 40, 10);

    }
}
