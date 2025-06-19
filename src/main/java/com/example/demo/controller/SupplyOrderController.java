package com.example.demo.controller;

import com.example.demo.model.SupplyOrder;
import com.example.demo.model.Customer;
import com.example.demo.service.SupplyOrderService;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SupplyOrderController {

    @Autowired
    private SupplyOrderService supplyOrderService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/orders/new")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new SupplyOrder());
        model.addAttribute("customers", customerService.findAll());
        return "order-form";
    }

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute("order") SupplyOrder order) {
        supplyOrderService.save(order);
        return "redirect:/";
    }
}
