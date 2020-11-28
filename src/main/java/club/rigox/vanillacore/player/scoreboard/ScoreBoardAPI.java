package club.rigox.vanillacore.player.scoreboard;

import club.rigox.vanillacore.VanillaCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.security.SecureRandom;

public class ScoreBoardAPI {
    private final VanillaCore plugin;


    public ScoreBoardAPI(VanillaCore plugin) {
        this.plugin = plugin;
    }

    public ScoreboardCreator setScoreBoard(Player p, String type, boolean health) {

        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        ScoreboardCreator scoreboard = new ScoreboardCreator(randomString(8), health);

        scoreboard.setName(plugin.getScoreboard().getString(type + ".title"));

        int i = plugin.getScoreboard().getStringList(type + ".body").size();
        for (String line : plugin.getScoreboard().getStringList(type + ".body")) {
            scoreboard.lines(i, plugin.parseField(line, p));
            i--;
        }

        p.setScoreboard(scoreboard.getScoreboard());

        return scoreboard;
    }


    private String randomString(int length) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
