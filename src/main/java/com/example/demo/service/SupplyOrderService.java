package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyOrderService {

    private final SupplyOrderRepository supplyOrderRepository;
    private final ProductRepository productRepository;
    private final ProductAliasRepository productAliasRepository;
    private final SetProductRepository setProductRepository;
    private final OrderSetRepository orderSetRepository;

    @Autowired
    public SupplyOrderService(SupplyOrderRepository supplyOrderRepository,
                              ProductRepository productRepository,
                              ProductAliasRepository productAliasRepository,
                              SetProductRepository setProductRepository,
                              OrderSetRepository orderSetRepository) {
        this.supplyOrderRepository = supplyOrderRepository;
        this.productRepository = productRepository;
        this.productAliasRepository = productAliasRepository;
        this.setProductRepository = setProductRepository;
        this.orderSetRepository = orderSetRepository;
    }

    public List<SupplyOrder> getActiveOrders() {
        return supplyOrderRepository.findByActiveTrue();
    }

    public List<SupplyOrder> getAllOrders() {
        return supplyOrderRepository.findAll();
    }

    public List<SupplyOrder> findBySupplierId(Long supplierId) {
        return supplyOrderRepository.findBySupplierId(supplierId);
    }

    public SupplyOrder findById(Long id) {
        return supplyOrderRepository.findById(id).orElse(null);
    }

    public void addProductToOrder(Long orderId, String inputName, int quantity) {
        SupplyOrder order = findById(orderId);
        if (order == null) return;

        Product product = resolveProductByName(inputName);
        if (product == null) {
            product = new Product();
            product.setName(inputName);
            product = productRepository.save(product);

            ProductAlias alias = new ProductAlias();
            alias.setAlias(inputName);
            alias.setProduct(product);
            productAliasRepository.save(alias);
        }

        OrderSet orderSet = getOrCreateDefaultOrderSet(order);

        SetProduct setProduct = new SetProduct();
        setProduct.setOrderSet(orderSet);
        setProduct.setProduct(product);
        setProduct.setQuantity(quantity);

        setProductRepository.save(setProduct);
    }

    private Product resolveProductByName(String name) {
        ProductAlias alias = productAliasRepository.findByAliasIgnoreCase(name);
        if (alias != null) return alias.getProduct();

        return productRepository.findAll().stream()
                .filter(p -> p.getName().replaceAll("\\W", "").equalsIgnoreCase(name.replaceAll("\\W", "")))
                .findFirst()
                .orElse(null);
    }

    private OrderSet getOrCreateDefaultOrderSet(SupplyOrder order) {
        List<OrderSet> sets = orderSetRepository.findByOrderId(order.getId());
        if (!sets.isEmpty()) return sets.get(0);

        OrderSet newSet = new OrderSet();
        newSet.setOrder(order);
        newSet.setName("Комплект по умолчанию");
        return orderSetRepository.save(newSet);
    }
}
