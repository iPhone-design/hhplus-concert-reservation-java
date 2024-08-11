package com.concert.reservation.interfaces.controller.customer;

import com.concert.reservation.domain.customer.CustomerDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long customerId;
    private String customerName;
    private Long amount;

    public static CustomerResponse toResponse (CustomerDomain customerDomain) {
        return CustomerResponse.builder()
                .customerId(customerDomain.getCustomerId())
                .customerName(customerDomain.getCustomerName())
                .amount(customerDomain.getAmount())
                .build();
    }
}