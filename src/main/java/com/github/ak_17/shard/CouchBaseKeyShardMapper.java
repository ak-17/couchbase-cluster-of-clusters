package com.github.ak_17.shard;

import com.github.ak_17.model.couchbase.CouchBaseShardingInfo;

public class CouchBaseKeyShardMapper implements KeyToShardMapper{

    private CouchBaseShardingInfo couchBaseShardingInfo;

    public CouchBaseKeyShardMapper(CouchBaseShardingInfo couchBaseShardingInfo) {
        this.couchBaseShardingInfo = couchBaseShardingInfo;
    }


    @Override
    public String getShard(String key) {
        return null;
    }
}
