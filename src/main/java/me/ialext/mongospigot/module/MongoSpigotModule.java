package me.ialext.mongospigot.module;

import me.ialext.mongospigot.MongoSpigot;
import me.ialext.mongospigot.cache.Cache;
import me.ialext.mongospigot.cache.UserCache;
import me.ialext.mongospigot.user.User;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.key.TypeReference;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class MongoSpigotModule extends AbstractModule {

  private final MongoSpigot plugin;

  public MongoSpigotModule(MongoSpigot plugin) {
    this.plugin = plugin;
  }

  @Override
  protected void configure() {
    bind(TypeReference.of(Cache.class, UUID.class, User.class)).to(UserCache.class).singleton();
    bind(Plugin.class).to(MongoSpigot.class);
    bind(MongoSpigot.class).toInstance(plugin);
    install(
        new MongoJackModule(),
        new ObjectRepositoryModule(),
        new ServiceModule()
    );
  }
}
