package com.concert.reservation.domain.payment;

import com.concert.reservation.domain.payment.event.PaymentEvent;
import com.concert.reservation.interfaces.event.PaymentEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventListener paymentEventListener;

    /**
     * 결제
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   paymentDomain - 결제 도메인
     * @return  paymentDomain
     */
    public PaymentDomain save(PaymentDomain paymentDomain) {
        return paymentRepository.save(paymentDomain);
    }

    /**
     * 결제 성공 이벤트 발행
     *
     * @author  양종문
     * @since   2024-08-11
     */
    public void success(Long paymentId, Long reservationId, Long amount, PaymentStatus status, LocalDateTime paymentDt) {
        paymentEventListener.paymentSuccessHandler(PaymentEvent.builder().paymentId(paymentId).reservationId(reservationId).amount(amount).status(status).paymentDt(paymentDt).build());
    }
}
