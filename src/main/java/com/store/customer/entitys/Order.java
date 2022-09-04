package com.store.customer.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Vladislav Gruzdov
 */
@Document(collection = "ORDER")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order extends BaseUuidEntity {

    private static final long serialVersionUID = 2271255497931152196L;

    @NotNull
    @Field("CUSTOMER_ID")
    private UUID customerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        return Objects.equals(id, order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
