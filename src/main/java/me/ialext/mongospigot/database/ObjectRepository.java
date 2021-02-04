package me.ialext.mongospigot.database;

import com.google.common.util.concurrent.ListenableFuture;
import me.ialext.mongospigot.model.Model;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ObjectRepository<O extends Model> {

  ListenableFuture<Optional<O>> findOne(String id);

  Optional<O> findOneSync(String id);

  ListenableFuture<Optional<O>> findByQuery(Bson query);

  Optional<O> findByQuerySync(Bson query);

  ListenableFuture<List<O>> find(List<String> ids);

  default ListenableFuture<List<O>> find(String... ids) {
    return find(Arrays.asList(ids));
  }

  List<O> findSync(List<String> ids);

  default List<O> findSync(String... ids) {
    return findSync(Arrays.asList(ids));
  }

  ListenableFuture<?> save(O o);

  void saveSync(O o);

  ListenableFuture<?> replace(String id, O o);

  void replaceSync(String id, O o);

  ListenableFuture<?> delete(String id);

  void deleteSync(String id);

  ListenableFuture<?> replaceIfPresent(String id, O o);

  void replaceIfPresentSync(String id, O o);

}
