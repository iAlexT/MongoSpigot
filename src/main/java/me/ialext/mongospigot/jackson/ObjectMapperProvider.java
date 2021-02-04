package me.ialext.mongospigot.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Provider;

public class ObjectMapperProvider implements Provider<ObjectMapper> {

  @Override
  public ObjectMapper get() {
    ObjectMapper mapper = new ObjectMapper().registerModule(InterfaceDeserializer.getAbstractTypes());
    mapper.setVisibility(mapper.getSerializationConfig()
        .getDefaultVisibilityChecker()
        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
        .withGetterVisibility(JsonAutoDetect.Visibility.ANY)
        .withIsGetterVisibility(JsonAutoDetect.Visibility.ANY)
        .withSetterVisibility(JsonAutoDetect.Visibility.ANY)
        .withCreatorVisibility(JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC));

    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return mapper;
  }
}
