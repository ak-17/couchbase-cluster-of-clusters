package com.github.ak_17.shard;

public interface KeyToShardMapper {
    String getShard(String key);
}
