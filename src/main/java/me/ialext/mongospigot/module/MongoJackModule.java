package me.ialext.mongospigot.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import me.ialext.mongospigot.jackson.ObjectMapperProvider;
import me.ialext.mongospigot.mongo.MongoClientProvider;
import me.ialext.mongospigot.mongo.MongoDatabaseProvider;
import me.yushust.inject.AbstractModule;

import java.util.concurrent.Executors;

public class MongoJackModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ListeningExecutorService.class).toInstance(MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())));
    bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class);
    bind(MongoClient.class).toProvider(MongoClientProvider.class);
    bind(MongoDatabase.class).toProvider(MongoDatabaseProvider.class);
  }
}
