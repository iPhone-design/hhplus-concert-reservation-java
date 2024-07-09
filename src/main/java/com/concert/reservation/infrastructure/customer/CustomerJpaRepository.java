package com.concert.reservation.infrastructure.customer;

import com.concert.reservation.infrastructure.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
}