package com.concert.reservation.application.customer;

import com.concert.reservation.domain.customer.CustomerDomain;
import org.springframework.stereotype.Component;

@Component
public interface CustomerRepository {
    CustomerDomain findById(Long customerId);
    CustomerDomain save(CustomerDomain customerDomain);
    void updateAmount(Long customerId, Long amount);
}