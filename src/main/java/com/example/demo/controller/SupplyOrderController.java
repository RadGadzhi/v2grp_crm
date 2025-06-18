package com.example.demo.controller;

import com.example.demo.model.SupplyOrder;
import com.example.demo.service.SupplierService;
import com.example.demo.service.SupplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SupplyOrderController {

    private final SupplyOrderService supplyOrderService;
    private final SupplierService supplierService;

    @Autowired
    public SupplyOrderController(SupplyOrderService supplyOrderService,
                                 SupplierService supplierService) {
        this.supplyOrderService = supplyOrderService;
        this.supplierService = supplierService;
    }

    @GetMapping("/orders")
    public String listOrders(Model model) {
        List<SupplyOrder> orders = supplyOrderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order-list";
    }
}
