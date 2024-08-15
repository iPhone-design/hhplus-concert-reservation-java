package com.concert.reservation.infrastructure.db.payment;

import com.concert.reservation.domain.payment.PaymentDomain;
import com.concert.reservation.domain.payment.PaymentRepository;
import com.concert.reservation.domain.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    /**
     * 결제
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   paymentDomain - 결제 도메인
     * @return  paymentDomain
     */
    @Override
    public PaymentDomain save(PaymentDomain paymentDomain) {
        return paymentJpaRepository.save(Payment.toEntity(paymentDomain)).toDomain();
    }
}
