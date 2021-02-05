package me.ialext.mongospigot.listener;

import me.ialext.mongospigot.cache.Cache;
import me.ialext.mongospigot.database.ObjectRepository;
import me.ialext.mongospigot.user.User;
import me.ialext.mongospigot.user.UserImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class PlayerJoinListener implements Listener {

  @Inject
  private Cache<UUID, User> userCache;

  @Inject
  private ObjectRepository<User> userObjectRepository;

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    Optional<User> optionalUser = userObjectRepository.findOneSync(player.getUniqueId().toString());

    userCache.add(player.getUniqueId(), optionalUser.orElse(new UserImpl(player.getUniqueId().toString())));
  }
}