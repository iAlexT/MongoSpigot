package me.ialext.mongospigot.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.mongodb.client.MongoDatabase;
import me.ialext.mongospigot.database.MongoObjectRepository;
import me.ialext.mongospigot.database.ObjectRepository;
import me.ialext.mongospigot.model.Model;

import javax.inject.Inject;
import javax.inject.Provider;

public class ObjectRepositoryProvider<O extends Model> implements Provider<ObjectRepository<O>> {

  @Inject
  private ListeningExecutorService executorService;

  @Inject
  private ObjectMapper objectMapper;

  @Inject
  private MongoDatabase mongoDatabase;

  private final String collectionName;
  private final Class<O> modelClass;

  public ObjectRepositoryProvider(String collectionName, Class<O> modelClass) {
    this.collectionName = collectionName;
    this.modelClass = modelClass;
  }

  @Override
  public ObjectRepository<O> get() {
    return new MongoObjectRepository<>(executorService, mongoDatabase, collectionName, modelClass, objectMapper);
  }
}
