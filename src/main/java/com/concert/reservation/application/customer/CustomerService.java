package com.concert.reservation.application.customer;

import com.concert.reservation.domain.customer.CustomerDomain;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * 잔액 조회
     *
     * @author  양종문
     * @since   2024-07-07
     * @param   customerId - 고객 ID
     * @return  customerDomain
     */
    public CustomerDomain findById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    /**
     * 잔액 충전
     *
     * @author  양종문
     * @since   2024-07-07
     * @param   customerDomain - 고객 도메인
     * @return  customerDomain
     */
    @Transactional
    public CustomerDomain save(CustomerDomain customerDomain) {
        return customerRepository.save(customerDomain);
    }

    /**
     * 잔액 수정
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   customerId - 고객 ID
     * @param   amount - 금액
     */
    @Transactional
    public void updateAmount(Long customerId, Long amount) {
        customerRepository.updateAmount(customerId, amount);
    }
}
