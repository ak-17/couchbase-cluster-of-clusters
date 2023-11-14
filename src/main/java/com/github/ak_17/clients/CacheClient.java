package com.github.ak_17.clients;

public interface CacheClient {
    byte[] get(String key);
    void put(String key, byte[] value, int ttlInSeconds);

    class CBBucketConfig {
    }
}
