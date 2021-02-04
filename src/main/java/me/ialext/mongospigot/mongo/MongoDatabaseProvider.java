package me.ialext.mongospigot.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.inject.Inject;
import javax.inject.Provider;

public class MongoDatabaseProvider implements Provider<MongoDatabase> {

  @Inject
  private MongoClient mongoClient;

  @Override
  public MongoDatabase get() {
    return mongoClient.getDatabase("MongoSpigot");
  }
}
