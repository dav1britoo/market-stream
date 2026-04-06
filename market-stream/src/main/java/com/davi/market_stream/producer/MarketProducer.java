package com.davi.market_stream.producer;

import com.davi.market_stream.model.Quote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketProducer {

    private final KafkaTemplate<String, Quote> kafkaTemplate;
    private final Random random = new Random();

    // Simula o envio de um preço a cada 1 segundo
    @Scheduled(fixedRate = 1000)
    public void generateQuotes() {
        BigDecimal basePrice = new BigDecimal("65000.00");
        BigDecimal variation = BigDecimal.valueOf(random.nextDouble() * 100);
        BigDecimal finalPrice = basePrice.add(variation).setScale(2, RoundingMode.HALF_UP);

        Quote quote = new Quote("BTC-USD", finalPrice, Instant.now());

        log.info("Enviando para o Kafka: {}", quote);

        // O primeiro parâmetro é o nome do tópico, o segundo a chave e o terceiro o valor
        kafkaTemplate.send("market-prices", quote.symbol(), quote);
    }
}