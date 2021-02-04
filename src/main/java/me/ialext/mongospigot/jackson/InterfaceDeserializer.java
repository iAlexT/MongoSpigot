package me.ialext.mongospigot.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import me.ialext.mongospigot.user.User;
import me.ialext.mongospigot.user.UserImpl;

public class InterfaceDeserializer {

  public static SimpleModule getAbstractTypes() {
    SimpleModule module = new SimpleModule("InterfaceDeserializerModule", Version.unknownVersion());
    SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver()
        .addMapping(User.class, UserImpl.class);
    module.setAbstractTypes(resolver);

    return module;
  }
}
