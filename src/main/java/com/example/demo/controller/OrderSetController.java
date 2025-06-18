package com.example.demo.controller;

import com.example.demo.model.OrderSet;
import com.example.demo.model.SupplyOrder;
import com.example.demo.repository.OrderSetRepository;
import com.example.demo.repository.SupplyOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders/{orderId}/sets")
public class OrderSetController {

    private final OrderSetRepository orderSetRepository;
    private final SupplyOrderRepository supplyOrderRepository;

    @Autowired
    public OrderSetController(OrderSetRepository orderSetRepository, SupplyOrderRepository supplyOrderRepository) {
        this.orderSetRepository = orderSetRepository;
        this.supplyOrderRepository = supplyOrderRepository;
    }

    @GetMapping
    public String listSets(@PathVariable("orderId") Long orderId, Model model) {
        SupplyOrder order = supplyOrderRepository.findById(orderId).orElseThrow();
        List<OrderSet> sets = orderSetRepository.findByOrderId(orderId);
        model.addAttribute("order", order);
        model.addAttribute("sets", sets);
        model.addAttribute("newSet", new OrderSet());
        return "order-sets";
    }

    @PostMapping
    public String addSet(@PathVariable("orderId") Long orderId, @ModelAttribute OrderSet newSet) {
        SupplyOrder order = supplyOrderRepository.findById(orderId).orElseThrow();
        newSet.setOrder(order);
        orderSetRepository.save(newSet);
        return "redirect:/orders/" + orderId + "/sets";
    }
}
