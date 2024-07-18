package com.concert.reservation.infrastructure.customer;

import com.concert.reservation.domain.customer.CustomerRepository;
import com.concert.reservation.domain.customer.CustomerDomain;
import com.concert.reservation.domain.customer.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    /**
     * 잔액 조회
     *
     * @author  양종문
     * @since   2024-07-07
     * @param   customerId - 고객 ID
     * @return  customerDomain
     */
    @Override
    public Optional<CustomerDomain> findById(Long customerId) {
        return customerJpaRepository.findById(customerId).map(Customer::toDomain);
    }

    /**
     * 잔액 충전
     *
     * @author  양종문
     * @since   2024-07-07
     * @param   customerDomain - 고객 도메인
     * @return  customerDomain
     */
    @Override
    public CustomerDomain save(CustomerDomain customerDomain) {
        return customerJpaRepository.save(Customer.toEntity(customerDomain)).toDomain();
    }
}
