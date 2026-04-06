package com.davi.market_stream.consumer;

import com.davi.market_stream.model.Quote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketConsumer {

    private final ReactiveRedisTemplate<String, Quote> redisTemplate;

    @KafkaListener(topics = "market-prices", groupId = "market-group")
    public void consume(Quote quote) {
        log.info("Consumido do Kafka: {}", quote);

        redisTemplate.opsForValue()
                .set("price:" + quote.symbol(), quote)
                .subscribe(success -> log.info("Último preço do {} salvo no Redis!", quote.symbol()));
    }
}