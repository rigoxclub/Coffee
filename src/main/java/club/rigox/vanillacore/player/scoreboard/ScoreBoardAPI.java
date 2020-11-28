package club.rigox.vanillacore.player.scoreboard;

import com.imarcomc.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.security.SecureRandom;


import static com.imarcomc.skyblock.Utils.Console.C;

public class ScoreBoardAPI {
    private Main plugin;

    private Configuration Lang;

    public ScoreBoardAPI(Main plugin) {
        this.plugin = plugin;
        this.Lang = plugin.getLang();
    }

    public ScoreboardCreator setScoreBoard(Player p, boolean health) {

        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        ScoreboardCreator scoreboard = new ScoreboardCreator(randomString(8), health);

        scoreboard.setName(C(Lang.getString("Scoreboard.Title")));


        int i = Lang.getStringList("Scoreboard.Body").size();
        for (String line : Lang.getStringList("Scoreboard.Body")) {
            scoreboard.lines(i, plugin.getUtils().parseField(line, p));
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
