package com.concert.reservation.application.payment;

import com.concert.reservation.domain.customer.CustomerService;
import com.concert.reservation.domain.reservation.ReservationService;
import com.concert.reservation.domain.seat.SeatOptionService;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.customer.CustomerDomain;
import com.concert.reservation.domain.payment.PaymentDomain;
import com.concert.reservation.domain.payment.PaymentService;
import com.concert.reservation.domain.seat.SeatOptionDomain;
import com.concert.reservation.domain.token.TokenDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final TokenService tokenService;
    private final SeatOptionService seatOptionService;
    private final ReservationService reservationService;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    /**
     * 결제
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   customerId - 고객 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   date - 날짜
     */
    public PaymentDomain payment(Long seatOptionId, Long customerId, Long concertOptionId, LocalDate date) {
        // 고객 토큰 유효성 체크
        this.validateToken(tokenService.findByCustomerId(customerId));

        // 좌석 유효성 체크
        SeatOptionDomain seatOptionDomain = seatOptionService.findSeat(seatOptionId, concertOptionId, date);
        this.validateSeat(seatOptionDomain);

        // 고객이 소지한 금액이 좌석 금액보다 적을 경우 오류
        CustomerDomain customerDomain = customerService.findById(customerId);
        if (customerDomain.getAmount() < seatOptionDomain.getPrice()) throw new IllegalArgumentException("금액이 부족합니다.");

        // 결제
        PaymentDomain paymentDomain = paymentService.save(PaymentDomain.builder().reservationId(seatOptionId).amount(seatOptionDomain.getPrice()).build());
        // 고객 금액 수정 (고객 금액 - 좌석 값)
        customerService.updateAmount(customerId, customerDomain.getAmount() - seatOptionDomain.getPrice());

        // 예약 상태 값 변경 (미완료 → 완료)
        reservationService.modifyStatus(reservationService.findBySeatOptionIdAndCustomerId(seatOptionId, customerId).getReservationId(), "COMPLETE");

        // 토큰 상태 값 변경 (활성화 → 만료)
        tokenService.modifyStatus(tokenService.findByCustomerId(customerId), "EXPIRED");

        return paymentDomain;
    }

    /**
     * 고객 토큰 유효성 체크
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   tokenDomain - 고객 도메인
     */
    private void validateToken(TokenDomain tokenDomain) {
        // 유효성 체크
        if (tokenDomain == null) throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        if (!"ACTIVE".equals(tokenDomain.getStatus())) throw new IllegalArgumentException("토큰이 만료되었습니다.");
    }

    /**
     * 좌석 유효성 체크
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   seatOptionDomain - 좌석 옵션 도메인
     */
    private void validateSeat(SeatOptionDomain seatOptionDomain) {
        if (seatOptionDomain == null) throw new IllegalArgumentException("좌석 정보가 존재하지 않습니다.");
        if (!"UNAVAILABLE".equals(seatOptionDomain.getStatus())) throw new IllegalArgumentException("좌석 상태를 확인해 주세요.");
    }
}
