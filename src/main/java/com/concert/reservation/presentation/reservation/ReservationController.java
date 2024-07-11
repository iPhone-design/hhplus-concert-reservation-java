package com.concert.reservation.presentation.reservation;

import com.concert.reservation.application.reservation.ReservationFacade;
import com.concert.reservation.domain.reservation.ReservationCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/concert")
public class ReservationController {

    private final ReservationFacade reservationFacade;

    /**
     * 콘서트 좌석 예약 요청
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   reservationRequest - 예약 요청
     * @return  reservationResponse
     */
    @PostMapping("/reservation")
    public ReservationResponse reservationConcert(@Valid @RequestBody ReservationRequest reservationRequest) {
        return ReservationCommand.toResponse(reservationFacade.reservationConcert(ReservationCommand.toDomain(reservationRequest)));
    }
}
