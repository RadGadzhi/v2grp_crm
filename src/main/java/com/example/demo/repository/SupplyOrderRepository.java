package com.example.demo.repository;

import com.example.demo.model.SupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {
    List<SupplyOrder> findByCustomer_Id(Long customerId);
}
