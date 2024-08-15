package com.concert.reservation.interfaces.api.controller.customer;

import com.concert.reservation.domain.customer.CustomerDomain;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private Long customerId;
    private String customerName;
    @Min(value = 0, message = "최소 금액은 0원 이상입니다.")
    private Long amount;

    public static CustomerDomain toDomain (CustomerRequest customerRequest) {
        return CustomerDomain.builder()
                .customerId(customerRequest.getCustomerId())
                .customerName(customerRequest.getCustomerName())
                .amount(customerRequest.getAmount())
                .build();
    }
}
