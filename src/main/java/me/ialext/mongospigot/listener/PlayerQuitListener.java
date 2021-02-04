package me.ialext.mongospigot.listener;

import me.ialext.mongospigot.cache.Cache;
import me.ialext.mongospigot.database.ObjectRepository;
import me.ialext.mongospigot.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.inject.Inject;
import java.util.UUID;

public class PlayerQuitListener implements Listener {

  @Inject
  private Cache<UUID, User> userCache;

  @Inject
  private ObjectRepository<User> userObjectRepository;

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    User user = userCache.find(player.getUniqueId()).orElseThrow(() -> new IllegalArgumentException("This should be impossible!"));

    userObjectRepository.replaceIfPresentSync(user.getId(), user);
    userCache.remove(player.getUniqueId());
  }
}
