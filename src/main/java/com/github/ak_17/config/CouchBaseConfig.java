package com.github.ak_17.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CouchBaseConfig {
    private String connectionString;
    private String userName;
    private String passWord;
    private String bucketName;
}
