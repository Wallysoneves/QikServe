package com.br.qikserveteste.infrastructure.cache;

import com.br.qikserveteste.domain.dto.OrderDto;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
public class OrderCache {

    private static final String CACHE_NAME = "orderCache";

    private final Cache cache;

    public OrderCache(CacheManager cacheManager) {
        this.cache = cacheManager.getCache(CACHE_NAME);
    }

    @Scheduled(fixedRate = 3600000) // 1 hora
    public void atualizarCache () {
        cache.evict(CACHE_NAME);
    }

    public void saveOrder(String orderId, OrderDto order) {
        cache.put(orderId, order);
    }

    public OrderDto getOrderById(String orderId) {
        Cache.ValueWrapper valueWrapper = cache.get(orderId);
        return valueWrapper != null ? (OrderDto) valueWrapper.get() : null;
    }
}
