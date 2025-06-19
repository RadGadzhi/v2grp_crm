package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.SupplyOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final CustomerService customerService;
    private final SupplyOrderService supplyOrderService;

    public MainController(CustomerService customerService,
                          SupplyOrderService supplyOrderService) {
        this.customerService = customerService;
        this.supplyOrderService = supplyOrderService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("orders", supplyOrderService.getActiveOrders());
        return "main";
    }

    
}
