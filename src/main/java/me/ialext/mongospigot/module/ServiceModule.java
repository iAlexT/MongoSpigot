package me.ialext.mongospigot.module;

import me.ialext.mongospigot.service.MongoSpigotService;
import me.ialext.mongospigot.service.Service;
import me.ialext.mongospigot.service.initializer.ListenerInitializer;
import me.yushust.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Service.class).named("mongo-spigot-service").to(MongoSpigotService.class).singleton();
    bind(Service.class).named("listener-initializer").to(ListenerInitializer.class).singleton();
  }
}
