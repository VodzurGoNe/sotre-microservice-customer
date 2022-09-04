package com.store.customer.services;

import com.store.customer.entitys.Customer;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
public interface CustomerService {

    String NAME = "customer_CustomerService";

    Customer save(Customer customer);

    Optional<Customer> findById(UUID id);

}
