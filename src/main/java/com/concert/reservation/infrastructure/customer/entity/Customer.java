package com.concert.reservation.infrastructure.customer.entity;

import com.concert.reservation.domain.customer.dto.CustomerDomain;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @Column(name = "amount", nullable = false)
    private Long amount;

    public static Customer toEntity(CustomerDomain customerDomain) {
        return Customer.builder()
                .customerId(customerDomain.getCustomerId())
                .customerName(customerDomain.getCustomerName())
                .amount(customerDomain.getAmount())
                .build();
    }

    public static CustomerDomain toDomain(Customer customer) {
        return CustomerDomain.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .amount((customer.getAmount()))
                .build();
    }
}
