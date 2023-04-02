package ru.skillbox.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.paymentservice.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
