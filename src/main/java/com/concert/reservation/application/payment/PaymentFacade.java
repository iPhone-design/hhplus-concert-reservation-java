package com.concert.reservation.application.payment;

import com.concert.reservation.domain.customer.CustomerService;
import com.concert.reservation.domain.payment.PaymentDomain;
import com.concert.reservation.domain.payment.PaymentService;
import com.concert.reservation.domain.payment.PaymentStatus;
import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationService;
import com.concert.reservation.domain.reservation.ReservationStatus;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.token.entity.TokenRedis;
import com.concert.reservation.domain.payment.event.PaymentEvent;
import com.concert.reservation.interfaces.event.PaymentEventListener;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final TokenService tokenService;
    private final ReservationService reservationService;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final PaymentEventListener paymentEventListener;

    /**
     * 결제
     *
     * @author  양종문
     * @since   2024-07-11
     * @history 2024-08-08 양종문 - 활성화 토큰 비활성화 처리 부분이 Redis 활성화 목록에서 삭제 처리가 되는 로직으로 변경
     *          2024-08-11 양종문 - 결제 완료 후 결제 성공 이벤트 발행 로직 추가 (외부 시스템)
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

        // 활성화 토큰 상세조회
        Optional<TokenRedis> tokenRedis = tokenService.getActiveTokenByCustomerIdFromRedis(customerId);
        // 활성화 토큰 삭제
        tokenService.deleteActiveQueueByUUID(tokenRedis.get().getUuid(), tokenRedis.get().getCustomerId(), tokenRedis.get().getExpireDt());

        // 결제
        PaymentDomain paymentDomain = paymentService.save(PaymentDomain.builder().reservationId(reservationDomain.getReservationId()).amount(amount).status(PaymentStatus.COMPLETE).build());

        log.info("[{}] 가 결제 완료", Thread.currentThread().getName());

        log.info("Thread : {} end", Thread.currentThread().getName());
        
        // 결제 성공 이벤트 발행
        paymentEventListener.paymentSuccessHandler(PaymentEvent.builder().paymentId(paymentDomain.getPaymentId()).reservationId(paymentDomain.getReservationId()).amount(paymentDomain.getAmount()).status(paymentDomain.getStatus()).paymentDt(paymentDomain.getPaymentDt()).build());

        return paymentDomain;
    }
}