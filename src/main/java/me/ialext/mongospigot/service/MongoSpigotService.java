package me.ialext.mongospigot.service;

import org.bukkit.Bukkit;

import javax.inject.Inject;
import javax.inject.Named;

public class MongoSpigotService implements Service {

  @Inject
  @Named("listener-initializer")
  private Service listenerInitializer;

  @Override
  public void setup() {
    Bukkit.getLogger().info("Starting main service...");
    listenerInitializer.setup();
  }
}
