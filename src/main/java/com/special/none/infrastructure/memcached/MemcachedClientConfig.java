package com.special.none.infrastructure.memcached;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
public class MemcachedClientConfig {

    @Value("${memcached.endpoint}")
    String endpoint;

    @Bean
    MemcachedClient memcachedClient() throws IOException {
        String host = endpoint.split(":")[0];
        int port = Integer.parseInt(endpoint.split(":")[1]);
        return new MemcachedClient(new InetSocketAddress(host, port));
    }
}
