package com.concert.reservation.infrastructure.customer;

import com.concert.reservation.application.customer.CustomerRepository;
import com.concert.reservation.domain.customer.CustomerCommand;
import com.concert.reservation.domain.customer.CustomerDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public CustomerDomain findById(Long customerId) {
        return CustomerCommand.toDomain(customerJpaRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("고객 정보가 존재하지 않습니다.")));
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
        return CustomerCommand.toDomain(customerJpaRepository.save(CustomerCommand.toEntity(customerDomain)));
    }
}
