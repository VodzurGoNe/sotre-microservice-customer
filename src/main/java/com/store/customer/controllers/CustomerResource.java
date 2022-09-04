package com.store.customer.controllers;

import com.store.customer.entitys.Customer;
import com.store.customer.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Vladislav Gruzdov
 */
@RestController
@RequestMapping("/api/v1")
public class CustomerResource {

    private static final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private static final String ENTITY_NAME = "customer";

    @Value("${spring.application.name}")
    private String applicationName;

    private final CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws URISyntaxException {
        log.debug("REST request to save Customer : {}", customer);
        if (customer.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A new customer cannot already have an ID");
        }

        var result = customerService.save(customer);

        String message = String.format("A new %s is created with identifier %s", ENTITY_NAME, result.getId());

        var headers = new HttpHeaders();
        headers.add("X-" + applicationName + "-alert", message);
        headers.add("X-" + applicationName + "-params", result.getId().toString());

        return ResponseEntity.created(new URI("/api/customers/" + result.getId()))
                .headers(headers)
                .body(result);
    }

}
