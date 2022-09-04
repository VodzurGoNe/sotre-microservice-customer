package com.store.customer.repositorys;

import com.store.customer.entitys.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, UUID> {
}
