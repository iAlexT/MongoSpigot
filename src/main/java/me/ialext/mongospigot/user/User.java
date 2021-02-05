package me.ialext.mongospigot.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.ialext.mongospigot.model.Model;

public interface User extends Model {

  @JsonProperty("locale")
  String getLocale();

  @JsonProperty("current_display_name")
  String getDisplayName();

  @JsonProperty("op")
  boolean isOp();

}
