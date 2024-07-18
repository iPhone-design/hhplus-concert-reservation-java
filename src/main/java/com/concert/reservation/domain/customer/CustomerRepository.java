package com.concert.reservation.domain.customer;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CustomerRepository {
    Optional<CustomerDomain> findById(Long customerId);
    CustomerDomain save(CustomerDomain customerDomain);
}