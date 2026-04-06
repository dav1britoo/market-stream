package com.davi.market_stream.controller;

import com.davi.market_stream.model.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class MarketController {

    private final ReactiveRedisTemplate<String, Quote> redisTemplate;

    @GetMapping("/price/{symbol}")
    public Mono<Quote> getLatestPrice(@PathVariable String symbol) {
        return redisTemplate.opsForValue().get("price:" + symbol);
    }
}