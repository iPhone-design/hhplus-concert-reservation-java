package com.concert.reservation.domain.customer;

import com.concert.reservation.infrastructure.entity.Customer;
import com.concert.reservation.presentation.customer.CustomerRequest;
import com.concert.reservation.presentation.customer.CustomerResponse;

public class CustomerCommand {
    public static CustomerDomain toDomain (CustomerRequest customerRequest) {
        return CustomerDomain.builder()
                .customerId(customerRequest.getCustomerId())
                .amount(customerRequest.getAmount())
                .build();
    }

    public static CustomerDomain toDomain (Customer customer) {
        return CustomerDomain.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .amount(customer.getAmount())
                .build();
    }

    public static Customer toEntity (CustomerDomain customerDomain) {
        return Customer.builder()
                .customerId(customerDomain.getCustomerId())
                .customerName(customerDomain.getCustomerName())
                .amount(customerDomain.getAmount())
                .build();
    }

    public static CustomerResponse toResponse (CustomerDomain customerDomain) {
        return CustomerResponse.builder()
                .customerId(customerDomain.getCustomerId())
                .customerName(customerDomain.getCustomerName())
                .amount(customerDomain.getAmount())
                .build();
    }
}
