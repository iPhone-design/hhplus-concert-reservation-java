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
    public CustomerDomain detailAmount(Long customerId) {
        return customerService.findById(customerId);
    }

    /**
     * 잔액 충전
     *
     * @author  양종문
     * @since   2024-07-07
     * @param   customerDomain - 고객 도메인
     * @return  customerDomain
     */
    public CustomerDomain chargeAmount(CustomerDomain customerDomain) {
        // 잔액 조회
        CustomerDomain detailCustomer = customerService.findById(customerDomain.getCustomerId());

        // 조회된 고객 정보의 잔액에 파라미터로 넘어온 금액을 더해준다.
        detailCustomer.setAmount(detailCustomer.getAmount() + customerDomain.getAmount());
        
        // 잔액 충전
        return customerService.save(detailCustomer);
    }
}
