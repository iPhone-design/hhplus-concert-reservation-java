package com.concert.reservation.application.reservation;

import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationService;
import com.concert.reservation.domain.reservation.ReservationStatus;
import com.concert.reservation.domain.seat.SeatOptionService;
import com.concert.reservation.domain.seat.SeatOptionStatus;
import com.concert.reservation.domain.token.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final TokenService tokenService;
    private final SeatOptionService seatOptionService;
    private final ReservationService reservationService;

    /**
     * 콘서트 좌석 예약 요청
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   customerId - 고객 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   seatOptionId - 좌석 옵션 ID
     * @return  reservationDomain
     */
    @Transactional
    public ReservationDomain reservationConcert(Long customerId, Long concertOptionId, Long seatOptionId) {
        // 토큰 유효성 체크
        tokenService.checkActiveStatus(customerId);
        
        // 좌석 유효성 체크
        seatOptionService.checkAvailableStatus(seatOptionId, concertOptionId);

        // 좌석 상태 값 변경 (가능 → 불가능)
        seatOptionService.changeStatus(seatOptionId, concertOptionId, SeatOptionStatus.UNAVAILABLE);

        // 예약 (미완료)
        return reservationService.save(ReservationDomain.builder().customerId(customerId).concertOptionId(concertOptionId).seatOptionId(seatOptionId).status(ReservationStatus.INCOMPLETE).build());
    }
}
