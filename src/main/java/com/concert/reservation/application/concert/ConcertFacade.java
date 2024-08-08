package com.concert.reservation.application.concert;

import com.concert.reservation.domain.concert.ConcertOptionDomain;
import com.concert.reservation.domain.concert.ConcertOptionService;
import com.concert.reservation.domain.seat.SeatOptionDomain;
import com.concert.reservation.domain.seat.SeatOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConcertFacade {

    private final ConcertOptionService concertOptionService;
    private final SeatOptionService seatOptionService;

    /**
     * 예약 가능 콘서트 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @return  List<ConcertOptionDomain>
     */
    public List<ConcertOptionDomain> findAllAvailableConcertForReservation() {
        LocalDateTime startDt = LocalDateTime.now();
        log.info("Start findAllAvailableConcertForReservation");

        // 예약 가능 콘서트 조회
        List<ConcertOptionDomain> concertOptionDomains = concertOptionService.getConcertOptionsFromRedis();
        if (concertOptionDomains.isEmpty()) {
            // 예약 가능 콘서트 조회 (Redis에 목록이 없는 경우 DB조회)
            concertOptionDomains = concertOptionService.findAllAvailableConcertForReservation();
        }

        LocalDateTime endDt = LocalDateTime.now();

        log.info("소요 시간 (밀리초) : {}", ChronoUnit.MILLIS.between(startDt, endDt));
        log.info("End findAllAvailableConcertForReservation");

        return concertOptionDomains;
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
