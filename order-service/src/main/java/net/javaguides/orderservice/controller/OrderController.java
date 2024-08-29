package net.javaguides.orderservice.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.basedomains.dto.Order;
import net.javaguides.basedomains.dto.OrderEvent;
import net.javaguides.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer;

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        OrderEvent  orderEvent = OrderEvent.builder()
                .status("PENDING")
                .message("Order Status Is In Pending State")
                .build();
        orderEvent.setOrder(order);
        orderProducer.sendMessage(orderEvent);
        return "Ordered Places Successfully..!";
    }
}
