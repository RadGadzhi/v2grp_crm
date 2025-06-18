package com.example.demo.repository;

import com.example.demo.model.OrderSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderSetRepository extends JpaRepository<OrderSet, Long> {
    List<OrderSet> findByOrderId(Long orderId);
}
