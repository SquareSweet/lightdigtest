package me.sqsw.lightdigtest.service;

import me.sqsw.lightdigtest.config.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistingService {

    @CachePut(CacheConfig.BLACKLIST_CACHE_NAME)
    public String blacklistToken(String token) {
        return token;
    }

    @Cacheable(value = CacheConfig.BLACKLIST_CACHE_NAME, unless = "#result == null")
    public String getTokenBlacklist(String token) {
        return null;
    }

}
