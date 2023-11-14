package com.github.ak_17.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BucketConfig {
    private String name;
    private int ttlInSeconds;
    private String shardingStrategy;
}
