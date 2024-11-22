package ex.evencategory.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.github.benmanes.caffeine.cache.Caffeine;
import ex.evencategory.domain.Category;
import ex.evencategory.domain.RecCategory;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    @Primary
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(defaultCacheConfig())
            .withInitialCacheConfigurations(cacheConfigurations())
            .build();
    }

    private RedisCacheConfiguration defaultCacheConfig() {
        return RedisCacheConfiguration.defaultCacheConfig() // 기본 설정
            .serializeKeysWith(SerializationPair.fromSerializer(RedisSerializer.string())) // 키는 문자열
            .serializeValuesWith(SerializationPair.fromSerializer(RedisSerializer.string())) // 값은 문자열
            .entryTtl(Duration.ofMinutes(1L)) // 기본 캐싱 TTL 설정
            .disableCachingNullValues(); // null 캐싱 불가
    }

    private Map<String, RedisCacheConfiguration> cacheConfigurations() {

        return Map.of(
            "category::allsub", categoryListCacheConfig(),
            "category::onlysub", categoryListCacheConfig(),
            "rec-category", recCategoryCacheConfig()
        );
    }

    private RedisCacheConfiguration categoryListCacheConfig() {
        CollectionType collectionType = new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Category.class);
        Jackson2JsonRedisSerializer<List<Category>> serializer = new Jackson2JsonRedisSerializer<>(collectionType);

        return defaultCacheConfig()
            .entryTtl(Duration.ofHours(1L))
            .serializeValuesWith(SerializationPair.fromSerializer(serializer))
            .disableCachingNullValues();
    }

    private RedisCacheConfiguration recCategoryCacheConfig() {
        Jackson2JsonRedisSerializer<RecCategory> serializer = new Jackson2JsonRedisSerializer<>(RecCategory.class);

        return defaultCacheConfig()
            .entryTtl(Duration.ofDays(7L))
            .serializeValuesWith(SerializationPair.fromSerializer(serializer))
            .disableCachingNullValues();
    }

    @Bean
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(caffeineCacheOfRecCategory()));

        return cacheManager;
    }

    private CaffeineCache caffeineCacheOfRecCategory() {
        return new CaffeineCache("rec-category", Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofDays(7L))
            .maximumSize(10_000L)
            .recordStats()
            .build());
    }
}