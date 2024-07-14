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
     * 잔액 조회
     *
     * @author  양종문
     * @since   2024-07-07
     * @param   customerId - 고객 ID
     * @return  customerDomain
     */
    public CustomerDomain getUserInfo(Long customerId) {
        return customerService.getUserInfo(customerId);
    }

    /**
     * 잔액 충전
     *
     * @author  양종문
     * @since   2024-07-07
     * @param   customerId - 고객 ID
     * @param   addAmount - 충전 금액
     * @return  customerDomain
     */
    public CustomerDomain chargeAmount(Long customerId, Long addAmount) {
        return customerService.save(customerId, addAmount);
    }
}
