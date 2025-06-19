package com.example.demo.repository;

import com.example.demo.model.OrderSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSetRepository extends JpaRepository<OrderSet, Long> {
}
