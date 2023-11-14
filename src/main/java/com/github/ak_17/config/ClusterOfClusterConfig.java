package com.github.ak_17.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClusterOfClusterConfig {
    List<ClusterConfig> clusterConfigList;
    List<BucketConfig> bucketConfigList;
}
