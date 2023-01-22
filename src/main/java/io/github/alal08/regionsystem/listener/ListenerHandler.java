package io.github.alal08.regionsystem.listener;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ListenerHandler {

    public ListenerHandler(@NotNull JavaPlugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerListener(), plugin);
    }
}