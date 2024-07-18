package com.concert.reservation.domain.customer;

import com.concert.reservation.domain.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * 고객 상세조회
     *
     * @author  양종문
     * @since   2024-07-07
     * @param   customerId - 고객 ID
     * @return  customerDomain
     */
    public CustomerDomain findById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomException(HttpStatus.ACCEPTED, "고객 정보가 존재하지 않습니다."));
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
    @Transactional
    public CustomerDomain chargeAmount(Long customerId, Long amount) {
        // 고객 상세조회
        CustomerDomain customerDomain = this.findById(customerId);

        // 잔액 추가
        customerDomain.plusAmount(amount);
        
        // 저장
        return customerRepository.save(customerDomain);
    }

    /**
     * 잔액 사용
     *
     * @author  양종문
     * @since   2024-07-17
     * @param   customerId - 고객 ID
     * @param   amount - 결제 금액
     * @return  customerDomain
     */
    public CustomerDomain useAmount(Long customerId, Long amount) {
        // 고객 상세조회
        CustomerDomain customerDomain = this.findById(customerId);

        // 잔액 사용
        customerDomain.minusAmount(amount);

        // 저장
        return customerRepository.save(customerDomain);
    }

    /**
     * 잔액 체크
     *
     * @author  양종문
     * @since   2024-07-17
     * @param   customerId - 고객 ID
     * @param   amount - 결제 금액
     */
    public void checkAmount(Long customerId, Long amount) {
        // 고객 상세조회
        CustomerDomain customerDomain = this.findById(customerId);
        
        // 결제 금액과 잔액 비교
        if (customerDomain.getAmount() < amount) {
            throw new CustomException(HttpStatus.ACCEPTED, "잔액이 부족합니다.");
        }
    }
}
