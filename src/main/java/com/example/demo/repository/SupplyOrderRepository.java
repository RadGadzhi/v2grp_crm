package com.example.demo.repository;

import com.example.demo.model.SupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {

    List<SupplyOrder> findBySupplierId(Long supplierId);  // ✅ вот этот метод нужен

    List<SupplyOrder> findByActiveTrue(); // если есть активные заказы
}
