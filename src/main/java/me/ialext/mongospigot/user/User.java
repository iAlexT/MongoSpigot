package me.ialext.mongospigot.user;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.ialext.mongospigot.model.Model;

public interface User extends Model {

  @JsonGetter
  @JsonProperty("locale")
  String getLocale();

  @JsonGetter
  @JsonProperty("current_display_name")
  String getDisplayName();

  @JsonGetter
  @JsonProperty("op")
  boolean isOp();

}
