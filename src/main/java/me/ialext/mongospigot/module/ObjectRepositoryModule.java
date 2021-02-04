package me.ialext.mongospigot.module;

import me.ialext.mongospigot.database.ObjectRepository;
import me.ialext.mongospigot.provider.ObjectRepositoryProvider;
import me.ialext.mongospigot.user.User;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.key.TypeReference;

public class ObjectRepositoryModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(new TypeReference<ObjectRepository<User>>() {}).toProvider(new ObjectRepositoryProvider<>("users", User.class));
  }
}
