package me.ialext.mongospigot;

import me.ialext.mongospigot.module.MongoSpigotModule;
import me.ialext.mongospigot.service.Service;
import me.yushust.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.inject.Named;

public final class MongoSpigot extends JavaPlugin {

  @Inject
  @Named("mongo-spigot-service")
  private Service service;

  @Override
  public void onEnable() {
    Injector injector = Injector.create(new MongoSpigotModule(this));
    injector.injectMembers(this);

    service.setup();
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
