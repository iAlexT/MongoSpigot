package me.ialext.mongospigot.service.initializer;

import me.ialext.mongospigot.listener.PlayerJoinListener;
import me.ialext.mongospigot.listener.PlayerQuitListener;
import me.ialext.mongospigot.service.Service;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

public class ListenerInitializer implements Service {

  @Inject
  private Plugin plugin;

  @Inject
  private PlayerJoinListener playerJoinListener;

  @Inject
  private PlayerQuitListener playerQuitListener;

  @Override
  public void setup() {
    Bukkit.getLogger().info("Starting listener initializer...");
    registerListeners(
        playerJoinListener,
        playerQuitListener
    );
  }

  private void registerListeners(Listener... listeners) {
    for (Listener listener : listeners) {
      plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
  }
}
