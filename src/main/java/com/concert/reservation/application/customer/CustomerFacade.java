package com.concert.reservation.application.customer;

import com.concert.reservation.domain.customer.CustomerDomain;
import com.concert.reservation.domain.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerFacade {

    private final CustomerService customerService;

    /**
     * 고객 상세조회
     *
     * @author  양종문
     * @since   2024-07-07
     * @param   customerId - 고객 ID
     * @return  customerDomain
     */
    public CustomerDomain findById(Long customerId) {
        return customerService.findById(customerId);
    }

    /**
     * 잔액 충전
     *
     * @author  양종문
     * @since   2024-07-07
     * @param   customerId - 고객 ID
     * @param   amount - 충전 금액
     * @return  customerDomain
     */
    public CustomerDomain chargeAmount(Long customerId, Long amount) {
        return customerService.chargeAmount(customerId, amount);
    }
}
