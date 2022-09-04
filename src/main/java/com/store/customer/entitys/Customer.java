package com.store.customer.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vladislav Gruzdov
 */
@Document(collection = "CUSTOMER")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Customer extends BaseUuidEntity {

    private static final long serialVersionUID = 4271255497931152196L;

    @Field("ORDERS")
    private Set<Order> orders = new HashSet<>();

    public Customer addOrder(Order order) {
        this.orders.add(order);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Customer order = (Customer) o;

        return Objects.equals(id, order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
