package com.davi.market_stream.model;

import java.math.BigDecimal;
import java.time.Instant;

public record Quote(String symbol, BigDecimal price, Instant timestamp) {}

