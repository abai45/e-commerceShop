package kz.abai.eCommerce.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
    @Bean
    public CacheStore<String, Integer> clientCache() {
        return new CacheStore<>(900, TimeUnit.SECONDS);
    }
//    @Bean(name = {"registrationCache"})
//    public CacheStore<String, Integer> anotherCache() {
//        return new CacheStore<>(900, TimeUnit.SECONDS);
//    }
}
