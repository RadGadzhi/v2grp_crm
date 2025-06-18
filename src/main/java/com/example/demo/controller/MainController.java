package com.example.demo.controller;

import com.example.demo.model.SetProduct;
import com.example.demo.model.Supplier;
import com.example.demo.repository.SetProductRepository;
import com.example.demo.service.SupplierService;
import com.example.demo.service.SupplyOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private SetProductRepository setProductRepository;

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
        model.addAttribute("newSupplier", new Supplier()); // 🔧 добавлено
        return "suppliers";
    }

    @PostMapping("/suppliers")
    public String saveSupplier(@ModelAttribute("newSupplier") Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/suppliers/{id}/orders")
    public String showOrdersBySupplier(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orders", supplyOrderService.findBySupplierId(id));
        return "supplier-orders";
    }

    @GetMapping("/order-products")
    public String orderProductsPage(Model model) {
        List<SetProduct> products = setProductRepository.findAll();
        model.addAttribute("products", products);
        return "order-products";
    }
}
