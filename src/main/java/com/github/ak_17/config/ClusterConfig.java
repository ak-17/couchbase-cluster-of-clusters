package com.github.ak_17.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClusterConfig {
    private String name;
    private String connectionString;
    private String username;
    private String password;
}
