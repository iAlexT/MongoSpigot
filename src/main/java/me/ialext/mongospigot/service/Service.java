package me.ialext.mongospigot.service;

public interface Service {

  void setup();

  default void shutdown() {}
}
