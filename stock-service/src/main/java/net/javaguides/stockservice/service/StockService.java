package net.javaguides.stockservice.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.stockservice.dto.Order;
import net.javaguides.stockservice.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public Long saveOrder(Order order) {
        stockRepository.save(order);
        return order.getOrderId();
    }
}
