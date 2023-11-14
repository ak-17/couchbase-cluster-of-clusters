package com.github.ak_17.shard;

public class SimpleShardingStrategy implements ShardingStrategy{

    private int totalShards;

    public SimpleShardingStrategy(int totalShards) {
        this.totalShards = totalShards;
    }

    @Override
    public String chooseShard(String key) {
        return null;
    }
}
