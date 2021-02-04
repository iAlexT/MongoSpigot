package me.ialext.mongospigot.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.mongodb.client.MongoDatabase;
import me.ialext.mongospigot.model.Model;
import org.bson.UuidRepresentation;
import org.bson.conversions.Bson;
import org.mongojack.JacksonMongoCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoObjectRepository<O extends Model> implements ObjectRepository<O> {

  private final ListeningExecutorService executorService;
  private final JacksonMongoCollection<O> mongoCollection;

  public MongoObjectRepository(ListeningExecutorService executorService,
                               MongoDatabase mongoDatabase,
                               String collectionName,
                               Class<O> modelClass,
                               ObjectMapper objectMapper) {
    this.executorService = executorService;
    this.mongoCollection = JacksonMongoCollection
        .builder()
        .withObjectMapper(objectMapper)
        .build(mongoDatabase.getCollection(collectionName, modelClass), modelClass, UuidRepresentation.STANDARD);
  }

  @Override
  public ListenableFuture<Optional<O>> findOne(String id) {
    return executorService.submit(() -> findOneSync(id));
  }

  @Override
  public Optional<O> findOneSync(String id) {
    return Optional.ofNullable(mongoCollection.findOneById(id));
  }

  @Override
  public ListenableFuture<Optional<O>> findByQuery(Bson query) {
    return executorService.submit(() -> findByQuerySync(query));
  }

  @Override
  public Optional<O> findByQuerySync(Bson query) {
    return Optional.ofNullable(mongoCollection.find(query).first());
  }

  @Override
  public ListenableFuture<List<O>> find(List<String> ids) {
    return executorService.submit(() -> findSync(ids));
  }

  @Override
  public List<O> findSync(List<String> ids) {
    List<O> foundDocs = new ArrayList<>();

    for (String id : ids) {
      Optional<O> optionalDoc = Optional.ofNullable(mongoCollection.findOneById(id));
      optionalDoc.ifPresent(foundDocs::add);
    }

    return mongoCollection.find().into(foundDocs);
  }

  @Override
  public ListenableFuture<?> save(O o) {
    return executorService.submit(() -> saveSync(o));
  }

  @Override
  public void saveSync(O o) {
    mongoCollection.save(o);
  }

  @Override
  public ListenableFuture<?> replace(String id, O o) {
    return executorService.submit(() -> replaceSync(id, o));
  }

  @Override
  public void replaceSync(String id, O o) {
    mongoCollection.replaceOneById(id, o);
  }

  @Override
  public ListenableFuture<?> delete(String id) {
    return executorService.submit(() -> deleteSync(id));
  }

  @Override
  public void deleteSync(String id) {
    mongoCollection.removeById(id);
  }

  @Override
  public ListenableFuture<?> replaceIfPresent(String id, O o) {
    return executorService.submit(() -> replaceIfPresentSync(id, o));
  }

  @Override
  public void replaceIfPresentSync(String id, O o) {
    Optional<O> optionalDoc = findOneSync(id);

    if (optionalDoc.isPresent()) {
      replaceSync(id, optionalDoc.get());
    } else {
      saveSync(o);
    }
  }
}
