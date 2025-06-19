package com.example.demo.service;

import com.example.demo.model.OrderSet;
import com.example.demo.model.SetProduct;
import com.example.demo.model.SupplyOrder;
import com.example.demo.repository.OrderSetRepository;
import com.example.demo.repository.SetProductRepository;
import com.example.demo.repository.SupplyOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyOrderService {

    private final SupplyOrderRepository supplyOrderRepository;
    private final OrderSetRepository orderSetRepository;
    private final SetProductRepository setProductRepository;

    public SupplyOrderService(SupplyOrderRepository supplyOrderRepository,
                              OrderSetRepository orderSetRepository,
                              SetProductRepository setProductRepository) {
        this.supplyOrderRepository = supplyOrderRepository;
        this.orderSetRepository = orderSetRepository;
        this.setProductRepository = setProductRepository;
    }

    public List<SupplyOrder> getAllOrders() {
        return supplyOrderRepository.findAll();
    }

    public List<SupplyOrder> getActiveOrders() {
        return supplyOrderRepository.findAll(); // заглушка, при необходимости добавим фильтр по статусу
    }

    public SupplyOrder save(SupplyOrder order) {
        return supplyOrderRepository.save(order);
    }

    public SupplyOrder findById(Long id) {
        return supplyOrderRepository.findById(id).orElse(null);
    }

    public List<SupplyOrder> findByCustomerId(Long customerId) {
        return supplyOrderRepository.findByCustomer_Id(customerId);
    }

    public void saveOrderWithSets(SupplyOrder order, List<OrderSet> sets) {
        SupplyOrder savedOrder = supplyOrderRepository.save(order);
        for (OrderSet set : sets) {
            set.setOrder(savedOrder);
            OrderSet savedSet = orderSetRepository.save(set);
            for (SetProduct sp : set.getProducts()) {
                sp.setOrderSet(savedSet);
                setProductRepository.save(sp);
            }
        }
    }
}
