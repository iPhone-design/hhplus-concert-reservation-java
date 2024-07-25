package com.concert.reservation.application.payment;

import com.concert.reservation.domain.customer.CustomerService;
import com.concert.reservation.domain.payment.PaymentDomain;
import com.concert.reservation.domain.payment.PaymentService;
import com.concert.reservation.domain.payment.PaymentStatus;
import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationService;
import com.concert.reservation.domain.reservation.ReservationStatus;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.token.TokenStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final TokenService tokenService;
    private final ReservationService reservationService;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    /**
     * 결제
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   customerId - 고객 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   amount - 결제 금액
     * @return  paymentDomain
     */
    @Transactional
    public PaymentDomain payment(Long customerId, Long concertOptionId, Long seatOptionId, Long amount) {
        log.info("Thread : {} start", Thread.currentThread().getName());

        // 예약 조회
        ReservationDomain reservationDomain = reservationService.findByConcertOptionIdAndSeatOptionIdAndCustomerId(concertOptionId, seatOptionId, customerId);
        // 예약 상태 값 변경 (미완료 → 완료)
        reservationService.changeStatus(reservationDomain.getReservationId(), ReservationStatus.COMPLETE);

        // 잔액 유효성 체크
        customerService.checkAmount(customerId, amount);
        // 이용자 금액 사용
        customerService.useAmount(customerId, amount);

        // 토큰 상태 값 변경 (활성화 → 만료)
        tokenService.changeStatus(customerId, TokenStatus.EXPIRE);

        // 결제
        PaymentDomain paymentDomain = paymentService.save(PaymentDomain.builder().reservationId(reservationDomain.getReservationId()).amount(amount).status(PaymentStatus.COMPLETE).build());

        log.info("[{}] 가 결제 완료", Thread.currentThread().getName());

        log.info("Thread : {} end", Thread.currentThread().getName());

        return paymentDomain;
    }
}