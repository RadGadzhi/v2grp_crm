package com.example.demo.controller;

import com.example.demo.model.Supplier;
import com.example.demo.service.SupplierService;
import com.example.demo.service.SupplyOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final SupplierService supplierService;
    private final SupplyOrderService supplyOrderService;

    public MainController(SupplierService supplierService,
                          SupplyOrderService supplyOrderService) {
        this.supplierService = supplierService;
        this.supplyOrderService = supplyOrderService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("orders", supplyOrderService.getActiveOrders());
        return "main";
    }

    @GetMapping("/suppliers")
    public String suppliers(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        return "suppliers";
    }

    @GetMapping("/suppliers/new")
    public String createSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier-form";
    }

    @PostMapping("/suppliers")
    public String saveSupplier(@ModelAttribute Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/suppliers/{id}/orders")
    public String showOrdersBySupplier(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orders", supplyOrderService.findBySupplierId(id));
        return "supplier-orders";
    }
}
