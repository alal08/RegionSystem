package io.github.alal08.regionsystem;

import io.github.alal08.regionsystem.listener.ListenerHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class RegionSystem extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        new ListenerHandler(plugin);
        Region.load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
