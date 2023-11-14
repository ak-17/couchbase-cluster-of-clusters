package com.github.ak_17.clients;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.codec.RawBinaryTranscoder;
import com.couchbase.client.java.kv.*;
import com.github.ak_17.config.BucketConfig;
import com.github.ak_17.config.ClusterConfig;
import com.github.ak_17.shard.ClusterKeyMapper;
import com.github.ak_17.shard.ShardingStrategyFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouchBaseClient implements CacheClient {
    private Map<String,Collection> clusterCollectionMap;
    private ClusterKeyMapper clusterKeyMapper;

    public CouchBaseClient(List<ClusterConfig> clusterConfigList, BucketConfig bucketConfig) {
        this.clusterCollectionMap = new HashMap<>();
        for (ClusterConfig clusterConfig : clusterConfigList) {
            Cluster cluster = Cluster.connect(clusterConfig.getConnectionString(), clusterConfig.getUsername(), clusterConfig.getPassword());
            Bucket bucket = cluster.bucket(bucketConfig.getName());
            Collection collection = bucket.defaultCollection();
            clusterCollectionMap.put(clusterConfig.getName(), collection);
        }
        this.clusterKeyMapper = ShardingStrategyFactory.getClusterShardingStrategy(bucketConfig);
    }

    @Override
    public byte[] get(String key) {
        Collection collection = getCollection(key);
        try {
            GetResult getResult = collection.get(key, GetOptions.getOptions().withExpiry(true).transcoder(RawBinaryTranscoder.INSTANCE));
            if (getResult.expiryTime().isPresent())
                System.out.println(getResult.expiryTime().get().getEpochSecond());
            return getResult.contentAsBytes();
        } catch (DocumentNotFoundException documentNotFoundException) {
            return null;
        }

    }

    @Override
    public void put(String key, byte[] value, int ttlInSeconds) {
        UpsertOptions upsertOptions =  UpsertOptions.upsertOptions().expiry(Duration.ofSeconds(ttlInSeconds)).transcoder(RawBinaryTranscoder.INSTANCE);
        MutationResult mutationResult = getCollection(key).upsert(key,value, upsertOptions);
        System.out.println(mutationResult.cas());
    }

    private Collection getCollection(String key) {
        String clusterName = this.clusterKeyMapper.findCluster(key);
        return clusterCollectionMap.get(clusterName);
    }
}
