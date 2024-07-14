package com.concert.reservation.application.reservation;

import com.concert.reservation.domain.customer.CustomerService;
import com.concert.reservation.domain.payment.PaymentService;
import com.concert.reservation.domain.seat.SeatOptionService;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.customer.CustomerDomain;
import com.concert.reservation.domain.payment.PaymentDomain;
import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationService;
import com.concert.reservation.domain.seat.SeatOptionDomain;
import com.concert.reservation.domain.token.TokenDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final TokenService tokenService;
    private final SeatOptionService seatOptionService;
    private final ReservationService reservationService;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    /**
     * 콘서트 좌석 예약 요청
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   reservationDomain - 예약 요청
     * @return  reservationDomain
     */
    public ReservationDomain reservationConcert(ReservationDomain reservationDomain) {
        // 파라미터
        Long customerId = reservationDomain.getCustomerId();
        Long seatOptionId = reservationDomain.getSeatOptionId();
        Long concertOptionId = reservationDomain.getConcertOptionId();
        LocalDate date = reservationDomain.getDate();

        // 토큰 프로세스
        this.processToken(customerId);
        
        // 좌석 프로세스
        this.processSeat(seatOptionId, concertOptionId, date);

        // 예약 프로세스
        this.processReservation(seatOptionId, customerId, concertOptionId, date);

        // 결제 프로세스
        this.processPayment(seatOptionId, customerId, concertOptionId, date);

        // 예약 조회
        return reservationService.findBySeatOptionIdAndCustomerId(seatOptionId, customerId);
    }

    /**
     * 토큰 프로세스
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   customerId - 고객 ID
     */
    private void processToken(Long customerId) {
        // 고객 토큰 조회
        TokenDomain tokenDomain = tokenService.findByCustomerId(customerId);
        // 고객 토큰 유효성 체크
        this.validateToken(tokenDomain);
        // 토큰 상태 값 변경 (대기 → 활성화)
        tokenService.modifyStatus(tokenDomain, "ACTIVE");
    }

    /**
     * 좌석 프로세스
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   date - 날짜
     */
    private void processSeat(Long seatOptionId, Long concertOptionId, LocalDate date) {
        // 좌석 조회
        SeatOptionDomain seatOptionDomain = seatOptionService.findSeat(seatOptionId, concertOptionId, date);
        // 좌석 유효성 체크
        this.validateSeat(seatOptionDomain);
    }

    /**
     * 예약 프로세스
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   customerId - 고객 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   date - 날짜
     */
    private void processReservation(Long seatOptionId, Long customerId, Long concertOptionId, LocalDate date) {
        // 예약
        reservationService.save(ReservationDomain.builder().seatOptionId(seatOptionId).customerId(customerId).status("INCOMPLETE").build());
        // 좌석 상태 값 변경 (이용 가능 → 이용 불가능)
        seatOptionService.modifyStatus(seatOptionService.findSeat(seatOptionId, concertOptionId, date), "UNAVAILABLE");
    }

    /**
     * 결제 프로세스
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   customerId - 고객 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   date - 날짜
     */
    private void processPayment(Long seatOptionId, Long customerId, Long concertOptionId, LocalDate date) {
        // 고객 조회
        CustomerDomain customerDomain = customerService.findById(customerId);
        // 좌석 조회
        SeatOptionDomain seatOptionDomain = seatOptionService.findSeat(seatOptionId, concertOptionId, date);
        
        // 고객이 소지한 금액이 좌석 금액보다 클 경우 결제 진행
        if (customerDomain.getAmount() > seatOptionDomain.getPrice()) {
            // 결제
            paymentService.save(PaymentDomain.builder().reservationId(seatOptionId).amount(seatOptionDomain.getPrice()).build());
            // 고객 금액 수정 (고객 금액 - 좌석 값)
            customerService.updateAmount(customerId, customerDomain.getAmount() - seatOptionDomain.getPrice());

            // 예약 상태 값 변경 (미완료 → 완료)
            reservationService.modifyStatus(reservationService.findBySeatOptionIdAndCustomerId(seatOptionId, customerId).getReservationId(), "COMPLETE");

            // 토큰 상태 값 변경 (활성화 → 만료)
            tokenService.modifyStatus(tokenService.findByCustomerId(customerId), "EXPIRED");
        }
    }

    /**
     * 고객 토큰 유효성 체크
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   tokenDomain - 고객 도메인
     */
    public void validateToken(TokenDomain tokenDomain) {
        // 유효성 체크
        if (tokenDomain == null) throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        if (!tokenDomain.getCustomerId().equals(tokenService.findFirstWaiting().getCustomerId())) throw new IllegalArgumentException("대기열 인원이 존재합니다.");
        if (tokenService.countActive() != 0) throw new IllegalArgumentException("활성화 인원이 존재합니다.");
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
        if (!"AVAILABLE".equals(seatOptionDomain.getStatus())) throw new IllegalArgumentException("예약 불가 좌석입니다.");
    }
}
