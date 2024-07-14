package com.concert.reservation.domain.customer;

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
    public CustomerDomain getUserInfo(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("고객 정보가 존재하지 않습니다."));
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
    @Transactional
    public CustomerDomain save(Long customerId, Long addAmount) {
        // 잔액 조회
        CustomerDomain customerDomain = this.getUserInfo(customerId);

        // 잔액 추가
        customerDomain.plusAmount(addAmount);
        
        // 저장
        return customerRepository.save(customerDomain);
    }
}
