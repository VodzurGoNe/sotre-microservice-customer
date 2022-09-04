package com.store.customer.controllers.dto;

import com.store.customer.enums.HealthStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Health {

    private HealthStatus status;

}
