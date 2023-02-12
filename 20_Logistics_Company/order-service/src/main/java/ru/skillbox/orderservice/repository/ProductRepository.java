package ru.skillbox.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.orderservice.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
