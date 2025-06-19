package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> findAll() {
        return customerRepository.findAll(); // 👈 используется в MainController и SupplyOrderController
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void save(Customer customer) {
        customerRepository.save(customer); // 👈 используется в MainController
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
