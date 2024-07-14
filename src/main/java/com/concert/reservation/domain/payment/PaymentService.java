package com.concert.reservation.domain.payment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    /**
     * 결제
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   paymentDomain - 결제 도메인
     * @return  paymentDomain
     */
    @Transactional
    public PaymentDomain save(PaymentDomain paymentDomain) {
        return paymentRepository.save(paymentDomain);
    }
}
