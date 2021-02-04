package me.ialext.mongospigot.cache;

import me.ialext.mongospigot.user.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserCache implements Cache<UUID, User> {

  private final Map<UUID, User> userMap = new ConcurrentHashMap<>();

  @Override
  public Map<UUID, User> getDelegate() {
    return userMap;
  }
}
