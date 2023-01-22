package io.github.alal08.regionsystem.listener;

import io.github.alal08.regionsystem.Region;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerListener implements Listener {

    Location location1 = null;
    Location location2 = null;

    @EventHandler
    public void onInteract(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        @NotNull Action action = event.getAction();
        if (action.isLeftClick()) {
            location1 = event.getClickedBlock().getLocation();
            player.sendMessage(location1.toString());
        } else if (action.isRightClick()) {
            location2 = event.getClickedBlock().getLocation();
            player.sendMessage(location2.toString());
        }
        if (location1 == null) return;
        if (location2 == null) return;
        Region region = new Region(location1, location2);
        player.sendMessage(region.getUniqueId());
    }
}
