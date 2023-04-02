package ru.skillbox.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.orderservice.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
