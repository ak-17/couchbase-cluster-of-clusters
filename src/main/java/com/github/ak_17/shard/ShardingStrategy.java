package com.github.ak_17.shard;

public interface ShardingStrategy {
    String chooseShard(String key);
}
