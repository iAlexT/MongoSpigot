package me.ialext.mongospigot.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import javax.inject.Provider;

public class MongoClientProvider implements Provider<MongoClient> {

  private final MongoClient mongoClient = MongoClients.create();

  @Override
  public MongoClient get() {
    return mongoClient;
  }
}
