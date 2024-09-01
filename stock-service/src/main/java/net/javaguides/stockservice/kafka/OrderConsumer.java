package net.javaguides.stockservice.kafka;

import lombok.RequiredArgsConstructor;
import net.javaguides.basedomains.dto.OrderEvent;
import net.javaguides.stockservice.dto.Order;
import net.javaguides.stockservice.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private final StockService stockService;


    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order Event Recived In Stock Service => %s ", String.valueOf(orderEvent)));

        // save the order event into the database.

        Long orderId = stockService.saveOrder(mapper(orderEvent));
        LOGGER.info(String.format("Order ID Saved In Stock Service => %s ", String.valueOf(orderId)));
    }

    private static net.javaguides.stockservice.dto.Order mapper(OrderEvent orderEvent) {
        return Order.builder()
                .name(orderEvent.getOrder().getName())
                .qty(orderEvent.getOrder().getQty())
                .price(orderEvent.getOrder().getPrice())
                .build();
    }
}
