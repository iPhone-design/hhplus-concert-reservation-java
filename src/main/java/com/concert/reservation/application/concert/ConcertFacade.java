package com.concert.reservation.application.concert;

import com.concert.reservation.domain.concert.ConcertOptionDomain;
import com.concert.reservation.domain.concert.ConcertOptionService;
import com.concert.reservation.domain.seat.SeatOptionDomain;
import com.concert.reservation.domain.seat.SeatOptionService;
import com.concert.reservation.domain.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

    private final ConcertOptionService concertOptionService;
    private final SeatOptionService seatOptionService;
    private final TokenService tokenService;

    /**
     * 예약 가능 콘서트 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @return  List<ConcertOptionDomain>
     */
    public List<ConcertOptionDomain> findAllAvailableConcertForReservation() {
        // 예약 가능 콘서트 조회
        return concertOptionService.findAllAvailableConcertForReservation();
    }

    /**
     * 예약 가능 콘서트 좌석 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @param   startDate - 시작일
     * @param   concertOptionId - 콘서트 옵션 ID
     * @return  List<SeatOptionDomain>
     */
    public List<SeatOptionDomain> findAllAvailableSeatForReservation(LocalDate startDate, Long concertOptionId) {
        // 예약 가능 콘서트 좌석 조회
        return seatOptionService.findAllAvailableSeatForReservation(startDate, concertOptionId);
    }
}