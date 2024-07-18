package com.concert.reservation.presentation.concert;

import com.concert.reservation.application.concert.ConcertFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * @param   concertOptionRequest - 콘서트 옵션 요청
     * @return  List<ConcertOptionResponse>
     */
    @GetMapping("/dates")
    public List<ConcertOptionResponse> findAllAvailableConcertForReservation(@Valid @RequestBody ConcertOptionRequest concertOptionRequest) {
        return ConcertOptionResponse.toResponse(concertFacade.findAllAvailableConcertForReservation(concertOptionRequest.getCustomerId()));
    }

    /**
     * 예약 가능 콘서트 좌석 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @param   startDate - 시작일
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   concertOptionRequest - 콘서트 옵션 요청
     * @return  List<SeatOptionResponse>
     */
    @GetMapping("/{date}/{concert-option-id}/seats")
    public List<SeatOptionResponse> findAllAvailableSeatForReservation(@PathVariable("date") LocalDate startDate, @PathVariable("concert-option-id") Long concertOptionId, @Valid @RequestBody ConcertOptionRequest concertOptionRequest) {
        return SeatOptionResponse.toResponse(concertFacade.findAllAvailableSeatForReservation(concertOptionRequest.getCustomerId(), startDate, concertOptionId));
    }
}
