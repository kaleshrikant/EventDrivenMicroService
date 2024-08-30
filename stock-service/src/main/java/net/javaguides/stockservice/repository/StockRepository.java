package net.javaguides.stockservice.repository;

import net.javaguides.stockservice.dto.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Order, String> {
}
