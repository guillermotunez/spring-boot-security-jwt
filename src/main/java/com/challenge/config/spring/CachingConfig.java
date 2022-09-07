package com.challenge.config.spring;

import com.challenge.config.NotTestProfileCondition;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@EnableCaching
@Configuration
@Conditional(NotTestProfileCondition.class)
public class CachingConfig {

    @Bean("percentageApiCacheManager")
    public RedisCacheManager percentageApiCacheManager(RedisConnectionFactory connectionFactory){

        RedisCacheConfiguration percentageApiCache = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMillis(1800000));

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        cacheConfigurations.put("percentageApiCache", percentageApiCache);

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
                .withInitialCacheConfigurations(cacheConfigurations).build();
    }
}
