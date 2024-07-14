package com.concert.reservation.infrastructure.customer;

import com.concert.reservation.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
    @Modifying
    @Query(value = "UPDATE customer" +
                   "   SET amount = :amount" +
                   " WHERE customer_id = :customer_id", nativeQuery = true)
    void updateAmount(@Param("customer_id") Long customerId, @Param("amount") Long amount);
}