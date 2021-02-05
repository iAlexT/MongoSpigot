package me.ialext.mongospigot.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.beans.ConstructorProperties;
import java.util.UUID;

public class UserImpl implements User {

  @JsonIgnore
  private final Player delegate;
  private final String id;

  @ConstructorProperties("id")
  public UserImpl(String id) {
    this.id = id;
    delegate = Bukkit.getPlayer(UUID.fromString(id));
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getLocale() {
    return delegate.spigot().getLocale();
  }

  @Override
  public String getDisplayName() {
    return delegate.getDisplayName();
  }

  @Override
  public boolean isOp() {
    return delegate.isOp();
  }
}
