package com.example.securedActuatorWithCustomMetrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

// GET: http://localhost:8080/actuator/metrics/beer.orders?tag=type:light

@Component
public class BeerService {
    private final MeterRegistry meterRegistry;

    private Counter lightOrderCounter;
    private Counter aleOrderCounter;

    private final ConcurrentLinkedQueue<Order> orders = new ConcurrentLinkedQueue<>();

    public BeerService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        initOrderCounters();
    }

    private void initOrderCounters() {
        lightOrderCounter = this.meterRegistry.counter("beer.orders", "type", "light"); // 1 - create a counter
        aleOrderCounter = Counter.builder("beer.orders")    // 2 - create a counter using the fluent API
                .tag("type", "ale")
                .description("The number of orders ever placed for Ale beers")
                .register(meterRegistry);
    }

    void orderBeer(Order order) {
        orders.add(order);

        if ("light".equals(order.type())) {
            lightOrderCounter.increment(1.0);  // 3 - increment the counter
        } else if ("ale".equals(order.type())) {
            aleOrderCounter.increment();
        }
    }
}
