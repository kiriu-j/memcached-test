package com.special.none.service;

import com.special.none.vo.CachedContentsVo;
import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class MemcachedTestServiceImpl implements MemcachedTestService {

    @Autowired
    MemcachedClient client;

    private static Logger logger = LoggerFactory.getLogger(MemcachedTestService.class);

    @Override
    public CachedContentsVo get(String cachedId) {
        return CachedContentsVo.builder()
                    .cachedId(cachedId)
                    .contents(client.get(cachedId).toString())
                .build();
    }

    @Override
    public CachedContentsVo set(String cachedContents) {
        final String cachedId = UUID.randomUUID().toString();
        logger.info(new StringBuilder()
                            .append("Start set cache\n")
                            .append("cached ID: ").append(cachedId)
                            .append("\n")
                            .append("cached contents: ").append(cachedContents)
                        .toString());
        client.set(cachedId, 1800, cachedContents);
        logger.info("Set cached successfully!!");
        return this.get(cachedId);
    }

    @Override
    public CachedContentsVo set(String cachedId, String cachedContents) {
        logger.info("cachedId: " + cachedId);
        logger.info("cachedContents: " + cachedContents);
        CachedContentsVo oldData = this.get(cachedId);
        logger.info(new StringBuilder()
                .append("Old cache data\n")
                .append("cached ID: ").append(oldData.getCachedId())
                .append("\n")
                .append("cached contents: ").append(oldData.getContents())
                .toString());
        logger.info(new StringBuilder()
                .append("Start set new cache\n")
                .append("cached ID: ").append(cachedId)
                .append("\n")
                .append("cached contents: ").append(cachedContents)
                .toString());
        client.replace(cachedId, 1800, cachedContents);
        return this.get(cachedId);
    }
}
