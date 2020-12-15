package club.rigox.coffee.hooks;

import club.rigox.coffee.Coffee;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.event.node.NodeRemoveEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.entity.Player;

import static club.rigox.coffee.utils.MsgUtils.color;

public class LuckpermsHook {
    private final Coffee plugin;
    private final LuckPerms luckPerms;

    public LuckpermsHook(Coffee plugin, LuckPerms luckPerms) {
        this.plugin = plugin;
        this.luckPerms = luckPerms;
    }

    public void register() {
        EventBus eventBus = this.luckPerms.getEventBus();
        eventBus.subscribe(this.plugin, NodeAddEvent.class, this::onNodeAdd);
        eventBus.subscribe(this.plugin, NodeRemoveEvent.class, this::onNodeRemove);
    }

    private void onNodeAdd(NodeAddEvent e) {
        if (!e.isUser()) {
            return;
        }

        User target = (User) e.getTarget();
        Node node = e.getNode();

        this.plugin.getServer().getScheduler().runTask(this.plugin, () -> {
            Player player = this.plugin.getServer().getPlayer(target.getUniqueId());
            if (player == null) {
                return; // Player not online.
            }

            if (node instanceof InheritanceNode) {
                String groupName = ((InheritanceNode) node).getGroupName();
                player.sendMessage(color(String.format("&8&l* &fThe &b%s &frank has been added on you.", groupName)));
                plugin.getScoreBoardAPI().setScoreBoard(player, "general", true);
            }
        });
    }

    private void onNodeRemove(NodeRemoveEvent e) {
        if (!e.isUser()) {
            return;
        }

        User target = (User) e.getTarget();
        Node node = e.getNode();

        this.plugin.getServer().getScheduler().runTask(this.plugin, () -> {
            Player player = this.plugin.getServer().getPlayer(target.getUniqueId());
            if (player == null) {
                return; // Player not online.
            }

            if (node instanceof InheritanceNode) {
                String groupName = ((InheritanceNode) node).getGroupName();
                player.sendMessage(color(String.format("&8&l* &fThe &b%s &frank has been removed from you.", groupName)));
                plugin.getScoreBoardAPI().setScoreBoard(player, "general", true);
            }
        });
    }


}
