package com.store.customer.controllers;

import com.store.customer.entitys.Customer;
import com.store.customer.entitys.Order;
import com.store.customer.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
@RestController
@RequestMapping("/api/v1")
public class CustomerOrderResource {

    private static final Logger log = LoggerFactory.getLogger(CustomerOrderResource.class);

    private static final String ENTITY_NAME = "order";

    @Value("${spring.application.name}")
    private String applicationName;

    private final CustomerService customerService;

    @Autowired
    public CustomerOrderResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customerOrders/{customerId}")
    public ResponseEntity<Order> createOrder(@PathVariable String customerId, @Valid @RequestBody Order order) {
        log.debug("REST request to save Order : {} for Customer ID: {}", order, customerId);
        if (customerId.isBlank()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Customer: " + ENTITY_NAME);
        }

        Optional<Customer> customerOptional = customerService.findById(UUID.fromString(customerId));
        if (customerOptional.isPresent()) {
            var customer = customerOptional.get();
            customer.addOrder(order);
            customerService.save(customer);

            return ResponseEntity.ok()
                    .body(order);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer: " + ENTITY_NAME);
        }

    }

}
