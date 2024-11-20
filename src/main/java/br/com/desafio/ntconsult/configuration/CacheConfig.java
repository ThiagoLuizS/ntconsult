package br.com.desafio.ntconsult.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("voto");
    }

    @CacheEvict(allEntries = true, cacheNames = "voto")
    @Scheduled(fixedDelay = 10 * 60 * 1000 ,  initialDelay = 500)
    public void removeCache() {
        log.info(">> Remover cache voto");
    }
}
