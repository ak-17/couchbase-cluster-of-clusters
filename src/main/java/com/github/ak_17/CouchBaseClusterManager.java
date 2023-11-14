package com.github.ak_17;

import com.github.ak_17.clients.CacheClient;
import com.github.ak_17.clients.CouchbaseClient;
import com.github.ak_17.config.BucketConfig;
import com.github.ak_17.config.ClusterConfig;
import com.github.ak_17.config.ClusterOfClusterConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouchBaseClusterManager {

    private Map<String, CacheClient> cacheClients;

    public CouchBaseClusterManager(ClusterOfClusterConfig clusterOfClusterConfig) {
        this.cacheClients = new HashMap<>();
        for(BucketConfig bucketConfig : clusterOfClusterConfig.getBucketConfigList()) {
            cacheClients.put(bucketConfig.getName(),createCacheclient(clusterOfClusterConfig.getClusterConfigList(), bucketConfig));
        }
    }


    private CacheClient createCacheclient(List<ClusterConfig> clusterConfigList, BucketConfig bucketConfig) {
        if (cacheClients.containsKey(bucketConfig.getName())) {
            return cacheClients.get(bucketConfig.getName());
        }
        CacheClient cacheClient =  new CouchbaseClient(clusterConfigList, bucketConfig);
        cacheClients.put(bucketConfig.getName(), cacheClient);
        return cacheClient;
    }

    public CacheClient getCacheClient(String bucket) {
        if (cacheClients.containsKey(bucket)) {
            return cacheClients.get(bucket);
        }
        return null;
    }

}
