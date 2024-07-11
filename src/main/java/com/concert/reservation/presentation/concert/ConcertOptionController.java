package com.concert.reservation.presentation.concert;

import com.concert.reservation.application.concert.ConcertFacade;
import com.concert.reservation.domain.Concert.ConcertOptionCommand;
import com.concert.reservation.domain.seat.SeatOptionCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/concert")
public class ConcertOptionController {

    private final ConcertFacade concertFacade;

    /**
     * 예약 가능 콘서트 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @return  List<ConcertOptionResponse>
     */
    @GetMapping("/dates")
    public List<ConcertOptionResponse> findAllAvailableConcertForReservation() {
        return ConcertOptionCommand.toResponse(concertFacade.findAllAvailableConcertForReservation());
    }

    /**
     * 예약 가능 콘서트 좌석 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @param   startDate - 시작일
     * @param   concertOptionId - 콘서트 옵션 ID
     * @return  List<SeatOptionResponse>
     */
    @GetMapping("/{date}/{concert-option-id}/seats")
    public List<SeatOptionResponse> findAllAvailableSeatForReservation(@PathVariable("date") LocalDate startDate, @PathVariable("concert-option-id") Long concertOptionId) {
        return SeatOptionCommand.toResponse(concertFacade.findAllAvailableSeatForReservation(startDate, concertOptionId));
    }
}
